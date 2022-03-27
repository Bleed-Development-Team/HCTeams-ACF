package net.frozenorb.foxtrot.monthlycrate.animations;

import net.frozenorb.foxtrot.util.CC;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Animator {
    public static Map<UUID, UUID> animators = new HashMap() {
    };

    public static void monthly(Player player) {
        if (Animator.isAnimating(player)) {
            player.sendMessage(CC.translate("&cYou are already opening a Mystery Crate.!"));

        }
    }

    public static boolean isAnimating(Player player) {
        return animators.containsValue(player.getUniqueId());
    }
}

