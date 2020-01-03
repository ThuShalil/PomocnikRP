package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.inventory.WeaponCategory;
import com.thushalil.pomocnikrp.repositories.WeaponCategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class WeaponCategoryServiceImpl implements WeaponCategoryService
{
    private final WeaponCategoryRepository weaponCategoryRepository;

    public WeaponCategoryServiceImpl(WeaponCategoryRepository weaponCategoryRepository)
    {
        this.weaponCategoryRepository = weaponCategoryRepository;
    }

    @Override
    public List<WeaponCategory> getWeaponCategories()
    {
        List<WeaponCategory> weaponCategories = new ArrayList<>();
        weaponCategoryRepository.findAll().iterator().forEachRemaining(weaponCategories::add);
        return weaponCategories;
    }
}
