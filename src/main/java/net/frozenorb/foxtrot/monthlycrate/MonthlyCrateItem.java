package net.frozenorb.foxtrot.monthlycrate;

import net.frozenorb.foxtrot.util.CC;
import net.frozenorb.foxtrot.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MonthlyCrateItem {
    public static ItemStack MONTHLY;
    List<String> lines = new ArrayList();
    public static List<String> getLore() {
        List<String> lines = new ArrayList();
        lines.add(CC.translate("&dUnlocked at frozenhcf.tebex.io"));
        lines.add(CC.translate("&c"));
        lines.add(CC.translate("&e&lEXCLUSIVE"));
        lines.add(CC.translate("&7▹ &a10 Seasonal Keys"));
        lines.add(CC.translate("&7▹ &a15 Seasonal Keys"));
        lines.add(CC.translate("&7▹ &b10 Partner Keys"));
        lines.add(CC.translate("&7▹ &b15 Partner Keys"));
        lines.add(CC.translate("&7▹ &d10 Legacy Keys"));
        lines.add(CC.translate("&7▹ &d15 Legacy Keys"));
        lines.add(CC.translate("&7▹ &315 Partner Packages"));
        lines.add(CC.translate("&7▹ &320 Partner Packages"));
        lines.add(CC.translate("&7▹ &c32 Rage Balls"));
        lines.add(CC.translate("&7▹ &c32 Focus Modes"));
        lines.add(CC.translate("&7▹ &c32 Time Warps"));
        lines.add(CC.translate("&7▹ &c32 Exotic Bones"));
        lines.add(CC.translate("&c"));
        lines.add(CC.translate("&5&lLEGENDARY"));
        lines.add(CC.translate("&7▹ &e2 Mystery Boxes"));
        lines.add(CC.translate("&7▹ &e20 Partner Keys"));
        lines.add(CC.translate("&7▹ &e20 Seasonal Keys"));
        lines.add(CC.translate("&7▹ &e20 Legacy Keys"));
        lines.add(CC.translate("&7▹ &e30 Partner Packages"));
        lines.add(CC.translate("&7▹ &51 Samurai Ability"));
        lines.add(CC.translate("&7▹ &53 Portable Archers"));
        lines.add(CC.translate("&7▹ &53 Grappling Hooks"));
        lines.add(CC.translate("&7▹ &53 Ninja Abilities"));
        lines.add(CC.translate("&7▹ &590 Day Pyro Rank"));
        lines.add(CC.translate("&7▹ &590 Day Highroller Rank"));


        return lines;

    }

    static {
        MONTHLY = ItemBuilder.of(Material.ENDER_CHEST).name(CC.translate("&5&lM&e&lY&5&lS&e&lT&5&lE&e&lR&5&lY &5&lC&e&lR&5&lA&e&lT&5&lE")).setLore(getLore()).build();
    }
}
