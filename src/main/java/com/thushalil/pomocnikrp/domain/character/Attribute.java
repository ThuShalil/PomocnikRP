package com.thushalil.pomocnikrp.domain.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Attribute
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ColumnDefault("0")
    private int basicValue;
    @ColumnDefault("0")
    private int advancesValue;
    @ColumnDefault("0")
    private int bonusValue;

    private String name;
    private String longName;

    public Attribute(int basicValue, int advancesValue)
    {
        this.basicValue = basicValue;
        this.advancesValue = advancesValue;
    }

    @Override
    public String toString()
    {
        return "Attribute{" +
                "id=" + id +
                ", basicValue=" + basicValue +
                ", advancesValue=" + advancesValue +
                ", bonusValue=" + bonusValue +
                ", name='" + name + '\'' +
                '}';
    }

    public String getValue()
    {
        return String.valueOf(basicValue + advancesValue + bonusValue);
    }
}
