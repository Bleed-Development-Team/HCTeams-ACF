package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.frozenorb.foxtrot.util.InventoryUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@CommandAlias("crowbar")
@CommandPermission("op")
public class CrowbarCommand extends BaseCommand {

    @Default
    @Description("Gives you a crowbar.")
    public static void crowbar(Player sender, @Optional Player target) {
        if (sender.getGameMode() != GameMode.CREATIVE) {
            sender.sendMessage(ChatColor.RED + "This command must be ran in creative.");
            return;
        }
        if (target == null) {
            sender.getInventory().setItemInMainHand(InventoryUtils.CROWBAR);
            sender.sendMessage(ChatColor.YELLOW + "Gave you a crowbar.");
            return;
        }
        target.getInventory().addItem(InventoryUtils.CROWBAR);
        target.sendMessage(ChatColor.YELLOW + "You received a crowbar from " + sender.getName() + ".");
        sender.sendMessage(ChatColor.YELLOW + "You gave a crowbar to " + target.getName() + ".");
    }


}