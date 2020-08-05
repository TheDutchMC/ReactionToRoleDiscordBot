# ReactionToRoleDiscordBot
Spigot 1.13+, even though it has nothing to do with Minecraft.

This plugin/bot gives the user a role when they react with an emoji to a specified message.

### Why in the world is this  a Spigot plugin?
So, a lot of people have a Minecraft server, but no seperate server available for hosting a Discord bot. This way, the bot just piggybacks off of your Minecraft server :)

## Installation
1. Download the latest jar from [here](https://github.com/TheDutchMC/ReactionToRoleDiscordBot/releases)
2. Drop the downloaded jar in your plugins folder
3. Start your server, it will give errors, this is normal.
4. Shut down your server, and navigate to plugins/ReactionToRole and open config.yml
5. Enter your Discord bot token, this must be within double quotes ("), how to get a Discord bot token is explained [here](https://discordpy.readthedocs.io/en/latest/discord.html)
6. Post your message you want the bot to listen for reactions for.
7. Copy the message ID of your message into the config, into the field ``reactionMessageId``, how to get the ID is explained [here](https://support.discord.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID-)
8. Enter the emote and role ID for every "set" you want to be listened for. In the format: ``- ":emojiname:@roleId"``. Note: not all emojis are named the same as in Discord! E.g ``:thumbsup:`` is ``:+1:`` !
9. Save the config file and start your server.

## Building
You can clone this repository to build it yourself if you wish to do so.

#### Build a test Jar
``gradlew testJar``

#### Build a release jar
``gradlew releaseJar``
