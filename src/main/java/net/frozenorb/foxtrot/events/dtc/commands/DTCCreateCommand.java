package net.frozenorb.foxtrot.events.dtc.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.events.dtc.DTC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("dtc")
@CommandPermission("op")
public class DTCCreateCommand extends BaseCommand {
    @Default
    @Description("Create a new DTC event")
    public static void kothCreate(Player sender, String koth) {
        new DTC(koth, sender.getLocation());
        sender.sendMessage(ChatColor.GRAY + "Created a DTC named " + koth + ".");
    }

}
