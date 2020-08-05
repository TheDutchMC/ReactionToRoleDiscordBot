package nl.thedutchmc.reactiontorole.discordEvents;

import com.vdurmont.emoji.EmojiParser;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.thedutchmc.reactiontorole.ConfigurationHandler;
import nl.thedutchmc.reactiontorole.ReactionToRole;

public class MessageReactionRemoveEventListener extends ListenerAdapter {
	
	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
		
		if(!event.getMessageId().contentEquals(ConfigurationHandler.reactionMessageId)) return;
		
		boolean hasPermission = false;
		for(Role r :event.getGuild().getMemberById(ReactionToRole.getJda().getSelfUser().getId()).getRoles()) {
			if(r.getPermissions().contains(Permission.MANAGE_ROLES) || r.getPermissions().contains(Permission.ADMINISTRATOR)) hasPermission = true;
		}
		
		if(!hasPermission) {
			ReactionToRole.logWarn("This bot does not have the manage roles permission, and will not function!");
			return;
		}
		
		String emojiAsText = "";
		if(event.getReactionEmote().isEmote()) {
			emojiAsText = event.getReactionEmote().getEmote().getAsMention();
		}
		
		if(event.getReactionEmote().isEmoji()) {
			emojiAsText = EmojiParser.parseToAliases(event.getReactionEmote().getEmoji());
		}
		
		if(ConfigurationHandler.reactionsWithRoles.containsKey(emojiAsText)) {
			Role r = ReactionToRole.getJda().getRoleById(ConfigurationHandler.reactionsWithRoles.get(emojiAsText));
			
			event.getGuild().retrieveMemberById(event.getUserId()).queue(m -> {
				
				if(m.getRoles() != null && m.getRoles().contains(r)) {
					event.getGuild().removeRoleFromMember(m, r).queue();
				}
			});
		}
	}
}
