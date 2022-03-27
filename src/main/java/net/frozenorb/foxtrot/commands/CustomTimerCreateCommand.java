package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.google.common.collect.Sets;
import lombok.Getter;
import net.frozenorb.foxtrot.util.TimeUtils;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@CommandAlias("customtimer")
@CommandPermission("foxtrot.customtimer")
public class CustomTimerCreateCommand extends BaseCommand {

    @Getter private static Map<String, Long> customTimers = new HashMap<>();
    @Getter private static Set<UUID> sotwEnabled = Sets.newHashSet();


    @Subcommand("create")
    @Description("Create a custom timer")
    public static void customTimerCreate(CommandSender sender, String time, String title) {
        int fakeTime = TimeUtils.parseTime(time);
        if (fakeTime == 0) {
            customTimers.remove(title);
        } else {
            customTimers.put(String.valueOf(title), System.currentTimeMillis() + (fakeTime * 1000L));
        }
    }

    public static boolean isSOTWTimer() {
        return customTimers.containsKey("&a&lSOTW");
    }

    public static boolean hasSOTWEnabled(UUID uuid) {
        return sotwEnabled.contains(uuid);
    }
}