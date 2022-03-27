package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.Foxtrot;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("associate|ass")
@CommandPermission("op")
public class AssociateAccountsCommand extends BaseCommand {



    @Default
    @Description("Associate two accounts")
    public static void associate(Player sender, Player player, Player playertwo) {
        if(Foxtrot.getInstance().getWhitelistedIPMap().contains(player.getUniqueId())) {
            UUID other = Foxtrot.getInstance().getWhitelistedIPMap().get(player.getUniqueId());
            Foxtrot.getInstance().getWhitelistedIPMap().add(playertwo.getUniqueId(), other);
        } else if( Foxtrot.getInstance().getWhitelistedIPMap().contains(playertwo.getUniqueId())) {
            UUID other = Foxtrot.getInstance().getWhitelistedIPMap().get(playertwo.getUniqueId());
            Foxtrot.getInstance().getWhitelistedIPMap().add(player.getUniqueId(), other);
        } else {
            if( Foxtrot.getInstance().getWhitelistedIPMap().containsValue(player.getUniqueId())) {
                Foxtrot.getInstance().getWhitelistedIPMap().add(playertwo.getUniqueId(), player.getUniqueId());
            } else if( Foxtrot.getInstance().getWhitelistedIPMap().containsValue(playertwo.getUniqueId())) {
                Foxtrot.getInstance().getWhitelistedIPMap().add(player.getUniqueId(), playertwo.getUniqueId());
            } else {
                Foxtrot.getInstance().getWhitelistedIPMap().add(playertwo.getUniqueId(), player.getUniqueId());
            }
        }
        sender.sendMessage(ChatColor.GREEN + "You have successfully associated these accounts");
    }



}
