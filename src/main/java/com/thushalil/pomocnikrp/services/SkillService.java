package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.character.Skill;

import java.util.List;

public interface SkillService
{
    List<Skill> getSkills();
    Skill findById(Long id);
    Skill findByName(String name);
    Skill saveSkill(Skill skill);
}
