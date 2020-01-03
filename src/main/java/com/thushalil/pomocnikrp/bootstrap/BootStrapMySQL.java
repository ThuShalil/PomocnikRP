package com.thushalil.pomocnikrp.bootstrap;

import com.thushalil.pomocnikrp.domain.inventory.WeaponCategory;
import com.thushalil.pomocnikrp.domain.inventory.WeaponSpecialCategory;
import com.thushalil.pomocnikrp.repositories.WeaponCategoryRepository;
import com.thushalil.pomocnikrp.repositories.WeaponSpecialCategoryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


//@Component
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent>
{
    private final WeaponCategoryRepository weaponCategoryRepository;
    private final WeaponSpecialCategoryRepository weaponSpecialCategoryRepository;

    public BootStrapMySQL(WeaponCategoryRepository weaponCategoryRepository, WeaponSpecialCategoryRepository weaponSpecialCategoryRepository)
    {
        this.weaponCategoryRepository = weaponCategoryRepository;
        this.weaponSpecialCategoryRepository = weaponSpecialCategoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        loadWeaponCategories();
        loadWeaponSpecialCategories();
    }

    private void loadWeaponCategories()
    {
        WeaponCategory cat1 = new WeaponCategory();
        cat1.setDescription("NORMAL");
        weaponCategoryRepository.save(cat1);

        WeaponCategory cat2 = new WeaponCategory();
        cat2.setDescription("TWO-HANDED");
        weaponCategoryRepository.save(cat2);

        WeaponCategory cat3 = new WeaponCategory();
        cat3.setDescription("PARRYING");
        weaponCategoryRepository.save(cat3);

        WeaponCategory cat4 = new WeaponCategory();
        cat4.setDescription("LONG-BOW");
        weaponCategoryRepository.save(cat4);
    }

    private void loadWeaponSpecialCategories()
    {
        WeaponSpecialCategory cat1 = new WeaponSpecialCategory();
        cat1.setDescription("HEAVY");
        weaponSpecialCategoryRepository.save(cat1);

        WeaponSpecialCategory cat2 = new WeaponSpecialCategory();
        cat2.setDescription("DEVASTATING");
        weaponSpecialCategoryRepository.save(cat2);

        WeaponSpecialCategory cat3 = new WeaponSpecialCategory();
        cat3.setDescription("EXPERIMENTAL");
        weaponSpecialCategoryRepository.save(cat3);

        WeaponSpecialCategory cat4 = new WeaponSpecialCategory();
        cat4.setDescription("FRAGMENTATION");
        weaponSpecialCategoryRepository.save(cat4);

        WeaponSpecialCategory cat5 = new WeaponSpecialCategory();
        cat5.setDescription("STUNNING");
        weaponSpecialCategoryRepository.save(cat5);

        WeaponSpecialCategory cat6 = new WeaponSpecialCategory();
        cat6.setDescription("PARRYING");
        weaponSpecialCategoryRepository.save(cat6);

        WeaponSpecialCategory cat7 = new WeaponSpecialCategory();
        cat7.setDescription("SLOW");
        weaponSpecialCategoryRepository.save(cat7);

        WeaponSpecialCategory cat8 = new WeaponSpecialCategory();
        cat8.setDescription("PRECISE");
        weaponSpecialCategoryRepository.save(cat8);

        WeaponSpecialCategory cat9 = new WeaponSpecialCategory();
        cat9.setDescription("PIERCING ARMOR");
        weaponSpecialCategoryRepository.save(cat9);

        WeaponSpecialCategory cat10 = new WeaponSpecialCategory();
        cat10.setDescription("SPECIAL");
        weaponSpecialCategoryRepository.save(cat10);

        WeaponSpecialCategory cat11 = new WeaponSpecialCategory();
        cat11.setDescription("FAST");
        weaponSpecialCategoryRepository.save(cat11);

        WeaponSpecialCategory cat12 = new WeaponSpecialCategory();
        cat12.setDescription("IMMOBILIZING");
        weaponSpecialCategoryRepository.save(cat12);

        WeaponSpecialCategory cat13 = new WeaponSpecialCategory();
        cat13.setDescription("BALANCED");
        weaponSpecialCategoryRepository.save(cat13);

        WeaponSpecialCategory cat14 = new WeaponSpecialCategory();
        cat14.setDescription("UNRELIABLE");
        weaponSpecialCategoryRepository.save(cat14);
    }
}
