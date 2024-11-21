package io.github.rephrasing.discommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public final class SlashListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        SlashCommandManager.findCommand(event.getGuild(), event.getName()).ifPresent(cmd -> {
            cmd.execute(event);
        });
    }
}
