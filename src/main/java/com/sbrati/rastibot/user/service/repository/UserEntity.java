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

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "locale")
    private String locale;

    @Column(name = "awareness")
    private Integer awareness;

    @Column(name = "inactive")
    private boolean inactive;
}
