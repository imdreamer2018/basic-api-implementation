package com.thoughtworks.rslist.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    private Integer age;

    private String gender;

    private String email;

    private String phone;

}
