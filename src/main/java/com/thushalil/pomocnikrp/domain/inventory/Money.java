package com.thushalil.pomocnikrp.domain.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Money
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int gold;
    private int silver;
    private int pennies;

    public int getGold()
    {
        return gold;
    }

    public void addMoney(int gold, int silver, int pennies)
    {
        this.gold += gold;
        this.silver += silver;
        this.pennies += pennies;
    }

    public boolean payMoney(Money money)
    {
        return payMoney(money.getGold(),money.getSilver(),money.pennies);
    }

    public boolean payMoney(int gold, int silver, int pennies)
    {
        if(pennies > this.pennies)
        {
            if(this.silver == 0)
            {
                if(this.gold == 0)
                {
                    return false;
                }
                else
                {
                    this.gold--;
                    this.silver += 20;
                    return payMoney(gold,silver,pennies);
                }
            }
            else
            {
                this.silver--;
                this.pennies += 12;
                return payMoney(gold,silver,pennies);
            }
        }

        if(silver > this.pennies)
        {
            if(this.gold == 0)
            {
                return false;
            }
            else
            {
                this.gold--;
                this.silver += 20;
                return payMoney(gold,silver,pennies);
            }
        }

        if(gold > this.gold)
        {
            return false;
        }


        this.gold -= gold;
        this.silver -= silver;
        this.pennies -= pennies;
        return true;
    }

    @Override
    public String toString()
    {
        return "Money{" +
                "gold=" + gold +
                ", silver=" + silver +
                ", pennies=" + pennies +
                '}';
    }
}
