package com.thushalil.pomocnikrp.repositories;

import com.thushalil.pomocnikrp.domain.session.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session,Long>
{
    Optional<Session> findByName(String name);
}
