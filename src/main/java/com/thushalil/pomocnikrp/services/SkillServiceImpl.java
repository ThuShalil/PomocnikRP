package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.character.Skill;
import com.thushalil.pomocnikrp.exceptions.NotFoundException;
import com.thushalil.pomocnikrp.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService
{
    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository)
    {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getSkills()
    {
        List<Skill> skills = new ArrayList<>();
        skillRepository.findAll().iterator().forEachRemaining(skills::add);
        return skills;
    }

    @Override
    public Skill findById(Long id)
    {
        Optional<Skill> optionalSkill = skillRepository.findById(id);

        if(!optionalSkill.isPresent())
        {
            throw new NotFoundException("Skill Not Found. For ID value: " + id.toString());
        }

        return optionalSkill.get();
    }

    @Override
    public Skill findByName(String name)
    {
        Optional<Skill> optionalSkill = skillRepository.findByName(name);

        if(!optionalSkill.isPresent())
        {
            throw new NotFoundException("Skill Not Found. For NAME value: " + name);
        }

        return optionalSkill.get();
    }

    @Override
    public Skill saveSkill(Skill skill)
    {
        return skillRepository.save(skill);
    }
}
