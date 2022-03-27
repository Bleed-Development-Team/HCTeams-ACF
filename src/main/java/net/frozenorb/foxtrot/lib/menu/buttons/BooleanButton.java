package net.frozenorb.foxtrot.lib.menu.buttons;


import net.frozenorb.foxtrot.lib.menu.Button;
import net.frozenorb.foxtrot.util.Callback;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

public class BooleanButton extends Button {
    private final boolean confirm;
    private final Callback<Boolean> callback;

    @Override
    public void clicked(Player player, int i, ClickType clickType) {
        if (this.confirm) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 20.0f, 0.1f);
        } else {
            player.playSound(player.getLocation(), Sound.BLOCK_GRASS_BREAK, 20.0f, 0.1f);
        }
        player.closeInventory();
        this.callback.callback(this.confirm);
    }

    @Override
    public String getName(Player player) {
        return this.confirm ? "\u00a7aConfirm" : "\u00a7cCancel";
    }

    @Override
    public List<String> getDescription(Player player) {
        return new ArrayList<String>();
    }

    @Override
    public byte getDamageValue(Player player) {
        return this.confirm ? (byte)5 : 14;
    }

    @Override
    public Material getMaterial(Player player) {
        return Material.WHITE_WOOL;
    }

    @ConstructorProperties(value={"confirm", "callback"})
    public BooleanButton(boolean confirm, Callback<Boolean> callback) {
        this.confirm = confirm;
        this.callback = callback;
    }
}
