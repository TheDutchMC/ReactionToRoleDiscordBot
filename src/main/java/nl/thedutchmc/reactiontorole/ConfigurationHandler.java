package nl.thedutchmc.reactiontorole;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHandler {
	
	public static String discordToken, reactionMessageId;
	public static HashMap<String, String> reactionsWithRoles = new HashMap<>();
	
	private File file;
	private FileConfiguration config;
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void loadConfig() {
		file = new File(ReactionToRole.INSTANCE.getDataFolder(), "config.yml");
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			ReactionToRole.INSTANCE.saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		
		try {
			config.load(file);
			readConfig();
		} catch (InvalidConfigurationException e) {
			ReactionToRole.logWarn("Invalid config.yml!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readConfig() {
		discordToken = this.getConfig().getString("discordToken");
		reactionMessageId = this.getConfig().getString("reactionMessageId");
		
		List<String> reactionToRole = (List<String>) this.getConfig().getList("reactionToRole");
		
		for(String str : reactionToRole) {
			String[] parts = str.split("@");
			
			reactionsWithRoles.put(parts[0], parts[1]);
		}
		
		if(discordToken == null || discordToken == "") {
			ReactionToRole.logWarn("Token not provided! This plugin will not work!");
		}
		
		if(reactionMessageId == null || reactionMessageId == "") {
			ReactionToRole.logWarn("Message ID not provided! This plugin will not wokr!");
		}
	}
}
