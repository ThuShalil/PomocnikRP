package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.character.Hero;

import java.util.List;

public interface HeroService
{
    List<Hero> getHeroes();
    Hero findByOwner(String owner);
    Hero findById(Long id);
    Hero saveHero(Hero hero);
    boolean isHeroOwnerExist(String ownerid);
}
