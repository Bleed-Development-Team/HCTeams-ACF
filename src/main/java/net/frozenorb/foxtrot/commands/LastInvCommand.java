package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.uuid.FrozenUUIDCache;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

@CommandAlias("lastinv")
@CommandPermission("foxtrot.lastinv")
public class LastInvCommand extends BaseCommand {


    @Default
    @Description("View your target's last inventory")
    public static void lastInv(Player sender, Player player) {
        Foxtrot.getInstance().getServer().getScheduler().runTaskAsynchronously(Foxtrot.getInstance(), () -> {
            Foxtrot.getInstance().runRedisCommand((redis) -> {
                if (!redis.exists("lastInv:contents:" + player.toString())) {
                    sender.sendMessage(ChatColor.RED + "No last inventory recorded for " + FrozenUUIDCache.name(player.getUniqueId()));
                    return null;
                }

                ItemStack[] contents = Foxtrot.PLAIN_GSON.fromJson(redis.get("lastInv:contents:" + player.toString()), ItemStack[].class);
                ItemStack[] armor = Foxtrot.PLAIN_GSON.fromJson(redis.get("lastInv:armorContents:" + player.toString()), ItemStack[].class);

                cleanLoot(contents);
                cleanLoot(armor);

                Foxtrot.getInstance().getServer().getScheduler().runTask(Foxtrot.getInstance(), () -> {
                    sender.getInventory().setContents(contents);
                    sender.getInventory().setArmorContents(armor);
                    sender.updateInventory();

                    sender.sendMessage(ChatColor.GREEN + "Loaded " + FrozenUUIDCache.name(player.getUniqueId()) + "'s last inventory.");
                });

                return null;
            });
        });
    }

    public static void cleanLoot(ItemStack[] stack) {
        for (ItemStack item : stack) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
                ItemMeta meta = item.getItemMeta();

                List<String> lore = item.getItemMeta().getLore();
                lore.remove(ChatColor.DARK_GRAY + "PVP Loot");
                meta.setLore(lore);

                item.setItemMeta(meta);
            }
        }
    }

    public static void recordInventory(Player player) {
        recordInventory(player.getUniqueId(), player.getInventory().getContents(), player.getInventory().getArmorContents());
    }

    public static void recordInventory(UUID player, ItemStack[] contents, ItemStack[] armor) {
        Foxtrot.getInstance().getServer().getScheduler().runTaskAsynchronously(Foxtrot.getInstance(), () -> {
            Foxtrot.getInstance().runRedisCommand((redis) -> {
                redis.set("lastInv:contents:" + player.toString(), Foxtrot.PLAIN_GSON.toJson(contents));
                redis.set("lastInv:armorContents:" + player.toString(), Foxtrot.PLAIN_GSON.toJson(armor));
                return null;
            });
        });
    }

}