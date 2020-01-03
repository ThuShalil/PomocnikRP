package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.character.Ability;

import java.util.Set;

public interface AbilityService
{
    Set<Ability> getAbilities();
    Ability findById(Long id);
    Ability findByName(String name);
    Ability saveAbility(Ability ability);
}
