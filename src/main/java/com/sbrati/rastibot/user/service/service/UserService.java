package com.sbrati.rastibot.user.service.service;

import com.sbrati.rastibot.user.service.model.User;
import com.sbrati.rastibot.user.service.repository.UserEntity;
import com.sbrati.rastibot.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByChatId(long chatId) {
        UserEntity userEntity = userRepository.findByChatId(chatId);
        if (userEntity == null) {
            return null;
        }

        User user = new User();
        user.setChatId(chatId);
        user.setLocale(userEntity.getLocale());
        return user;
    }

    @Transactional
    public void createOrUpdate(User user) {
        log.info("Creating user {}.", user);
        UserEntity entity = userRepository.findByChatId(user.getChatId());
        if (entity == null) {
            entity = new UserEntity();
            entity.setChatId(user.getChatId());
        }
        entity.setLocale(user.getLocale());
        userRepository.save(entity);
    }
}
