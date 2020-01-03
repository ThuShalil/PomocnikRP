package com.thushalil.pomocnikrp.domain.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Weapon extends Item
{
    @ManyToMany(cascade = CascadeType.ALL)
    private List<WeaponSpecialCategory> specialCategory = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private WeaponCategory category;
    @Enumerated(value = EnumType.STRING)
    private WeaponType type;

    private  int accurateDistance;
    private int distance;
    private String strength;
}
