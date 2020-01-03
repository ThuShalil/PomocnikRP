package com.thushalil.pomocnikrp.domain.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Lob
    private String description;
    private double count;
    private double weigh;
    @Enumerated(value = EnumType.STRING)
    private Quality quality;

    @OneToOne(cascade = CascadeType.ALL)
    private Money Price = new Money();

    private boolean equitable;
}
