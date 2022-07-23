package com.redescreen.essentials.runnable;

import com.redescreen.essentials.ScreenEssentialsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class InformativeMessagesTask extends BukkitRunnable {

    private List<String> messages;
    private boolean random;
    private int lastMessage;

    private boolean enableSounds;
//    private String soundType;

    public InformativeMessagesTask(ScreenEssentialsPlugin plugin) {
        this.random = plugin.getConfig().getBoolean("settings.auto-messaging.random");

        this.enableSounds = plugin.getConfig().getBoolean("settings.auto-messaging.sound-settings.enable-sounds");
//        this.soundType = plugin.getConfig().getString("settings.auto-messaging.sound-settings.sound-type");

        this.messages = plugin.getConfig().getStringList("messages.informative-messages");
    }

    @Override
    public void run() {
        String message = "";

        if (!random) {
            try {
                message = messages.get(lastMessage + 1);
                lastMessage++;
            } catch (ArrayIndexOutOfBoundsException e) {
                message = messages.get(0);
                lastMessage = 0;
            }
        } else {
            Random random = new Random();
            int nextMessage = random.nextInt(messages.size());
            while (nextMessage == lastMessage) {
                nextMessage = random.nextInt(messages.size());
            }

            message = messages.get(nextMessage);
            lastMessage = nextMessage;
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message.replace("&", "§").replace("@", "●").replace("^", "➜").replace("*", "✦"));
            if (enableSounds) {
                // TODO: Add a "if" clause to be able to use different types of sounds... (VILLAGER_HAGGLE e LEVEL_UP)
                player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 1, 1);
//              } else {
//                  player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 1, 1);
//              }
            }
        }
    }
}
