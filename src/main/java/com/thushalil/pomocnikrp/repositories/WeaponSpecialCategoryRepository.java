package com.thushalil.pomocnikrp.repositories;

import com.thushalil.pomocnikrp.domain.inventory.WeaponSpecialCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
@NoRepositoryBean
public interface WeaponSpecialCategoryRepository extends CrudRepository<WeaponSpecialCategory, Long>
{
    Optional<WeaponSpecialCategory> findByDescription(String description);
}
