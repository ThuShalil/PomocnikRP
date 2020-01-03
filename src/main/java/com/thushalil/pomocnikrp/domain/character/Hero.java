package com.thushalil.pomocnikrp.domain.character;

import com.thushalil.pomocnikrp.domain.character.Magic.SpellBook;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Hero
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String owner;
    private String name;
    private String profession;
    private String lastProfession;
    private String race;
    @Lob
    private String description;
    private int age;
    private String sex;
    private double heroWeigh;
    private int height;
    private String placeOfBirth;
    private String specialCharacters;
    private String eyeColor;
    private String hairColor;
    private String starSign;
    private String siblings;
    private double maxWeigh;

    //use only to get /hero/{id}/view.html
    private String pass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hero_skills",
            joinColumns = @JoinColumn(name = "hero_id"),
            inverseJoinColumns = @JoinColumn(name = "skills_id"))
    @org.hibernate.annotations.OrderBy(clause = "skills_id")
    private Set<Skill> skills = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hero_abilities",
            joinColumns = @JoinColumn(name = "hero_id"),
            inverseJoinColumns = @JoinColumn(name = "abilities_id"))
    private Set<Ability> abilities = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Equipment equipment = new Equipment();
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory = new Inventory();
    @OneToOne(cascade = CascadeType.ALL)
    private SpellBook spells;

    @OneToOne(cascade = CascadeType.ALL)
    private Attribute WW = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute US = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute K = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute ODP = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute ZR = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute INT = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute SW = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute OGD = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute A = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute ZYW = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute S = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute WT = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute SZ = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute MAG = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute PO = new Attribute();
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute PP = new Attribute();

    public String getAttributeString()
    {
        return WW.toString()+US.toString()+K.toString()+ODP.toString()+ZR.toString()+INT.toString()+SW.toString()+OGD.toString()
                +A.toString()+ZYW.toString()+S.toString()+WT.toString()+SZ.toString()+MAG.toString()+PO.toString()+PP.toString();
    }

    @Override
    public String toString()
    {
        String format = "║%s│%s│%s│%s│%s│%s│%s│%s║\n";
        int size = 6;

        String statLine1 = String.format(format,
                StringUtils.center("WW", size),
                StringUtils.center("US", size),
                StringUtils.center("K", size),
                StringUtils.center("ODP", size),
                StringUtils.center("ZR", size),
                StringUtils.center("INT", size),
                StringUtils.center("SW", size),
                StringUtils.center("OGD", size)
        );
        String statLine2 = String.format(format,
                StringUtils.center("A", size),
                StringUtils.center("ŻYW", size),
                StringUtils.center("S", size),
                StringUtils.center("WT", size),
                StringUtils.center("SZ", size),
                StringUtils.center("MAG", size),
                StringUtils.center("PO", size),
                StringUtils.center("PP", size)
        );

        String top = "╔" + StringUtils.center("",statLine1.length()-3,"═") + "╗\n";
        String bottom = "╚" + StringUtils.center("",statLine1.length()-3,"═") + "╝\n";
        String separator = "║" + StringUtils.center("",statLine1.length()-3,"─") + "║\n";

        return
                "Imie: " + name + '\n' +
                "Rasa: " + race + '\n' +
                "Znaki szczególne: " + specialCharacters + '\n' +
                        "Wiek:" + age+ '\n' +
                        "Płeć:" + sex+ '\n' +
                        "Waga:" + heroWeigh+ '\n' +
                        "Wzrost:" + height+ '\n' +
                        "Kolor oczu:" + eyeColor+ '\n' +
                        "Kolor włosów" + hairColor+ '\n' +

                        "\n\n" +
                        "Cechy główne\n"+
                        top+
                        statLine1+
                        separator+
                        String.format(format,
                               StringUtils.center(WW.getValue(),size),
                                StringUtils.center(US.getValue(),size),
                                StringUtils.center(K.getValue(),size),
                                StringUtils.center(ODP.getValue(),size),
                                StringUtils.center(ZR.getValue(),size),
                                StringUtils.center(INT.getValue(),size),
                                StringUtils.center(SW.getValue(),size),
                                StringUtils.center(OGD.getValue(),size)
                                )+

                        bottom+
                        "Cechy drugorzędne\n"+
                        top+
                        statLine2+
                        separator+
                        String.format(format,
                                StringUtils.center(A.getValue(),size),
                                StringUtils.center(ZYW.getValue(),size),
                                StringUtils.center(S.getValue(),size),
                                StringUtils.center(WT.getValue(),size),
                                StringUtils.center(SZ.getValue(),size),
                                StringUtils.center(MAG.getValue(),size),
                                StringUtils.center(PO.getValue(),size),
                                StringUtils.center(PP.getValue(),size)
                        )
                        +bottom;
    }
}
