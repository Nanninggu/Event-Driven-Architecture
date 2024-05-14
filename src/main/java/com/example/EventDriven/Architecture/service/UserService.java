package com.example.EventDriven.Architecture.service;

import com.example.EventDriven.Architecture.dto.User;
import com.example.EventDriven.Architecture.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getUser(String id) {
        return userMapper.getUser(id);
    }

}