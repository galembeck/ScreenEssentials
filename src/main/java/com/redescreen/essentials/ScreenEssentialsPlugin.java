package com.redescreen.essentials;

import com.redescreen.essentials.runnable.MessageTimer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ScreenEssentialsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!new File(getDataFolder(), "config.yml").exists())
            saveDefaultConfig();

        int interval = getConfig().getInt("settings.auto-messaging.interval") * 20;

        new MessageTimer(this).runTaskTimer(this, 0, interval);
    }
}
