package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import com.thushalil.pomocnikrp.services.HeroService;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InventoryManager extends Command
{
    private final HeroService heroService;

    public InventoryManager(Setting.DiscordInitializer discordInitializer, HelpCommand command, HeroService heroService)
    {
        super(discordInitializer, command);
        this.heroService = heroService;
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {

    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("inventory","inv","i");
    }

    @Override
    public String getDescription()
    {
        return "Zarządzanie plecakiem gracza";
    }

    @Override
    public String getName()
    {
        return "InventoryManager";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "..."
        );
    }
}
