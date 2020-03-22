package com.sbrati.rastibot.user.service.repository;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "locale")
    private String locale;
}
