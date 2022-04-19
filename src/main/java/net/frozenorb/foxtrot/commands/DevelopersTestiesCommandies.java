package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.bukkit.entity.Player;

@CommandAlias("devtest")
public class DevelopersTestiesCommandies extends BaseCommand {
    @Default
    public static void test(Player sender) {
        if (sender.getName().equals("Nopox")) {
            sender.setAI(false);

        }
    }
}
