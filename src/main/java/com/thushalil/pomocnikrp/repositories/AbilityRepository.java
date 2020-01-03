package com.thushalil.pomocnikrp.repositories;

import com.thushalil.pomocnikrp.domain.character.Ability;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AbilityRepository extends CrudRepository<Ability, Long>
{
    Optional<Ability> findById(Long id);
    Optional<Ability> findByName(String name);
}
