package net.frozenorb.foxtrot.server.commands.prefix;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.uuid.FrozenUUIDCache;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
@CommandAlias("prefix")
@CommandPermission("op")
public class PrefixListCommand extends BaseCommand {


    @Subcommand("list")
    @Description("List all prefixes")
    public static void prefixList(Player sender) {
        for (Map.Entry<UUID, String> prefixEntry : Foxtrot.getInstance().getChatHandler().getAllCustomPrefixes()) {
            sender.sendMessage(ChatColor.YELLOW + FrozenUUIDCache.name(prefixEntry.getKey()) + ": " + ChatColor.RESET + prefixEntry.getValue());
        }
    }


    @Subcommand("set")
    @Description("Set a prefix")
    public static void prefixSet(CommandSender sender, Player player, String prefix) {//TODO Maybe make this last tihng connected idk how in acf
        if (!prefix.equals("null")) {
            Foxtrot.getInstance().getChatHandler().setCustomPrefix(player.getUniqueId(), ChatColor.translateAlternateColorCodes('&', prefix));
        } else {
            Foxtrot.getInstance().getChatHandler().setCustomPrefix(player.getUniqueId(), null);
        }

        sender.sendMessage(ChatColor.YELLOW + "Prefix updated.");
    }

}