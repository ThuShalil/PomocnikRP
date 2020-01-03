package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TestCommand extends Command
{

    public TestCommand(Setting.DiscordInitializer discordInitializer, HelpCommand command)
    {
        super(discordInitializer, command);
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        e.getTextChannel().sendMessage(new MessageBuilder().append(e.getAuthor()).append("napisałeś: " + e.getMessage().getContentRaw()).build()).queue();
        sendMessage(e,e.getAuthor().getId(),true);

    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("test","test1");
    }

    @Override
    public String getDescription()
    {
        return "test command";
    }

    @Override
    public String getName()
    {
        return "test";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "test command help 1",
                "test command help 2"
        );
    }
}
