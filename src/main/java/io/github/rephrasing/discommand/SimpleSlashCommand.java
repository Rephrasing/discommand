package io.github.rephrasing.discommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleSlashCommand implements ISlashCommand {

    private final String name, description;
    private final boolean guildOnly, nsfw;
    private final DefaultMemberPermissions permissions;
    private final List<OptionData> options;

    public SimpleSlashCommand(String name, String description, boolean guildOnly, boolean nsfw) {
        this.name = name;
        this.description = description;
        this.guildOnly = guildOnly;
        this.nsfw = nsfw;
        this.permissions = DefaultMemberPermissions.ENABLED;
        this.options = new ArrayList<>();
    }

    public SimpleSlashCommand(String name, String description, boolean guildOnly, boolean nsfw, OptionData... options) {
        this.name = name;
        this.description = description;
        this.guildOnly = guildOnly;
        this.nsfw = nsfw;
        this.permissions = DefaultMemberPermissions.ENABLED;
        this.options = List.of(options);
    }

    public SimpleSlashCommand(String name, String description, boolean guildOnly, boolean nsfw, DefaultMemberPermissions permissions) {
        this.name = name;
        this.description = description;
        this.guildOnly = guildOnly;
        this.nsfw = nsfw;
        this.permissions = permissions;
        this.options = new ArrayList<>();
    }

    public SimpleSlashCommand(String name, String description, boolean guildOnly, boolean nsfw, DefaultMemberPermissions permissions, OptionData... options) {
        this.name = name;
        this.description = description;
        this.guildOnly = guildOnly;
        this.nsfw = nsfw;
        this.permissions = permissions;
        this.options = List.of(options);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isGuildOnly() {
        return this.guildOnly;
    }

    @Override
    public boolean isNSFW() {
        return this.nsfw;
    }

    @Override
    public DefaultMemberPermissions getPermissions() {
        return this.permissions;
    }

    @Override
    public List<OptionData> getOptions() {
        return this.options;
    }

    @Override
    public abstract void execute(SlashCommandInteractionEvent event);
}