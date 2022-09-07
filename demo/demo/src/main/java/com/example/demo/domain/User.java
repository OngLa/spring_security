package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User {

    @Id
    private String id;
    private String pw;
    private String role;
}
