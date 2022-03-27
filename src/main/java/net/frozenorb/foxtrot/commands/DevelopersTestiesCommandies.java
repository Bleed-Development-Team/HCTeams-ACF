package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import me.vaperion.blade.annotation.Command;
import me.vaperion.blade.annotation.Sender;
import org.bukkit.entity.Player;

public class DevelopersTestiesCommandies extends BaseCommand {
    @Command(value = "DevelopersTestiesCommandies")
    public static void test(@Sender Player sender) {
        if (sender.getName().equals("Nopox")) {
            sender.setAI(false);

        }
    }
}
