package net.frozenorb.foxtrot.gems.listener;

import net.frozenorb.foxtrot.Foxtrot;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class GemListener implements Listener {

    private final boolean testing = false;

    public GemListener() {
        Bukkit.getPluginManager().registerEvents(this, Foxtrot.getInstance());
    }





}
