package net.frozenorb.foxtrot.lib.menu.menus;

import net.frozenorb.foxtrot.lib.menu.Button;
import net.frozenorb.foxtrot.lib.menu.Menu;
import net.frozenorb.foxtrot.lib.menu.buttons.BooleanButton;
import net.frozenorb.foxtrot.util.Callback;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.beans.ConstructorProperties;
import java.util.HashMap;
import java.util.Map;

public class ConfirmMenu extends Menu {
    private final String title;
    private final Callback<Boolean> response;

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<Integer, Button>();
        for (int i = 0; i < 9; ++i) {
            if (i == 3) {
                buttons.put(i, new BooleanButton(true, this.response));
                continue;
            }
            if (i == 5) {
                buttons.put(i, new BooleanButton(false, this.response));
                continue;
            }
            buttons.put(i, Button.placeholder(Material.BLACK_STAINED_GLASS_PANE, (byte)14, new String[0]));
        }
        return buttons;
    }

    @Override
    public String getTitle(Player player) {
        return this.title;
    }

    @ConstructorProperties(value={"title", "response"})
    public ConfirmMenu(String title, Callback<Boolean> response) {
        this.title = title;
        this.response = response;
    }
}
