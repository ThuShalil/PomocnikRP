package com.thushalil.pomocnikrp.repositories;

import com.thushalil.pomocnikrp.domain.inventory.WeaponCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WeaponCategoryRepository extends CrudRepository<WeaponCategory, Long>
{
    Optional<WeaponCategory> findByDescription(String description);
}
