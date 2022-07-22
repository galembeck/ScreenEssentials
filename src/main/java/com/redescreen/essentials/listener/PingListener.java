package com.redescreen.essentials.listener;

import com.redescreen.essentials.ScreenEssentialsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingListener implements Listener {

    @EventHandler
    public void pingServerList(ServerListPingEvent e) {
        ConfigurationSection serverDescription = ScreenEssentialsPlugin.plugin.getConfig().getConfigurationSection("messages.server-description");

        String ableToPlay = serverDescription.getString("able-to-play").replace("&", "§").replace("^", "➜");
        String maintenance = serverDescription.getString("maintenance").replace("&", "§").replace("^", "➜");

        if (!Bukkit.hasWhitelist()) {
            e.setMotd(ableToPlay);
        } else {
            e.setMotd(maintenance);
        }
    }
}
