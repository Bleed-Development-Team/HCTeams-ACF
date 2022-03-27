package net.frozenorb.foxtrot.map.stats.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import lombok.Getter;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.team.dtr.DTRBitmask;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
//TODO Work on commands from here if commands arent done
@CommandAlias("chest")
public class ChestCommand extends BaseCommand {

    @Getter private static final Set<UUID> BYPASS = new HashSet<>();

    @Default
    @Description("Opens a chest")
    public static void chest(Player sender) {
        if (!Foxtrot.getInstance().getServerHandler().isVeltKitMap() && !Foxtrot.getInstance().getMapHandler().isKitMap()) {
            sender.sendMessage(ChatColor.RED + "This is a KitMap only command.");
            return;
        }
        
        if (!DTRBitmask.SAFE_ZONE.appliesAt(sender.getLocation())) {
            sender.sendMessage(ChatColor.RED + "You can only do this at spawn.");
            return;
        }

        BYPASS.add(sender.getUniqueId());
        sender.openInventory(sender.getEnderChest());
        BYPASS.remove(sender.getUniqueId());
    }

}
