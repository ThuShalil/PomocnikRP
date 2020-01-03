package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class HelpCommand extends Command
{
    private List<Command> commands;

    public List<Command> getCommands()
    {
        return commands;
    }

    public void setCommands(List<Command> commands)
    {
        this.commands = commands;
    }

    public HelpCommand(Setting.DiscordInitializer discordInitializer)
    {
        super(discordInitializer, null);

        commands = new ArrayList<>();
        this.commands.add(this);
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(args.length > 1)
        {
            for(Command cmd : commands)
            {
                if(cmd.getName().equals(args[1]) || cmd.getAliases().contains(args[1]))
                {

                    sendMessage(e,String.format("%s\n%s\nalias:%s\n", cmd.getName(),
                            cmd.getDescription(),cmd.getAliases().toString()),true);
                    MessageBuilder messageBuilder = new MessageBuilder();
                    int i = 0;
                    for(String s : cmd.getUsageInstructions())
                    {
                        messageBuilder.append(s+"\n");
                        i++;
                        if(i == 3)
                        {
                            sendMessage(e,messageBuilder.build(),true);
                            messageBuilder = new MessageBuilder();
                            i=0;
                        }
                    }
                    if(!messageBuilder.isEmpty())
                        sendMessage(e,messageBuilder.build(),true);
                    return;
                }
            }
        }

        MessageBuilder messageBuilder = new MessageBuilder();

        for(Command cmd : commands)
        {
            messageBuilder.append(String.format("%s%s - %s\n", cmd.getName(), cmd.getAliases().toString(), cmd.getDescription()));
        }
        sendMessage(e, messageBuilder.build(),true);

    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("help", "pomoc","?","man");
    }

    @Override
    public String getDescription()
    {
        return "Wyświetla spis komend oraz ich opis";
    }

    @Override
    public String getName()
    {
        return "Help";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "help - wyświetla liste komend",
                "help <command> - wyświtla instrukcję użycia komendy"
        );
    }
}
