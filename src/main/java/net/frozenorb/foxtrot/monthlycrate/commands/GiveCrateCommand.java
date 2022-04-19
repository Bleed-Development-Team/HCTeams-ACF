package net.frozenorb.foxtrot.monthlycrate.commands;

import net.frozenorb.foxtrot.monthlycrate.MonthlyCrateItem;
import net.frozenorb.foxtrot.util.CC;
import org.bukkit.entity.Player;

public class GiveCrateCommand {
    //@Command(value = "givecrate")
    //Permission(value = "monthlycrate.givecrate")
    public static void giveCrate(Player sender) {
        sender.getInventory().addItem(MonthlyCrateItem.MONTHLY);
        sender.sendMessage(CC.translate("&aYou have been given a &6Monthly Crate&a!"));
    }
}
