package com.thushalil.pomocnikrp.domain.character;

import com.thushalil.pomocnikrp.domain.inventory.Item;
import com.thushalil.pomocnikrp.domain.inventory.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Inventory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Money money = new Money();
}
