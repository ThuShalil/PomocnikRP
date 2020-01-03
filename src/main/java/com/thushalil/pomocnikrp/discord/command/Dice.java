package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Dice extends Command
{

    public Dice(Setting.DiscordInitializer discordInitializer, HelpCommand command)
    {
        super(discordInitializer, command);
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(args.length > 1)
        {
            String roll = args[1];
            String splited[];

            if(roll.contains("d"))
            {
                splited = roll.split("d");
            }
            else if(roll.contains("k"))
            {
                splited = roll.split("k");
            }
            else
            {
                sendMessage(e,"Błędne użycie komendy - zobacz użycie wpisując komende pomoc",true);
                return;
            }

            if(splited.length > 2 || !isDigit(splited[0]) || !isDigit(splited[1]))
            {
                sendMessage(e,String.format("rzut %s nie jest prawidłowy",roll),true);
                return;
            }

            int multipler = Integer.parseInt(splited[0]);
            int to = Integer.parseInt(splited[1]);


            Random random = new Random();
            int result = 0;
            for(int i =0;i<multipler;i++)
            {
                int r =random.nextInt(to);
                result += r;
                sendMessage(e,String.format("Rzut %d: %d", i+1,r),true);
            }

            sendMessage(e,String.format("wynik: %d", result),true);

        }
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("dice","rzut","kostka","roll","r");
    }

    @Override
    public String getDescription()
    {
        return "Losuje rzut kostką";
    }

    @Override
    public String getName()
    {
        return "Dice-Roll";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "roll <x>k/d<x+?> - wykonuje rzut kostką",
                "e.q",
                "roll 2k100 - rzuca 2 kościami od 1 do 100 i sumuje wynik"
        );
    }
}
