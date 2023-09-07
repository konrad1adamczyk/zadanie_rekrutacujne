package com.devskiller.audit.service;

import com.devskiller.audit.consumer.RegisteredUserEvent;
import com.devskiller.audit.model.User;
import com.devskiller.audit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserEventProcessor {

    @Autowired
    private UserRepository userRepository;

    public void processEvent(RegisteredUserEvent event) {
        User user = new User(event.login(), event.creationTime());
        userRepository.save(user);
    }

}
