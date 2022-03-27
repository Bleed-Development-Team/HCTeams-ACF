package net.frozenorb.foxtrot.monthlycrate;

import net.frozenorb.foxtrot.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class MonthlyListener implements Listener {

    @EventHandler
    public void onMonthlyCrate(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Objects.requireNonNull(event.getPlayer().getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase("&5&lM&e&lY&5&lS&e&lT&5&lE&e&lR&5&lY &5&lC&e&lR&5&lA&e&lT&5&lE")) {
                Bukkit.broadcastMessage(CC.translate("&a"));
                Bukkit.broadcastMessage(CC.translate("&bFrozen &7| &c " + event.getPlayer().getName() + " &7is opening &5&lM&e&lY&5&lS&e&lT&5&lE&e&lR&5&lY &5&lC&e&lR&5&lA&e&lT&5&lE"));
                Bukkit.broadcastMessage(CC.translate("&a"));
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Objects.requireNonNull(event.getPlayer().getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase("&5&lM&e&lY&5&lS&e&lT&5&lE&e&lR&5&lY &5&lC&e&lR&5&lA&e&lT&5&lE")) {
            event.setCancelled(true);
            Bukkit.broadcastMessage(CC.translate("&a"));
            Bukkit.broadcastMessage(CC.translate("&bFrozen &7| &c " + event.getPlayer().getName() + " &7is opening &5&lM&e&lY&5&lS&e&lT&5&lE&e&lR&5&lY &5&lC&e&lR&5&lA&e&lT&5&lE"));
            Bukkit.broadcastMessage(CC.translate("&a"));
        }
    }
}
