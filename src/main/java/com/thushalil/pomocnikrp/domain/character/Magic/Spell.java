package com.thushalil.pomocnikrp.domain.character.Magic;

import com.thushalil.pomocnikrp.domain.inventory.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Spell
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Lob
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private MagicType magicType;
    private float power;
    @ManyToMany
    private List<Item> ingredients = new ArrayList<>();
}
