package com.redescreen.essentials.runnable;

import com.redescreen.essentials.ScreenEssentialsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class MessageTimer extends BukkitRunnable {

    private List<String> messages;
    private boolean random;
    private int lastMessage;

    public MessageTimer(ScreenEssentialsPlugin plugin) {
        this.random = plugin.getConfig().getBoolean("settings.auto-messaging.random");
        this.messages = plugin.getConfig().getStringList("settings.auto-messaging.messages");
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
            player.sendMessage(message.replace("&", "§").replace("@", "●").replace("^", "➜").replace("*", "✦").replace("~", "➜"));
        }
    }
}
