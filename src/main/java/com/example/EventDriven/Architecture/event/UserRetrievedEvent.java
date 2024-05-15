package com.example.EventDriven.Architecture.event;

import com.example.EventDriven.Architecture.dto.User;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class UserRetrievedEvent extends ApplicationEvent {
    private final List<User> users;

    public UserRetrievedEvent(Object source, List<User> users) {
        super(source); // super is used to call the constructor of the parent class, 부모 클래스를 나타낸다.
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

}