package com.example.EventDriven.Architecture.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRetrievedEventListener implements ApplicationListener<UserRetrievedEvent> {
    @Override
    public void onApplicationEvent(UserRetrievedEvent event) {
        System.out.println("User retrieved: " + event.getUsers().toString());

    }

}