package com.thushalil.pomocnikrp.domain.character;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"heroes"})
public class Skill
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @Lob
    private String description;

    @ManyToMany(mappedBy = "skills")
    private Set<Hero> heroes;
}
