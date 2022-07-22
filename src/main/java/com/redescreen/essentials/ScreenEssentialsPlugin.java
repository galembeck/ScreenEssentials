package com.redescreen.essentials;

import com.redescreen.essentials.listener.PingListener;
import com.redescreen.essentials.runnable.InformativeMessagesTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ScreenEssentialsPlugin extends JavaPlugin {

    public static ScreenEssentialsPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        registerEvents();

        if (!new File(getDataFolder(), "config.yml").exists())
            saveDefaultConfig();

        int interval = getConfig().getInt("settings.auto-messaging.interval") * 20;
        new InformativeMessagesTask(this).runTaskTimer(this, 0, interval);
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PingListener(), this);
    }
}
