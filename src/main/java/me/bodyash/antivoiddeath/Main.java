package me.bodyash.antivoiddeath;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.bodyash.antivoiddeath.utils.ConfigUtil;
import me.bodyash.antivoiddeath.utils.PlayerListener;

public class Main extends JavaPlugin {
	
	private ConfigUtil config;
	
	@Override
	public void onEnable() {
		this.config = new ConfigUtil(getDataFolder(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this.config), this);
		Bukkit.getLogger().log(Level.INFO, "[AntiVoidDeath] loaded sucsessfully!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("antivoiddeath")){
			if (args.length == 0){
				sender.sendMessage(config.getChatLogo() + ChatColor.GREEN + " Plugin Writed by Bodyash v. " + this.getDescription().getVersion());
				return true;
			}
			if (args.length == 1 && args[0].equalsIgnoreCase("setspawn") && sender.hasPermission("antivoiddeath.setspawn") && !(sender instanceof ConsoleCommandSender)){
				config.setSpawn((Player)sender);
				return true;
			}
			if (args.length > 1){
				sender.sendMessage(config.getChatLogo() + ChatColor.RED + " Try " + ChatColor.GREEN + "/antivoiddeath setspawn " + ChatColor.RED + "command");
				return true;
			}
		}
		return false;
	}
	
	
}
