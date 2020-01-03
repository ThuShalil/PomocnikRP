package com.thushalil.pomocnikrp.repositories;

import com.thushalil.pomocnikrp.domain.character.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkillRepository extends CrudRepository<Skill,Long>
{
    Optional<Skill> findById(Long id);
    Optional<Skill> findByName(String name);
}
