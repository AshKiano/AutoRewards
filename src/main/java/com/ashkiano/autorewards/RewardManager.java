package com.ashkiano.autorewards;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RewardManager {

    public static void checkAndRewardPlayer(Player player, AutoRewards plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Získání času posledního přihlášení a intervalu odměny
                long lastClaimed = plugin.getConfig().getLong("rewards." + player.getUniqueId() + ".lastClaimed", 0);
                long rewardInterval = plugin.getConfig().getLong("reward-interval-minutes", 60) * 60000; // 1 minuta = 60000 ms
                long now = System.currentTimeMillis();

                if (now - lastClaimed >= rewardInterval) {
                    String rewardCommand = plugin.getConfig().getString("reward-command").replace("%player%", player.getName());
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), rewardCommand);

                    // Aktualizace času posledního nároku
                    plugin.getConfig().set("rewards." + player.getUniqueId() + ".lastClaimed", now);
                    plugin.saveConfig();
                }
            }
        }.runTaskLater(plugin, 20L * 60); // Spustí se 1 minutu po přihlášení
    }

    public static void checkAndRewardOnlinePlayers(AutoRewards plugin) {
        plugin.getServer().getOnlinePlayers().forEach(player -> checkAndRewardPlayer(player, plugin));
    }
}
