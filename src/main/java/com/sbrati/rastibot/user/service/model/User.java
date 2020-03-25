package com.sbrati.rastibot.user.service.model;

import lombok.Data;

@Data
public class User {

    private long chatId;
    private String username;
    private String firstName;
    private String lastName;
    private String locale;
}
