package com.example.jooleproject.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class User {

    @Id
    private Integer userId;

    private String role;

    private String password;

}
