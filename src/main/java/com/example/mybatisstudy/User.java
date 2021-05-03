package com.example.mybatisstudy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    String id;
    String name;
    String email;

    User (String name, String email) {
        this("0", name, email);
    }
}
