package net.frozenorb.foxtrot.server.commands.betrayer;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.util.Betrayer;
import net.frozenorb.foxtrot.util.UUIDUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

import static net.frozenorb.foxtrot.uuid.FrozenUUIDCache.name;
import static org.bukkit.ChatColor.*;

@CommandAlias("betrayer")
@CommandPermission("op")
public class BetrayerCommand extends BaseCommand {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yy HH:mm:ss z");

    @Default
    @Description("Shows the current status of the Betrayer")
    public static void betrayer(Player sender) {
        String[] msges = {
                "§c/betrayer list - Shows all betrayers.",
                "§c/betrayer add <player> <reason> - Add a betrayer for a reason.",
                "§c/betrayer remove <player> - Remove a betrayer."};

        sender.sendMessage(msges);
    }

    @Subcommand("info")
    public static void betrayerList(Player sender, Player player) {
        Betrayer betrayer = Foxtrot.getInstance().getServerHandler().getBetrayer(player.getUniqueId());

        if (betrayer != null) {
            sender.sendMessage(GOLD + "=====" + WHITE + " Betrayer Information " + GOLD + "=====");
            sender.sendMessage(GOLD + "Betrayer: " + LIGHT_PURPLE + name(betrayer.getUuid()) + GOLD + " Added by: " + LIGHT_PURPLE + name(betrayer.getAddedBy()));
            sender.sendMessage(GOLD + "When: " + LIGHT_PURPLE + sdf.format(new Date(betrayer.getTime())) + GOLD + " Why: " + LIGHT_PURPLE + betrayer.getReason());
        } else {
            sender.sendMessage(RED + "Could not find betrayer info for " + YELLOW + name(player.getUniqueId()) + RED + "!");
        }
    }


    @Subcommand("remove")
    public static void betrayerRemove(Player sender, Player player) {
        Betrayer betrayer = Foxtrot.getInstance().getServerHandler().getBetrayer(player.getUniqueId());
        if (betrayer != null) {
            Foxtrot.getInstance().getServerHandler().getBetrayers().remove(betrayer);
            Foxtrot.getInstance().getServerHandler().save();

            sender.sendMessage(ChatColor.GREEN + "Removed " + UUIDUtils.name(player.getUniqueId()) + "'s betrayer tag.");
        } else {
            sender.sendMessage(ChatColor.RED + UUIDUtils.name(player.getUniqueId()) + " isn't a betrayer.");
        }
    }


}