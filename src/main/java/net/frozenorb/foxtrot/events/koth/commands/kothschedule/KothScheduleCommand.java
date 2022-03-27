package net.frozenorb.foxtrot.events.koth.commands.kothschedule;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import me.vaperion.blade.annotation.Command;
import me.vaperion.blade.annotation.Permission;
import me.vaperion.blade.annotation.Sender;
import net.frozenorb.foxtrot.Foxtrot;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

@CommandAlias("kothschedule")
@CommandPermission("foxtrot.koth.schedule")
public class KothScheduleCommand extends BaseCommand {


    @Subcommand("disable")
    @Description("Disable the KOTH schedule")
    public static void kothScheduleDisable(CommandSender sender) {
        Foxtrot.getInstance().getEventHandler().setScheduleEnabled(false);

        sender.sendMessage(ChatColor.YELLOW + "The KOTH schedule has been " + ChatColor.RED + "disabled" + ChatColor.YELLOW + ".");
    }


    @Subcommand("reload")
    @Description("Reload the KOTH schedule")
    public static void kothScheduleReload(Player sender) {
        Foxtrot.getInstance().getEventHandler().loadSchedules();
        sender.sendMessage(ChatColor.GOLD + "[KingOfTheHill] " + ChatColor.YELLOW + "Reloaded the KOTH schedule.");
    }

    @Subcommand("regenerate|regen")
    @Description("Regenerate the KOTH schedule")
    public static void kothScheduleEnable(CommandSender sender) {
        File kothSchedule = new File(Foxtrot.getInstance().getDataFolder(), "eventSchedule.json");

        if (kothSchedule.delete()) {
            Foxtrot.getInstance().getEventHandler().loadSchedules();

            sender.sendMessage(ChatColor.YELLOW + "The event schedule has been regenerated.");
        } else {
            sender.sendMessage(ChatColor.RED + "Couldn't delete event schedule file.");
        }
    }

    @Command(value = {"KOTHSchedule debug"})
    @Permission(value = "op")
    public static void kothScheduleDebug(@Sender CommandSender sender) {
        Foxtrot.getInstance().getEventHandler().fillSchedule();
        sender.sendMessage(ChatColor.GREEN + "The event schedule has been filled.");
    }

}
