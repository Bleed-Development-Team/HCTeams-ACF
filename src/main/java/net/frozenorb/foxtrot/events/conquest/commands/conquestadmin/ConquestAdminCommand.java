package net.frozenorb.foxtrot.events.conquest.commands.conquestadmin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.events.conquest.ConquestHandler;
import net.frozenorb.foxtrot.events.conquest.game.ConquestGame;
import net.frozenorb.foxtrot.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandAlias("conquestadmin")
@CommandPermission("op")
public class ConquestAdminCommand extends BaseCommand {


    @Subcommand("setscore")
    @Description("Sets the score of a team")
    public static void conquestAdminSetScore(CommandSender sender, Team team, int score) {
        ConquestGame game = Foxtrot.getInstance().getConquestHandler().getGame();

        if (game == null) {
            sender.sendMessage(ChatColor.RED + "Conquest is not active.");
            return;
        }

        game.getTeamPoints().put(team.getUniqueId(), score);
        sender.sendMessage(ConquestHandler.PREFIX + " " + ChatColor.GOLD + "Updated the score for " + team.getName() + ChatColor.GOLD + ".");
    }

    @Subcommand("start")
    @Description("Starts the conquest game")
    public static void conquestAdminStart(CommandSender sender) {
        ConquestGame game = Foxtrot.getInstance().getConquestHandler().getGame();

        if (game != null) {
            sender.sendMessage(ChatColor.RED + "Conquest is already active.");
            return;
        }

        new ConquestGame();
    }


    @Subcommand("stop")
    @Description("Stops the conquest game")
    public static void conquestAdminStop(CommandSender sender) {
        ConquestGame game = Foxtrot.getInstance().getConquestHandler().getGame();

        if (game == null) {
            sender.sendMessage(ChatColor.RED + "Conquest is not active.");
            return;
        }

        game.endGame(null);
    }

}