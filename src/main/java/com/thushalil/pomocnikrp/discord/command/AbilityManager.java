package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import com.thushalil.pomocnikrp.domain.character.Ability;
import com.thushalil.pomocnikrp.domain.character.Hero;
import com.thushalil.pomocnikrp.services.AbilityService;
import com.thushalil.pomocnikrp.services.HeroService;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class AbilityManager extends Command
{

        private static final Logger logger = LoggerFactory.getLogger(HeroCreate.class);
        private final HeroService heroService;
        private final AbilityService abilityService;

        public AbilityManager(Setting.DiscordInitializer discordInitializer, HelpCommand command, HeroService heroService, AbilityService abilityService)
        {
            super(discordInitializer, command);
            this.heroService = heroService;
            this.abilityService = abilityService;
        }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        //abbility
        String format_desc = "Lista umiejętności bohatera %s";
        String format = "%d)[%s] - %s\n";

        if(args.length == 1)
        {
            Hero h = new Hero();
            h = heroService.findByOwner(e.getAuthor().getId());

            Set<Ability> abilities = h.getAbilities();


            sendMessage(e,String.format(format_desc,h.getName()),true);
            MessageBuilder messageBuilder = new MessageBuilder();;

            if(abilities.size() == 0)
            {
                sendMessage(e,"Brak umiejętności",true);
                return;
            }

            for(Ability ability : abilities)
            {
                messageBuilder.append(String.format(format,ability.getId(),ability.getName(),ability.getDescription()));
            }
            sendMessage(e,messageBuilder.build(),true);
        }
        else
        {
            if(args[1].equals("all"))
            {
                MessageBuilder messageBuilder = new MessageBuilder();

                for(Ability ability : abilityService.getAbilities())
                {
                    messageBuilder.append(String.format(format,ability.getId(),ability.getName(),ability.getDescription()));
                }
                sendMessage(e,messageBuilder.build(),true);
            }
        }
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("ability","umiejetnosci","abilities");
    }

    @Override
    public String getDescription()
    {
        return "Zarządzanie umiejętnościami";
    }

    @Override
    public String getName()
    {
        return "AbbilityManager";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "ability - wyświetla liste umiejętności gracza",
                "ability all - wyświetla listę wszystkich umiejętności",
                "ability add <id> - dodaje nową umiejętność dla gracza",
                "ability add <name> / <STANDARD/ADVANCED> / <0/10/20> / <ATTRIBUTE> / <DESCRIPTION> - dodaje nową umiejętność do bazy",
                "ability remove <id> - usuwa umiejętność z listy gracza"
        );
    }
}
