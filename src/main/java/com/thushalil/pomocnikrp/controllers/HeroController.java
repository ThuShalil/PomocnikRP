package com.thushalil.pomocnikrp.controllers;

import com.thushalil.pomocnikrp.discord.setting.Setting;
import com.thushalil.pomocnikrp.domain.character.Hero;
import com.thushalil.pomocnikrp.services.HeroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HeroController
{
    private final HeroService heroService;
    private final Setting.DiscordInitializer discordInitializer;

    public HeroController(HeroService heroService, Setting.DiscordInitializer discordInitializer)
    {
        this.heroService = heroService;
        this.discordInitializer = discordInitializer;
    }

    @GetMapping({"/hero/{id}/show"})
    public String showById(@PathVariable String id, Model model)
    {
        Hero h = heroService.findById(Long.valueOf(id));
        String avatar = discordInitializer.getJda().getUserById(h.getOwner()).getAvatarUrl();
        String name = discordInitializer.getJda().getUserById(h.getOwner()).getName();
        model.addAttribute("avatarurl",avatar);
        model.addAttribute("name",name);
        model.addAttribute("hero", h);
        return "hero/show";
    }
}
