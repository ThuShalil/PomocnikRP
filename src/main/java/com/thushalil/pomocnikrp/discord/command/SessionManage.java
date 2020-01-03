package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import com.thushalil.pomocnikrp.domain.character.Hero;
import com.thushalil.pomocnikrp.domain.session.Session;
import com.thushalil.pomocnikrp.services.HeroService;
import com.thushalil.pomocnikrp.services.SessionService;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SessionManage extends Command
{
    private final SessionService sessionService;
    private final HeroService heroService;

    public SessionManage(Setting.DiscordInitializer discordInitializer, HelpCommand command, SessionService sessionService, HeroService heroService)
    {
        super(discordInitializer, command);
        this.sessionService = sessionService;
        this.heroService = heroService;
        setActive(false);
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(args.length == 2)
        {
            if(args[1].equals("show"))
            {

                for (Session s : sessionService.getSessions())
                {
                    MessageBuilder message = new MessageBuilder();
                    message.append(String.format("Sesja: %s\nOpis: %s\nAktywna: %s",
                            s.getName(),
                            ((s.getDescription() == null) ? "" : s.getDescription()),
                            s.isActive()));
                    sendMessage(e,message.build(),true);
                }
            }
        }
        else if(args.length == 3)
        {
            if(args[1].equals("create"))
            {
                Session session = new Session();
                session.setName(args[2]);
                session.setGuildId(e.getGuild().getId());
                session.setPidGM(e.getAuthor().getId());
                sessionService.saveSession(session);
                sendMessage(e,"Sesja utworzona",true);
            }
            else if(args[1].equals("show"))
            {

                Session session = sessionService.findByName(args[2]);
                MessageBuilder message = new MessageBuilder();
                message.append(String.format("Sesja: %s\nOpis: %s\nAktywna: %s\nGracze:",
                        session.getName(),
                        ((session.getDescription() == null) ? "" : session.getDescription()),
                        session.isActive()));

                for(Hero h : session.getPlayerList())
                {
                    message.append(h.getName()+"\n");
                }
                message.append("\nGame Master: ").append(e.getGuild().getMemberById(session.getPidGM()).getUser().getName());
                message.append("\nHistoria:").append(session.getHistory());

                sendMessage(e,message.build(),true);

            }
        }
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("session","sesja","rozgrywka");
    }

    @Override
    public String getDescription()
    {
        return "Zarządza sesją gry";
    }

    @Override
    public String getName()
    {
        return "Session-Manager";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "session create <name> - tworzy nową sesje",
                "session show - wyświetla liste sesji",
                "session show <name> - wyświetla dane sesji",
                "session remove <name> - usuwa sesje z bazy",
                "session description add <session> <description> - dodaje opis sesji",
                "session description - wyświetla opis sesji",
                "session history add <session> <description> - dodaje log historii",
                "session history <session> - wyświetla historie sesji",
                "session active <session> - ustawie sesje jako aktywną",
                "session active - wyświetla aktywną sesje",
                "session join <session> - dołączenie do sesji przez gracza",
                "session leave <session> - wyjście z sesji przez gracza"
        );
    }
}
