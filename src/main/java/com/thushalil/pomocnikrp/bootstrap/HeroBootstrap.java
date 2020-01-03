package com.thushalil.pomocnikrp.bootstrap;

import com.thushalil.pomocnikrp.domain.character.*;
import com.thushalil.pomocnikrp.domain.inventory.Armor;
import com.thushalil.pomocnikrp.domain.inventory.Item;
import com.thushalil.pomocnikrp.domain.inventory.Weapon;
import com.thushalil.pomocnikrp.repositories.HeroRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

//@Component
public class HeroBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    private final HeroRepository heroRepository;

    public HeroBootstrap(HeroRepository heroRepository)
    {
        this.heroRepository = heroRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        Hero hero = new Hero();
        hero.setOwner("163056656127623168");
        hero.setProfession("zwiadowca");
        hero.setLastProfession("węglarz");
        hero.setRace("człowiek");
        hero.setWW(new Attribute(43,0));
        hero.setUS(new Attribute(33,0));
        hero.setK(new Attribute(48,0));
        hero.setODP(new Attribute(41,0));
        hero.setZR(new Attribute(46, 0));
        hero.setINT(new Attribute(33,0));
        hero.setSW(new Attribute(36,0));
        hero.setOGD(new Attribute(30,0));
        hero.setA(new Attribute(2,0));
        hero.setZYW(new Attribute(14,0));
        hero.setS(new Attribute(4,0));
        hero.setWT(new Attribute(4,0));
        hero.setSZ(new Attribute(4,0));
        hero.setMAG(new Attribute(0,0));
        hero.setPO(new Attribute(0,0));
        hero.setPP(new Attribute(3,0));

        Item a = new Item();
        a.setName("Przedmiot 1");
        a.setCount(10);
        a.setWeigh(10);

        Weapon b = new Weapon();
        b.setName("Bron 1");
        b.setCount(10);
        b.setWeigh(10);
        b.setStrength("S + 1");

        Armor c = new Armor();
        c.setName("Zbroja 1");
        c.setCount(10);
        c.setWeigh(10);

        hero.getInventory().getItemList().add(a);
        hero.getInventory().getItemList().add(b);
        hero.getInventory().getItemList().add(c);
        hero.getInventory().getMoney().addMoney(2,30,50);

        heroRepository.save(hero);

    }
}
