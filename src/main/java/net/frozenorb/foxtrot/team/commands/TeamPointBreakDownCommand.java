package net.frozenorb.foxtrot.team.commands;

import co.aikar.commands.BaseCommand;
import me.vaperion.blade.annotation.Command;
import me.vaperion.blade.annotation.Optional;
import me.vaperion.blade.annotation.Permission;
import me.vaperion.blade.annotation.Sender;
import net.frozenorb.foxtrot.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamPointBreakDownCommand extends BaseCommand {

	@Command(value = { "team pointbr", "team pbr", "t pointbr", "t pbr" })//TODO Add this to the team command
	@Permission(value = "op")
	public static void teamPointBreakDown(@Sender Player player, @Optional("self") final Team team) {
		player.sendMessage(ChatColor.GOLD + "Point Breakdown of " + team.getName());

		for (String info : team.getPointBreakDown()) {
			player.sendMessage(info);
		}
	}

}
