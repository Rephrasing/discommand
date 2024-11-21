package io.github.rephrasing.discommand;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

import java.util.*;

public final class SlashCommandManager {

    private static final Map<Guild, List<ISlashCommand>> commandsMap = new HashMap<>();

    public static void registerCommands(Guild guild, Collection<ISlashCommand> commands) {
        commandsMap.putIfAbsent(guild, new ArrayList<>());
        for (ISlashCommand cmd : commands) {
            if (findCommand(guild, cmd.getName()).isPresent()) throw new IllegalArgumentException("Cannot register command \"/" + cmd.getName() + "\" because it was already registered");
            commandsMap.get(guild).add(cmd);
        }
        guild.updateCommands().addCommands(commands.stream().map(ISlashCommand::raw).toList()).queue();
    }

    public static void registerSlashCommandListener(JDA api) {
        api.addEventListener(new SlashListener());
    }

    protected static <T extends ISlashCommand> Optional<T> findCommand(Guild guild, String commandName) {
        List<ISlashCommand> guildCmds = commandsMap.get(guild);
        if (guildCmds.isEmpty()) return Optional.empty();
        return guildCmds.stream().filter(cmd -> cmd.getName().equalsIgnoreCase(commandName)).map(cmd -> (T) cmd).findAny();
    }
}