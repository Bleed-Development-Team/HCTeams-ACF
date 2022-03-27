package net.frozenorb.foxtrot.team.menu;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.frozenorb.foxtrot.lib.menu.Button;
import net.frozenorb.foxtrot.lib.menu.Menu;
import net.frozenorb.foxtrot.team.Team;
import net.frozenorb.foxtrot.team.menu.button.ChangePromotionStatusButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class PromoteMembersMenu extends Menu {

    @NonNull @Getter Team team;

    @Override
    public String getTitle(Player player) {
        return "Members of " + team.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();
        int index = 0;

        for (UUID uuid : team.getMembers()) {
            if (!team.isOwner(uuid) && !team.isCoLeader(uuid)) {
                buttons.put(index, new ChangePromotionStatusButton(uuid, team, true));//Can someone else fix this I really cba
                index++;
            }
        }

        return buttons;
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }

}
