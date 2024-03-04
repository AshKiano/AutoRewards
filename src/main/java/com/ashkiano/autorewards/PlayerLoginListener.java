package com.ashkiano.autorewards;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {
    private final AutoRewards plugin;

    public PlayerLoginListener(AutoRewards plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        RewardManager.checkAndRewardPlayer(event.getPlayer(), plugin);
    }
}