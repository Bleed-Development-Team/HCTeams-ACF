package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.util.TimeUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("gopple|opple|goppletime|ooppletime|goppletimer|ooppletimer")
public class GoppleCommand extends BaseCommand {


    @Default
    @Description("Gets the time remaining in the gopple timer")
    public static void gopple(Player sender) {
        if (Foxtrot.getInstance().getOppleMap().isOnCooldown(sender.getUniqueId())) {
            long millisLeft = Foxtrot.getInstance().getOppleMap().getCooldown(sender.getUniqueId()) - System.currentTimeMillis();
            sender.sendMessage(ChatColor.GOLD + "Gopple cooldown: " + ChatColor.WHITE + TimeUtils.formatIntoDetailedString((int) millisLeft / 1000));
        } else {
            sender.sendMessage(ChatColor.RED + "No current gopple cooldown!");
        }
    }

}