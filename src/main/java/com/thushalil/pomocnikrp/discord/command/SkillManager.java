package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import com.thushalil.pomocnikrp.domain.character.Hero;
import com.thushalil.pomocnikrp.domain.character.Skill;
import com.thushalil.pomocnikrp.exceptions.NotFoundException;
import com.thushalil.pomocnikrp.services.HeroService;
import com.thushalil.pomocnikrp.services.SkillService;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SkillManager extends Command
{
    private static final Logger logger = LoggerFactory.getLogger(HeroCreate.class);
    private final HeroService heroService;
    private final SkillService skillService;

    public SkillManager(Setting.DiscordInitializer discordInitializer, HelpCommand command, HeroService heroService, SkillService skillService)
    {
        super(discordInitializer, command);
        this.heroService = heroService;
        this.skillService = skillService;
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(args.length == 1)
        {
            Hero h = heroService.findByOwner(e.getAuthor().getId());
            MessageBuilder messageBuilder = new MessageBuilder();
            String format = "%d)[%s] - %s\n";
            String format_desc = "Lista zdolności bohatera %s";
            sendMessageToAuthor(e,String.format(format_desc,h.getName()),true);

            if(h.getSkills().size() == 0)
            {
                sendMessage(e,"Brak zdolności",true);
                return;
            }

            for(Skill s : h.getSkills())
            {
                messageBuilder.append(String.format(format,s.getId(),s.getName(),s.getDescription()));
            }

            sendMessage(e,messageBuilder.build(),true);
        }
        else if(args.length == 2)
        {
            if(args[1].equals("all"))
            {
                MessageBuilder messageBuilder = new MessageBuilder();
                String format = "%d)[%s] - %s\n";
                String format_desc = "Lista zdolności";

                for(Skill s : skillService.getSkills())
                {
                    messageBuilder.append(String.format(format,s.getId(),s.getName(),s.getDescription()));
                }
                sendMessage(e,messageBuilder.build(),true);
            }
        }
        else if(args.length == 3)
        {
            Hero h = heroService.findByOwner(e.getAuthor().getId());

            Skill skill = null;
            if (isDigit(args[2]))
            {
                try
                {
                    skill = skillService.findById(Long.valueOf(args[2]));
                }
                catch (NotFoundException ex)
                {
                    sendMessage(e,String.format("Brak zdolności o id %s w bazie", args[2]),true);
                    return;
                }

            }

            if(args[1].equals("add"))
            {
                for(Skill s : h.getSkills())
                {
                    if(s.getId() == skill.getId())
                    {
                        sendMessage(e,"Zdolność jest już dodana",true);
                        return;
                    }
                }
                h.getSkills().add(skill);
                sendMessage(e,String.format("Zdolność [%s] dodana",skill.getName()),true);
                logger.info(String.format("Discord id[%s] add skill [%s] to hero id[%s]", e.getAuthor().getId(),
                        skill.getName(),h.getId()));

            }
            if(args[1].equals("remove"))
            {
                if (isDigit(args[2]))
                {
                    for(Skill s : h.getSkills())
                    {
                        if(s.getId() == skill.getId())
                        {
                            h.getSkills().remove(s);
                            sendMessage(e,String.format("Zdolność [%s] usunięta", s.getName()),true);
                            logger.info(String.format("Discord id[%s] remove skill [%s] from hero id[%s]", e.getAuthor().getId(),
                                    s.getName(),h.getId()));
                            break;
                        }
                    }
                }
            }

            heroService.saveHero(h);
        }
        else if(args.length >= 3)
        {
            if(args[1].equals("add"))
            {
                String value = "";
                for(int i=2;i<args.length;i++)
                {
                    value += args[i];
                    if(i!=args.length-1)
                    {
                        value += " ";
                    }
                }

                String split[] = value.split(" / ");
                Skill skill = new Skill();
                skill.setName(split[0]);
                skill.setDescription(split[1]);

                try
                {
                    skill = skillService.saveSkill(skill);
                }
                catch (DataIntegrityViolationException ex)
                {
                    sendMessage(e,"Zdolność już istnieje w bazie",true);
                    return;
                }

                sendMessage(e,String.format("Zapisano zdolność [%s] do bazy. Id=%s", skill.getName(),skill.getId()),true);
                logger.info(String.format("Discord id[%s] add skill [%s]", e.getAuthor().getId(),
                        skill.getName()));
            }

        }
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("skill","skills","zdolnosci");
    }

    @Override
    public String getDescription()
    {
        return "Zarządzanie zdolnościami";
    }

    @Override
    public String getName()
    {
        return "Skills";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "skill - wyświetla liste zdolności gracza",
                "skill all - wyświetla listę wszystkich zdolności",
                "skill add <id> - dodaje nową zdolność dla gracza",
                "skill add moja nowa zdolność / jakiś fajny opis",
                "skill remove <id> - usuwa zdolność z listy gracza"
        );
    }
}
