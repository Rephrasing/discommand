package io.github.rephrasing.discommand;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

import java.util.*;

public final class SlashCommandManager {

    private static final Map<Guild, List<SimpleSlashCommand>> commandsMap = new HashMap<>();

    public static void registerCommands(Guild guild, Collection<SimpleSlashCommand> commands) {
        commandsMap.putIfAbsent(guild, new ArrayList<>());
        commandsMap.get(guild).addAll(commands);
        guild.updateCommands().addCommands(commands.stream().map(ISlashCommand::raw).toList()).queue();
    }

    public static void registerSlashCommandListener(JDA api) {
        api.addEventListener(new SlashListener());
    }

    protected static Optional<SimpleSlashCommand> findCommand(Guild guild, String commandName) {
        List<SimpleSlashCommand> guildCmds = commandsMap.get(guild);
        if (guildCmds.isEmpty()) return Optional.empty();
        return guildCmds.stream().filter(cmd -> cmd.getName().equalsIgnoreCase(commandName)).findAny();
    }
}