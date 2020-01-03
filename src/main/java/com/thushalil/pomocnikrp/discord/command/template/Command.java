package com.thushalil.pomocnikrp.discord.command.template;

import com.thushalil.pomocnikrp.discord.command.HelpCommand;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public abstract class Command extends ListenerAdapter
{
    public abstract void onCommand(MessageReceivedEvent e, String[] args);
    public abstract List<String> getAliases();
    public abstract String getDescription();
    public abstract String getName();
    public abstract List<String> getUsageInstructions();
    private boolean active = true;

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public Command(Setting.DiscordInitializer discordInitializer, HelpCommand command)
    {
        System.out.println(String.format("Command [%s] loaded", getName()));
        discordInitializer.getJda().addEventListener(this);
        System.out.println(String.format("Listener for [%s] added", getName()));
        if(command != null)
            command.getCommands().add(this);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {
        if (e.getAuthor().isBot())
            return;
        if (containsCommand(e.getMessage()) && isActive())
            onCommand(e, commandArgs(e.getMessage()));
    }

    protected boolean containsCommand(Message message)
    {
        String command = commandArgs(message)[0];
        if(command.length() > 0)
        {
            char prefix = command.charAt(0);
            command = command.substring(1);

            if (prefix == Setting.commandPrefix)
                return getAliases().contains(command);
            else
                return false;
        }
        else
        {
            return false;
        }
    }

    protected boolean isDigit(String value)
    {
        for (char c : value.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    protected String[] commandArgs(Message message)
    {
        return commandArgs(message.getContentDisplay());
    }

    protected String[] commandArgs(String string)
    {
        return string.split(" ");
    }

    protected Message sendMessage(MessageReceivedEvent e, Message message)
    {
        if(e.isFromType(ChannelType.PRIVATE))
            return e.getPrivateChannel().sendMessage(message).complete();
        else
            return e.getTextChannel().sendMessage(message).complete();
    }
    protected Message sendMessage(MessageReceivedEvent e, Message message, boolean blockFormat)
    {
        return sendMessage(e,message.getContentRaw(),true);
    }

    protected Message sendMessage(MessageReceivedEvent e, String message, boolean blockFormat)
    {
        if(!blockFormat)
        {
            return sendMessage(e, new MessageBuilder().append(message).build());
        }
        else
        {
            return sendMessage(e, new MessageBuilder().appendCodeBlock(message,"css").build());
        }
    }

    protected Message sendMessageToAuthor(MessageReceivedEvent e, String message, boolean blockFormat)
    {
        if(!blockFormat)
        {
            return sendMessage(e, new MessageBuilder().append(e.getAuthor()).append(message).build());
        }
        else
        {
            return sendMessage(e, new MessageBuilder().append(e.getAuthor()).appendCodeBlock(message, "css").build());
        }
    }


}
