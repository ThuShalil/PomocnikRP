package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.inventory.WeaponSpecialCategory;
import com.thushalil.pomocnikrp.repositories.WeaponSpecialCategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class WeaponSpecialCategoryServiceImpl implements WeaponSpecialCategoryService
{
    private final WeaponSpecialCategoryRepository weaponSpecialCategoryRepository;

    public WeaponSpecialCategoryServiceImpl(WeaponSpecialCategoryRepository weaponSpecialCategoryRepository)
    {
        this.weaponSpecialCategoryRepository = weaponSpecialCategoryRepository;
    }

    @Override
    public List<WeaponSpecialCategory> getWeaponSpecialCategories()
    {
        List<WeaponSpecialCategory> weaponCategories = new ArrayList<>();
        weaponSpecialCategoryRepository.findAll().iterator().forEachRemaining(weaponCategories::add);
        return weaponCategories;
    }
}
