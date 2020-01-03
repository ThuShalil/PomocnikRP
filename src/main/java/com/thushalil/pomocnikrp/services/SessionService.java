package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.session.Session;

import java.util.List;

public interface SessionService
{
    List<Session> getSessions();
    Session findById(Long id);
    Session findByName(String name);
    Session findActive();
    Session saveSession(Session session);
}
