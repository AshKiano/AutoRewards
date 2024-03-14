package com.ashkiano.autorewards;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class AutoRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(this), this);
        startRewardCheckTask();

        Metrics metrics = new Metrics(this, 21249);
        this.getLogger().info("Thank you for using the AutoRewards plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    private void startRewardCheckTask() {
        long intervalTicks = 20L * 60; // 20 ticks per second * 60 for a 1-minute interval
        new BukkitRunnable() {
            @Override
            public void run() {
                RewardManager.checkAndRewardOnlinePlayers(AutoRewards.this);
            }
        }.runTaskTimer(this, intervalTicks, intervalTicks);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}