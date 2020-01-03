package com.thushalil.pomocnikrp.discord.command;

import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.setting.Setting;
import com.thushalil.pomocnikrp.domain.character.Hero;
import com.thushalil.pomocnikrp.services.HeroService;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class HeroCreate extends Command
{
    private static final Logger logger = LoggerFactory.getLogger(HeroCreate.class);
    private HeroService heroService;

    public HeroCreate(Setting.DiscordInitializer discordInitializer, HelpCommand command, HeroService heroService)
    {
        super(discordInitializer, command);
        this.heroService = heroService;
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        try
        {
            heroService.findByOwner(e.getAuthor().getId());
        }
        catch (Exception ex)
        {
            Hero hero = new Hero();
            hero.setOwner(e.getAuthor().getId());
            heroService.saveHero(hero);
        }

        Hero h = heroService.findByOwner(e.getAuthor().getId());
        if(args.length == 1)
        {

            sendMessageToAuthor(e,h.toString(),true);
            if(e.isFromType(ChannelType.PRIVATE))
            {
                MessageBuilder messageBuilder = new MessageBuilder();
                messageBuilder.append(String.format("Pieniądze: Złote korony[%s] Srebro[%s] Pensy[%s]\n",
                        h.getInventory().getMoney().getGold(),
                        h.getInventory().getMoney().getSilver(),
                        h.getInventory().getMoney().getPennies()))
                .append(String.format("Profesja: %s\n",h.getProfession()))
                .append(String.format("Poprzednia proesja: %s\n",h.getLastProfession()))
                .append(String.format("Miejsce urodzenia:: %s\n",h.getPlaceOfBirth()))
                .append(String.format("Znak gwiezdny: %s\n",h.getStarSign()))
                .append(String.format("Rodzeństwo: %s\n",h.getSiblings()))
                .append(String.format("Opis: %s\n",h.getDescription()))
                ;
                sendMessage(e,messageBuilder.build(),true);
            }

        }
        else if(args.length >= 3)
        {
            if(args[1].equals("set"))
            {
                List<String> statArgs = new ArrayList<>();
                for(int i =2;i<args.length;i++)
                {
                    statArgs.add(args[i]);
                }
                logger.info(String.format("Discord id[%s] change values[%s] in hero id[%s], last values[%s]", e.getAuthor().getId(),
                        statArgs,h.getId(),h.getAttributeString()));
                parseAndSetAttribute(e,h,statArgs);
            }
            if(args[1].equals("edit"))
            {
                if(args[2].equals("name"));
                {
                    h.setName(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setName(h.getName() + " " +args[i]);
                        }
                        sendMessage(e,"Imię postaci zmienione",true);
                    }
                }
                else if(args[2].equals("race"))
                {
                    h.setRace(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setRace(h.getRace() + " " +args[i]);
                        }
                        sendMessage(e,"Rasa postaci zmieniona",true);
                    }
                }
                else if(args[2].equals("profession"))
                {
                    h.setProfession(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setProfession(h.getProfession() + " " +args[i]);
                        }
                        sendMessage(e,"Profesja postaci zmieniona",true);
                    }
                }
                else if(args[2].equals("lastprofession"))
                {
                    h.setLastProfession(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setLastProfession(h.getLastProfession() + " " +args[i]);
                        }
                        sendMessage(e,"Poprzednia profesja postaci zmieniona",true);
                    }
                }
                else if(args[2].equals("specialcharacters"))
                {
                    h.setSpecialCharacters(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setSpecialCharacters(h.getSpecialCharacters() + " " +args[i]);
                        }
                        sendMessage(e,"Znaki szczególne postaci zmienione",true);
                    }
                }
                else if(args[2].equals("description"))
                {
                    h.setDescription(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setDescription(h.getDescription() + " " +args[i]);
                        }
                        sendMessage(e,"Opis postaci zmieniony",true);
                    }
                }
                else if(args[2].equals("age"))
                {
                    h.setAge(Integer.valueOf(args[3]));
                    sendMessage(e,"Wiek postaci zmieniony",true);
                }
                else if(args[2].equals("weigh"))
                {
                    h.setHeroWeigh(Double.valueOf(args[3]));
                    sendMessage(e,"Waga postaci zmieniona",true);
                }
                else if(args[2].equals("height"))
                {
                    h.setHeight(Integer.valueOf(args[3]));
                    sendMessage(e,"Wzrost postaci zmieniony",true);
                }
                else if(args[2].equals("placeofbirth"))
                {
                    h.setPlaceOfBirth(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setPlaceOfBirth(h.getPlaceOfBirth() + " " +args[i]);
                        }
                        sendMessage(e,"Miejsce urodzenia zmienione",true);
                    }
                }
                else if(args[2].equals("eyecolor"))
                {
                    h.setEyeColor(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setEyeColor(h.getEyeColor() + " " +args[i]);
                        }
                        sendMessage(e,"Kolor oczu zmieniony",true);
                    }
                }
                else if(args[2].equals("haircolor"))
                {
                    h.setHairColor(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setHairColor(h.getHairColor() + " " +args[i]);
                        }
                        sendMessage(e,"Kolor włosów zmieniony",true);
                    }
                }
                else if(args[2].equals("starsign"))
                {
                    h.setStarSign(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setStarSign(h.getStarSign() + " " +args[i]);
                        }
                        sendMessage(e,"Znak gwiezdny zmieniony",true);
                    }
                }
                else if(args[2].equals("siblings"))
                {
                    h.setSiblings(args[3]);
                    if(args.length >= 4)
                    {
                        for(int i=4;i<args.length;i++)
                        {
                            h.setSiblings(h.getSiblings() + " " +args[i]);
                        }
                        sendMessage(e,"Ustawiono rodzeństwo",true);
                    }
                }
                heroService.saveHero(h);
            }
        }
    }

    private void parseAndSetAttribute(MessageReceivedEvent e, Hero h, List<String> list)
    {
        for(String statString : list)
        {
            String split[] = statString.split("=");
            String stat =  split[0];
            int value = Integer.parseInt(split[1]);
            if(stat.equalsIgnoreCase("ww"))
            {
                h.getWW().setBasicValue(value);
                h.getWW().setName("WW");
                h.getWW().setLongName("Walka wręcz");
                sendMessage(e,String.format("Ustawiono WW: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("us"))
            {
                h.getUS().setBasicValue(value);
                h.getUS().setName("US");
                h.getUS().setLongName("Umiejętności sztrzekecjue");
                sendMessage(e,String.format("Ustawiono US: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("k"))
            {
                h.getK().setBasicValue(value);
                h.getK().setName("K");
                h.getK().setLongName("Krzepa");
                sendMessage(e,String.format("Ustawiono K: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("odp"))
            {
                h.getODP().setBasicValue(value);
                h.getODP().setName("ODP");
                h.getODP().setLongName("Odporność");
                sendMessage(e,String.format("Ustawiono ODP: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("zr"))
            {
                h.getZR().setBasicValue(value);
                h.getZR().setName("ZR");
                h.getZR().setLongName("Zręczność");
                sendMessage(e,String.format("Ustawiono ZR: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("int"))
            {
                h.getINT().setBasicValue(value);
                h.getINT().setName("INT");
                h.getINT().setLongName("Inteligencja");
                sendMessage(e,String.format("Ustawiono INT: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("sw"))
            {
                h.getSW().setBasicValue(value);
                h.getSW().setName("SW");
                h.getSW().setLongName("Siła woli");
                sendMessage(e,String.format("Ustawiono SW: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("ogd"))
            {
                h.getOGD().setBasicValue(value);
                h.getOGD().setName("OGD");
                h.getOGD().setLongName("Ogłada");
                sendMessage(e,String.format("Ustawiono OGD: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("a"))
            {
                h.getA().setBasicValue(value);
                h.getA().setName("A");
                h.getA().setLongName("Atak");
                sendMessage(e,String.format("Ustawiono A: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("zyw"))
            {
                h.getZYW().setBasicValue(value);
                h.getZYW().setName("ZYW");
                h.getZYW().setLongName("Żywotność");
                sendMessage(e,String.format("Ustawiono ZYW: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("s"))
            {
                h.getS().setBasicValue(value);
                h.getS().setName("S");
                h.getS().setLongName("Siła");
                sendMessage(e,String.format("Ustawiono S: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("wt"))
            {
                h.getWT().setBasicValue(value);
                h.getWT().setName("WT");
                h.getWT().setLongName("Wytrzymałość");
                sendMessage(e,String.format("Ustawiono WT: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("sz"))
            {
                h.getSZ().setBasicValue(value);
                h.getSZ().setName("SZ");
                h.getSZ().setLongName("Szybkość");
                sendMessage(e,String.format("Ustawiono SZ: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("mag"))
            {
                h.getMAG().setBasicValue(value);
                h.getMAG().setName("MAG");
                h.getMAG().setLongName("Magia");
                sendMessage(e,String.format("Ustawiono MAG: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("po"))
            {
                h.getPO().setBasicValue(value);
                h.getPO().setName("PO");
                h.getPO().setLongName("Punkty obłędu");
                sendMessage(e,String.format("Ustawiono PO: %s", value),true);
            }
            else if(stat.equalsIgnoreCase("pp"))
            {
                h.getPP().setBasicValue(value);
                h.getPP().setName("PP");
                h.getPP().setLongName("Punkty przeznaczenia");
                sendMessage(e,String.format("Ustawiono PP: %s", value),true);
            }

        }

        heroService.saveHero(h);
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("hero","bg");
    }

    @Override
    public String getDescription()
    {
        return "Tworzenie oraz obsługa bohatera";
    }

    @Override
    public String getName()
    {
        return "HeroCreate";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(
                "hero - wyświetla podstawowe dane bohatera(zaawansowane statystyki w wiadomości prywatnej do bota)",
                "hero set <stat=value>... - ustawia statystyki gracza np. hero set ww=10 us=20 k=5",
                "hero edit name <value> - ustawia imie gracza",
                "hero edit description <value> - ustawia opis postaci",
                "hero edit specialcharacters <value> - ustawia znaki szczególne",
                "hero edit profession <value> - ustawia proefsje",
                "hero edit lastprofession <value> - ustawia poprzednia profesje",
                "hero edit race <value> - ustawia rase gracza",
                "hero edit age <value> - ustawia wiek gracza",
                "hero edit sex <value> - ustawia płeć gracza",
                "hero edit weigh <value> - ustawia wage gracza",
                "hero edit height <value> - ustawia wzrost gracza",
                "hero edit placeofbirth <value> - ustawia miejsce urodzenia gracza",
                "hero edit eyecolor <value> - ustawia kolor oczu gracza",
                "hero edit haircolor <value> - ustawia kolor włosów gracza",
                "hero edit starsign <value> - ustawia znak gwiezdny gracza",
                "hero edit siblings <value> - ustawia rodzeństwo gracza"
        );
    }

}
