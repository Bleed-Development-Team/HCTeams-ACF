package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.Foxtrot;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("spawn")
public class SpawnCommand extends BaseCommand {


    @Default
    @Description("Teleports you to the spawn")
    public static void spawn(Player sender) {
        if (sender.hasPermission("foxtrot.spawn")) {
            sender.teleport(Foxtrot.getInstance().getServerHandler().getSpawnLocation());
        } else {
            // Make this pretty.
            String serverName = Foxtrot.getInstance().getServerHandler().getServerName();

            sender.sendMessage(ChatColor.RED + serverName + " does not have a spawn command! You must walk there!");
            sender.sendMessage(ChatColor.RED + "Spawn is located at 0,0.");
        }
    }

}
