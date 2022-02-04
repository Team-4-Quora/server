package com.example.userservice.service.impl;

import com.example.userservice.entity.Moderator;
import com.example.userservice.repository.ModeratorRepository;
import com.example.userservice.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;

public class ModeratorServiceImpl implements ModeratorService {
    @Autowired
    ModeratorRepository moderatorRepository;

    public ModeratorServiceImpl(ModeratorRepository mod)
    {
        this.moderatorRepository=mod;
    }

    @Override
    public void save(Moderator moderator) {
        moderatorRepository.save(moderator);
    }

    @Override
    public Moderator findById(String id) {
        return moderatorRepository.findById(id).get();
    }
}
