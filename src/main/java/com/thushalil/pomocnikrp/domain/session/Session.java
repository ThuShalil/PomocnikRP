package com.thushalil.pomocnikrp.domain.session;

import com.thushalil.pomocnikrp.domain.character.Hero;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Session
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String guildId;
    private String name;
    private String description;
    private String pidGM;

    @Lob
    private String history;

    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hero> playerList = new ArrayList<>();


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getGuildId()
    {
        return guildId;
    }

    public void setGuildId(String guildId)
    {
        this.guildId = guildId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPidGM()
    {
        return pidGM;
    }

    public void setPidGM(String pidGM)
    {
        this.pidGM = pidGM;
    }

    public String getHistory()
    {
        return history;
    }

    public void setHistory(String history)
    {
        this.history = history;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public List<Hero> getPlayerList()
    {
        return playerList;
    }

    public void setPlayerList(List<Hero> playerList)
    {
        this.playerList = playerList;
    }
}
