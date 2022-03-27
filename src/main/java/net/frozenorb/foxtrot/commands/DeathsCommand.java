package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.model.DBCollectionFindOptions;
import com.mongodb.util.JSON;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.uuid.FrozenUUIDCache;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@CommandAlias("deaths|death")
public class DeathsCommand extends BaseCommand {

    private final static DateFormat FORMAT = new SimpleDateFormat("M dd yyyy h:mm a");

    @Subcommand("refund")
    @Description("Refunds the inventory of a player who died.")
    @CommandPermission("foxtrot.deathrefund")
    public static void refund(Player sender, String id) {
        Foxtrot.getInstance().getServer().getScheduler().runTaskAsynchronously(Foxtrot.getInstance(), () -> {
            DBCollection mongoCollection = Foxtrot.getInstance().getMongoPool().getDB(Foxtrot.MONGO_DB_NAME).getCollection("Deaths");
            DBObject object = mongoCollection.findOne(id);

            if (object != null) {
                BasicDBObject basicDBObject = (BasicDBObject) object;
                Player player = Bukkit.getPlayer(UUIDfromString(object.get("uuid").toString()));

                if (basicDBObject.containsKey("refundedBy")) {
                    sender.sendMessage(ChatColor.RED + "This death was already refunded by " + FrozenUUIDCache.name(UUIDfromString(basicDBObject.getString("refundedBy"))) + ".");
                    return;
                }

                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Player isn't on to receive items.");
                    return;
                }

                ItemStack[] contents = Foxtrot.PLAIN_GSON.fromJson(JSON.serialize(((BasicDBObject) basicDBObject.get("playerInventory")).get("contents")), ItemStack[].class);
                ItemStack[] armor = Foxtrot.PLAIN_GSON.fromJson(JSON.serialize(((BasicDBObject) basicDBObject.get("playerInventory")).get("armor")), ItemStack[].class);

                LastInvCommand.cleanLoot(contents);
                LastInvCommand.cleanLoot(armor);

                player.getInventory().setContents(contents);
                player.getInventory().setArmorContents(armor);

                basicDBObject.put("refundedBy", sender.getUniqueId().toString().replace("-", ""));
                basicDBObject.put("refundedAt", new Date());

                mongoCollection.save(basicDBObject);

                player.sendMessage(ChatColor.GREEN + "Your inventory has been reset to an inventory from a previous life.");
                sender.sendMessage(ChatColor.GREEN + "Successfully refunded inventory to " + player.getName() + ".");

            } else {
                sender.sendMessage(ChatColor.RED + "Death not found.");
            }

        });
    }
    @Default
    @CommandPermission("foxtrot.deaths")
    public static void deaths(Player sender, OfflinePlayer fakePlayer) {
        UUID player = fakePlayer.getUniqueId();
        Foxtrot.getInstance().getServer().getScheduler().runTaskAsynchronously(Foxtrot.getInstance(), () -> {
            sender.sendMessage("");
            sender.sendMessage(ChatColor.GRAY + "Grabbing 10 latest deaths of " + FrozenUUIDCache.name(player) + "...");
            sender.sendMessage("");

            DBCollection mongoCollection = Foxtrot.getInstance().getMongoPool().getDB(Foxtrot.MONGO_DB_NAME).getCollection("Deaths");

            boolean empty = true;
            for (DBObject object : mongoCollection.find(new BasicDBObject("uuid", player.toString().replace("-", "")), new DBCollectionFindOptions().limit(10).sort(new BasicDBObject("when", -1)))) {
                empty = false;
                BasicDBObject basicDBObject = (BasicDBObject) object;

                ComponentBuilder message = new ComponentBuilder();

                message.append(ChatColor.RED + FrozenUUIDCache.name(player));

                if (object.get("killerUUID") != null) {
                    message.append(ChatColor.GRAY + " died to " + ChatColor.RED + FrozenUUIDCache.name(UUIDfromString(object.get("killerUUID").toString())));
                } else {
                    if (object.get("reason") != null) {
                        message.append(ChatColor.GRAY + " died from " + object.get("reason").toString().toLowerCase() + " damage.");
                    } else {
                        message.append(ChatColor.GRAY + " died from unknown causes.");
                    }
                }

                message.append(" (" + FORMAT.format(basicDBObject.getDate("when")) + ") ").color(ChatColor.GOLD.asBungee());

                if (!(basicDBObject.containsKey("refundedBy"))) {
                    message.append("[UNDO]").color(ChatColor.GOLD.asBungee()).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to refund inventory").color(ChatColor.GREEN.asBungee()).create()))
                            .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deathrefund " + object.get("_id").toString()));
                }

                sender.spigot().sendMessage(message.create());
            }

            if (empty) {
                sender.sendMessage(ChatColor.RED + FrozenUUIDCache.name(player) + " has no deaths to display.");
            }

            sender.sendMessage("");
        });
    }

    private static UUID UUIDfromString(String string) {
        return UUID.fromString(
                string.replaceFirst(
                        "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"
                )
        );
    }

}