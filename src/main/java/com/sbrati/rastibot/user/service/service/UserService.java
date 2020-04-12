package com.sbrati.rastibot.user.service.service;

import com.sbrati.rastibot.user.service.model.User;
import com.sbrati.rastibot.user.service.repository.UserEntity;
import com.sbrati.rastibot.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        UserEntity entity = userRepository.findByChatId(user.getChatId());
        if (entity == null) {
            log.info("Creating user {}.", user);
            entity = new UserEntity();
            entity.setChatId(user.getChatId());
        }
        entity.setLocale(user.getLocale());
        entity.setUsername(user.getUsername());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        userRepository.save(entity);
    }

    public List<Long> findByAwarenessLessThan(int awareness) {
        return userRepository.findByAwarenessNullOrAwarenessLessThan(awareness)
                .stream()
                .map(UserEntity::getChatId)
                .collect(Collectors.toList());
    }

    public Long count() {
        return userRepository.count();
    }

    @Transactional
    public void setAwareness(long chatId, int awareness) {
        userRepository.setAwareness(chatId, awareness);
    }

    public List<Long> getAllChatIds() {
        return userRepository.getAllChatIds();
    }
}
