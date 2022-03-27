package net.frozenorb.foxtrot.settings.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.settings.menu.SettingsMenu;
import org.bukkit.entity.Player;

@CommandAlias("settings|setting")
public class SettingsCommand extends BaseCommand {

    @Default
    @Description("Open the settings menu")
    public void settingCommand(Player sender) {
        new SettingsMenu().openMenu(sender);
    }
}