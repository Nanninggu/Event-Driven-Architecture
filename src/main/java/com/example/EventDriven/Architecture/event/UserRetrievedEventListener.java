package com.example.EventDriven.Architecture.event;

import com.example.EventDriven.Architecture.dto.EventBox;
import com.example.EventDriven.Architecture.dto.User;
import com.example.EventDriven.Architecture.mapper.UserMapper;
import com.example.EventDriven.Architecture.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserRetrievedEventListener implements ApplicationListener<UserRetrievedEvent> {

    private static final Logger log = LoggerFactory.getLogger(UserRetrievedEventListener.class);
    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private User users;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void onApplicationEvent(UserRetrievedEvent event) {

        System.out.println("UserRetrievedEvent 클래스가 호출될 때마다 이 이벤트 리스너 클래스의 메소드를 호출한다.");

        // 값을 잘 가져 오는지 테스트
        event.getUsers().forEach(user -> {
            log.info("UserRetrievedEvent: {}", user.getName());
            System.out.println("User: " + user.getName());
        });

        EventBox eventBox = new EventBox();
        eventBox.setId(event.getUsers().get(0).getId());
        eventBox.setEventType("UserCreatedEvent");

        String payload = null;
        try {
            payload = new ObjectMapper().writeValueAsString(event); // convert user to JSON string
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        eventBox.setPayload(payload);

        // payload를 Postgres DB에 저장
        userMapper.insertOutbox(eventBox);

        // Kafka로 전송
        kafkaService.sendMessage("exam", payload);
        log.info("UserRetrievedEvent: {}", event.getUsers());
    }

}