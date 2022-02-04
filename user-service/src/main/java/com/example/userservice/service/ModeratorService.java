package com.example.userservice.service;

import com.example.userservice.entity.Moderator;

public interface ModeratorService {

    void save(Moderator moderator);
    Moderator findById(String id);

}
