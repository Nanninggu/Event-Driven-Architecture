package com.example.EventDriven.Architecture.controller;

import com.example.EventDriven.Architecture.dto.User;
import com.example.EventDriven.Architecture.event.UserRetrievedEvent;
import com.example.EventDriven.Architecture.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public UserController(UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/users/{id}")
    public List<User> getUser(@PathVariable String id) {
        List<User> users = userService.getUser(id);
        eventPublisher.publishEvent(new UserRetrievedEvent(this, users));
        return users;
    }

}