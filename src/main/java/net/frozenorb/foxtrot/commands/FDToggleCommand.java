package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.Foxtrot;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("ToggleFD|FD|ToggleFoundDiamonds")
public class FDToggleCommand extends BaseCommand {

    @Default
    @Description("Toggles the Found Diamonds feature.")
    public static void fdToggle( Player sender) {
        boolean val = !Foxtrot.getInstance().getToggleFoundDiamondsMap().isFoundDiamondToggled(sender.getUniqueId());

        sender.sendMessage(ChatColor.YELLOW + "You are now " + (!val ? ChatColor.RED + "unable" : ChatColor.GREEN + "able") + ChatColor.YELLOW + " to see Found Diamonds messages!");
        Foxtrot.getInstance().getToggleFoundDiamondsMap().setFoundDiamondToggled(sender.getUniqueId(), val);
    }

}