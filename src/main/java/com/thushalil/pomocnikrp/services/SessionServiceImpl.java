package com.thushalil.pomocnikrp.services;

import com.thushalil.pomocnikrp.domain.session.Session;
import com.thushalil.pomocnikrp.exceptions.NotFoundException;
import com.thushalil.pomocnikrp.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService
{
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository)
    {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Session> getSessions()
    {
        List<Session> sessions = new ArrayList<>();
        sessionRepository.findAll().iterator().forEachRemaining(sessions::add);
        return sessions;
    }

    @Override
    public Session findById(Long id)
    {
        Optional<Session> session = sessionRepository.findById(id);

        if(!session.isPresent())
        {
            throw new NotFoundException("Session Not Found. For ID value: " + id.toString());
        }

        return session.get();
    }

    @Override
    public Session findByName(String name)
    {
        Optional<Session> session = sessionRepository.findByName(name);

        if(!session.isPresent())
        {
            throw new NotFoundException("Session Not Found. For Name value: " + name);
        }

        return session.get();
    }

    @Override
    public Session findActive()
    {
        Optional<Session> sessionOptional = Optional.empty();

        for (Session session : sessionRepository.findAll())
        {
            if(session.isActive())
            {
                sessionOptional = Optional.of(session);
            }
        }

        if(!sessionOptional.isPresent())
        {
            throw new NotFoundException("Active Session Not Found.");
        }

        return sessionOptional.get();

    }

    @Override
    public Session saveSession(Session session)
    {
        return sessionRepository.save(session);
    }
}
