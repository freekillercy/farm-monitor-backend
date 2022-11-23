package com.farmmonitorbackend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String email;
}
