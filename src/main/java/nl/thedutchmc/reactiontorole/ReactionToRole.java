package nl.thedutchmc.reactiontorole;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import nl.thedutchmc.reactiontorole.discordEvents.MessageReactionAddEventListener;
import nl.thedutchmc.reactiontorole.discordEvents.MessageReactionRemoveEventListener;

public class ReactionToRole extends JavaPlugin {

	public static ReactionToRole INSTANCE;
	
	private static JDA jda;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		//Read the configuration file
		ConfigurationHandler configHandler = new ConfigurationHandler();
		configHandler.loadConfig();
		
		if(ConfigurationHandler.discordToken == null) return;
		if(ConfigurationHandler.reactionMessageId == null) return;
		
		//log in to Discord
		try {
			jda = JDABuilder.createDefault(ConfigurationHandler.discordToken).build();
			jda.awaitReady();
		} catch (LoginException e) {
			logWarn("Unable to log in to Discord! Check your config!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		jda.addEventListener(new MessageReactionAddEventListener());
		jda.addEventListener(new MessageReactionRemoveEventListener());
	}
	
	public static void logInfo(String log) {
		Bukkit.getLogger().info("[" + ReactionToRole.INSTANCE.getDescription().getName() + "] " + log);	
	}
	
	public static void logWarn(String log) {
		Bukkit.getLogger().warning("[" + ReactionToRole.INSTANCE.getDescription().getName() + "] " + log);	
	}
	
	public static JDA getJda() {
		return jda;
	}
}
