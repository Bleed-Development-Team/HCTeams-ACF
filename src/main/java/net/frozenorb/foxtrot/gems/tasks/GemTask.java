package net.frozenorb.foxtrot.gems.tasks;

import net.frozenorb.foxtrot.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GemTask implements Runnable{

    public GemTask(){

    }

    @Override
    public void run(){
        for (Player player : Bukkit.getOnlinePlayers()){

            player.sendMessage(CC.translate("&aYou have received 1 gem for staying online!"));
        }
    }
}
