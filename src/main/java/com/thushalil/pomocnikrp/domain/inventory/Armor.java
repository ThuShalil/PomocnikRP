package com.thushalil.pomocnikrp.domain.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Armor extends Item
{
    private int armor;

    private boolean HEAD;
    private boolean BODY;
    private boolean ARMS;
    private boolean LEGS;
}
