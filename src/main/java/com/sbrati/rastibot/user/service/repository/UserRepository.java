package com.sbrati.rastibot.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByChatId(long chatId);

    List<UserEntity> findByAwarenessNullOrAwarenessLessThan(int awareness);

    @Modifying
    @Query(value = "UPDATE user_table SET awareness = :awareness WHERE chat_id = :chatId")
    void setAwareness(@Param("chatId") long chatId, @Param("awareness") int awareness);
}
