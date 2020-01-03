package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.character.Ability;
import com.thushalil.pomocnikrp.exceptions.NotFoundException;
import com.thushalil.pomocnikrp.repositories.AbilityRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AbilityServiceImpl implements AbilityService
{
    public final AbilityRepository abilityRepository;

    public AbilityServiceImpl(AbilityRepository abilityRepository)
    {
        this.abilityRepository = abilityRepository;
    }

    @Override
    public Set<Ability> getAbilities()
    {
        Set<Ability> abilities = new HashSet<>();
        abilityRepository.findAll().iterator().forEachRemaining(abilities::add);
        return abilities;
    }

    @Override
    public Ability findById(Long id)
    {
        Optional<Ability> optionalAbility = abilityRepository.findById(id);

        if(!optionalAbility.isPresent())
        {
            throw new NotFoundException("Ability Not Found. For ID value: " + id.toString());
        }
        return optionalAbility.get();
    }

    @Override
    public Ability findByName(String name)
    {
        Optional<Ability> optionalAbility = abilityRepository.findByName("name");

        if(!optionalAbility.isPresent())
        {
            throw new NotFoundException("Ability Not Found. For NAME value: " + name);
        }

        return  optionalAbility.get();
    }

    @Override
    public Ability saveAbility(Ability ability)
    {
        return abilityRepository.save(ability);
    }
}
