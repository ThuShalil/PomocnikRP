package com.thushalil.pomocnikrp.discord.setting;

import com.thushalil.pomocnikrp.discord.command.HelpCommand;
import com.thushalil.pomocnikrp.discord.command.template.Command;
import com.thushalil.pomocnikrp.discord.listener.MessageListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Setting
{
    public static final String token = "NTU2NDUzNTYyOTM5MzQyODY4.D25-PQ.0XNR8iwBhLtVKWVHRn9IqqOEbME";
    public static final char commandPrefix = '>';

    @Component
    public static class DiscordInitializer
    {
        private String helpCommandPath = "com.thushalil.pomocnikrp.discord.command.HelpCommand";
        private String commandTemplatePath = "com.thushalil.pomocnikrp.discord.command.template.Command";
        private String pathToCommands = "com.thushalil.pomocnikrp.discord.command";

        JDA jda = null;

        public JDA getJda()
        {
            return jda;
        }

        public DiscordInitializer()
        {
            System.out.println("create bot...");
            try
            {
                jda = new JDABuilder(token).build();
                jda.getPresence().setPresence(Game.of(Game.GameType.WATCHING,"Busty MILF... on Pornhub™"),false);
            }
            catch (LoginException e)
            {
                e.printStackTrace();
            }
        }

        //not used...
        private void Init()
        {
            try
            {

                System.out.println("add message listener...");
                jda.addEventListener(new MessageListener());
                System.out.println("add commands...");

                Class[] classList = getClasses(pathToCommands);

                System.out.println("load help command");
                HelpCommand help = (HelpCommand)Class.forName(helpCommandPath).getConstructor().newInstance();
                jda.addEventListener(help);

                System.out.println(String.format("found %d commands",classList.length-2));
                for(Class c : classList)
                {
                    if(c.getName() != commandTemplatePath && c.getName() != helpCommandPath)
                    {
                        System.out.println(String.format("Load [%s]", c.getName()));
                        Command cmd =  (Command)c.getConstructor().newInstance();
                        jda.addEventListener(cmd);
                        help.getCommands().add(cmd);

                    }
                }


                System.out.println("ready for usage");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        private static Class[] getClasses(String packageName)
                throws ClassNotFoundException, IOException
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            assert classLoader != null;
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<File>();
            while (resources.hasMoreElements())
            {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            ArrayList<Class> classes = new ArrayList<Class>();
            for (File directory : dirs)
            {
                classes.addAll(findClasses(directory, packageName));
            }
            return classes.toArray(new Class[classes.size()]);
        }

        private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException
        {
            List<Class> classes = new ArrayList<Class>();
            if (!directory.exists())
            {
                return classes;
            }
            File[] files = directory.listFiles();
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                }
                else if (file.getName().endsWith(".class"))
                {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
            return classes;
        }

    }
}
