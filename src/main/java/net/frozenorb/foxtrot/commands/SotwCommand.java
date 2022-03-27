package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import net.frozenorb.foxtrot.util.TimeUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("sotw")
public class SotwCommand extends BaseCommand {
    @Subcommand("enable")
    @Description("Enables the SOTW timer")
    public static void sotwEnable(Player sender) {
        if (!isSOTWTimer()) {
            sender.sendMessage(ChatColor.RED + "You can't /sotw enable when there is no SOTW timer...");
            return;
        }

        if (CustomTimerCreateCommand.getSotwEnabled().add(sender.getUniqueId())) {
            sender.sendMessage(ChatColor.GREEN + "Successfully disabled your SOTW timer.");
        } else {
            sender.sendMessage(ChatColor.RED + "Your SOTW timer was already disabled...");
        }
    }

    @Subcommand("disable")
    @Description("Disables the SOTW timer")
    @CommandPermission("foxtrot.sotw")
    public static void sotwCancel(CommandSender sender) {
        Long removed = CustomTimerCreateCommand.getCustomTimers().remove("&a&lSOTW");
        if (removed != null && System.currentTimeMillis() < removed) {
            sender.sendMessage(ChatColor.GREEN + "Deactivated the SOTW timer.");
            return;
        }

        sender.sendMessage(ChatColor.RED + "SOTW timer is not active.");
    }

    @Subcommand("stat")
    @Description("Enables the SOTW timer")
    @CommandPermission("foxtrot.sotw")
    public static void sotwStart(CommandSender sender, String time) {
        int seconds = TimeUtils.parseTime(time);
        if (seconds < 0) {
            sender.sendMessage(ChatColor.RED + "Invalid time!");
            return;
        }

        CustomTimerCreateCommand.getCustomTimers().put("&a&lSOTW", System.currentTimeMillis() + (seconds * 1000L));
        sender.sendMessage(ChatColor.GREEN + "Started the SOTW timer for " + time);

        CustomTimerCreateCommand.getSotwEnabled().clear();
    }

    @Subcommand("extend")
    @Description("Extends the SOTW timer")
    @CommandPermission("foxtrot.sotw")
    public static void sotwExtend(CommandSender sender, String time) {
        int seconds;
        try {
            seconds = TimeUtils.parseTime(time);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Invalid time!");
            return;
        }

        if (seconds < 0) {
            sender.sendMessage(ChatColor.RED + "Invalid time!");
            return;
        }

        if (!CustomTimerCreateCommand.getCustomTimers().containsKey("&a&lSOTW")) {
            sender.sendMessage(ChatColor.RED + "There is currently no active SOTW timer.");
            return;
        }

        CustomTimerCreateCommand.getCustomTimers().put("&a&lSOTW", CustomTimerCreateCommand.getCustomTimers().get("&a&lSOTW") + (seconds * 1000L));
        sender.sendMessage(ChatColor.GREEN + "Extended the SOTW timer by " + time);
    }

    public static boolean isSOTWTimer() {
        return CustomTimerCreateCommand.getCustomTimers().containsKey("&a&lSOTW");
    }


}

