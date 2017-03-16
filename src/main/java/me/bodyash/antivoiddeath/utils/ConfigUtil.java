package me.bodyash.antivoiddeath.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.bodyash.antivoiddeath.Main;

public class ConfigUtil {

	// files
	private File configFile;
	private YamlConfiguration config;

	private String consoleLogo = "[AntiVoidDeath] ";
	private String chatLogo = "&a[&bAntiVoidDeath&a]";
	private String teleportMessage = "&bDon't die, please!";
	private Main main;

	public ConfigUtil(File pluginFolder, Main main) {
		this.configFile = new File(pluginFolder, "config.yml");
		this.config = YamlConfiguration.loadConfiguration(configFile);
		this.main = main;
		this.startup();
	}

	private void startup() {
		if (!this.configFile.exists()) {
			Bukkit.getLogger().log(Level.WARNING, this.consoleLogo + "... Starting config creation ...");
			this.createConfig();
			loadconfig();
		} else {
			loadconfig();
		}
	}

	private void loadconfig() {
		chatLogo = this.config.getString("chatlogo");
		teleportMessage = this.config.getString("teleportmessage");
	}

	private void createConfig() {
		this.config.options()
				.header("ColorCodes supported!");
		this.config.set("chatlogo", this.chatLogo);
		this.config.set("teleportmessage", this.teleportMessage);

		try {
			this.config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void setSpawn(Player player) {
        this.config.set("spawn.world", player.getLocation().getWorld().getName());
        this.config.set("spawn.x", player.getLocation().getX());
        this.config.set("spawn.y", player.getLocation().getY());
        this.config.set("spawn.z", player.getLocation().getZ());
        this.config.set("spawn.yaw", Float.valueOf(player.getLocation().getYaw()));
        this.config.set("spawn.pitchh", Float.valueOf(player.getLocation().getPitch()));
		try {
			this.config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
        player.sendMessage(this.getChatLogo() + " Spawn " + ChatColor.GREEN + player.getLocation().getWorld().getName() + ", " + player.getLocation().getX() + ", " + player.getLocation().getZ() + ", " + player.getLocation().getYaw() + ", " + player.getLocation().getPitch());
    }

    public void loadSpawn(Player player) {
        World w = main.getServer().getWorld(this.config.getString("spawn.world"));
        double x = this.config.getDouble("spawn.x");
        double y = this.config.getDouble("spawn.y");
        double z = this.config.getDouble("spawn.z");
        float yaw = (float)this.config.getDouble("spawn.yaw");
        float pitch = (float)config.getDouble("spawn.pitch");
        Location spawn = new Location(w, x, y, z, yaw, pitch);
        player.teleport(spawn);
        player.sendMessage(this.getChatLogo() + " " + this.getTeleportMessage());
    }

	public String getChatLogo() {
		return this.colorize(this.chatLogo);
	}
	
	public String getTeleportMessage(){
		return this.colorize(this.teleportMessage);
	}

	public String colorize(String s) {
		if (s == null)
			return "";
		return s.replaceAll("&([0-9a-flknro])", "\u00A7$1");
	}


}
