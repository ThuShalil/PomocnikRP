package com.thushalil.pomocnikrp.domain.character;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"heroes"})
@Entity
public class Ability
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private AbilityType type;
    private String attribute;
    private int modificator;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Lob
    private String description;

    @ManyToMany(mappedBy = "abilities")
    private Set<Hero> heroes;
}
