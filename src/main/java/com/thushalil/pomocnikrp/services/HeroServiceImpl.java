package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.character.Hero;
import com.thushalil.pomocnikrp.exceptions.NotFoundException;
import com.thushalil.pomocnikrp.repositories.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService
{

    private final HeroRepository heroRepository;

    public HeroServiceImpl(HeroRepository heroRepository)
    {
        this.heroRepository = heroRepository;
    }

    @Override
    public List<Hero> getHeroes()
    {
        List<Hero> heroList = new ArrayList<>();
        heroRepository.findAll().iterator().forEachRemaining(heroList::add);
        return heroList;

    }

    @Override
    public Hero findByOwner(String owner)
    {
        Optional<Hero> heroOptional = heroRepository.findByOwner(owner);

        if(!heroOptional.isPresent())
        {
            throw new NotFoundException("Hero Not Found. For owner value: " + owner);
        }

        return heroOptional.get();
    }

    @Override
    public Hero findById(Long id)
    {
        Optional<Hero> heroOptional = heroRepository.findById(id);

        if(!heroOptional.isPresent())
        {
            throw new NotFoundException("Hero Not Found. For ID value: " + id.toString());
        }

        return heroOptional.get();
    }

    @Override
    public Hero saveHero(Hero hero)
    {
        return heroRepository.save(hero);

    }

    @Override
    public boolean isHeroOwnerExist(String ownerid)
    {
        Optional<Hero> heroOptional = heroRepository.findByOwner(ownerid);

        if(!heroOptional.isPresent())
        {
            return false;
        }

        return true;
    }
}
