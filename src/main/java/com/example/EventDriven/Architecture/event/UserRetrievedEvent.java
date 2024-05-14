package com.example.EventDriven.Architecture.event;

import com.example.EventDriven.Architecture.dto.User;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class UserRetrievedEvent extends ApplicationEvent {
    private final List<User> users;

    public UserRetrievedEvent(Object source, List<User> users) {
        super(source);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

}