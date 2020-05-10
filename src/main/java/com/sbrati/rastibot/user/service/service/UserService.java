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
        user.setUsername(userEntity.getUsername());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setLocale(userEntity.getLocale());
        user.setInactive(userEntity.isInactive());
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
        entity.setInactive(user.isInactive());
        userRepository.save(entity);
    }

    public List<Long> findByAwarenessLessThan(int awareness) {
        return userRepository.findByInactiveFalseAndAwarenessNullOrAwarenessLessThan(awareness)
                .stream()
                .map(UserEntity::getChatId)
                .collect(Collectors.toList());
    }

    public Long countAll() {
        return userRepository.count();
    }

    public Long countActive() {
        return userRepository.countAllByInactiveFalse();
    }

    @Transactional
    public void setAwareness(long chatId, int awareness) {
        userRepository.setAwareness(chatId, awareness);
    }

    @Transactional
    public void markAsInactive(long chatId) {
        userRepository.setInactive(chatId);
    }

    public List<Long> getAllChatIds() {
        return userRepository.getActiveChatIds();
    }
}
