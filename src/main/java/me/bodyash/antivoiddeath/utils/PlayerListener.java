package me.bodyash.antivoiddeath.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {
	
	private ConfigUtil config;
	
    public PlayerListener(ConfigUtil config) {
		this.config = config;
	}

	@EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getLocation().getY() < 0) {
            config.loadSpawn(player);
        }
    }

}
