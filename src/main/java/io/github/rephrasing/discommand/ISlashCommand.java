package io.github.rephrasing.discommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

public interface ISlashCommand {

    String getName();
    String getDescription();
    boolean isGuildOnly();
    boolean isNSFW();
    DefaultMemberPermissions getPermissions();
    List<OptionData> getOptions();

    void execute(SlashCommandInteractionEvent event);

    default SlashCommandData raw() {
        if (getName() == null || getDescription() == null) {
            throw new IllegalArgumentException("Cannot create a SlashCommandData without a name or description set");
        }
        SlashCommandData data = Commands.slash(getName(), getDescription())
                .setGuildOnly(isGuildOnly())
                .setNSFW(isNSFW())
                .setDefaultPermissions(getPermissions() == null ? DefaultMemberPermissions.ENABLED : getPermissions());
        if (getOptions() == null) {
            return data;
        }
        if (getOptions().isEmpty()) {
            return data;
        }
        data.addOptions(getOptions());
        return data;
    }
}
