package com.thushalil.pomocnikrp.repositories;

import com.thushalil.pomocnikrp.domain.character.Hero;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HeroRepository extends CrudRepository<Hero, Long>
{
    Optional<Hero> findByOwner(String pid);
}
