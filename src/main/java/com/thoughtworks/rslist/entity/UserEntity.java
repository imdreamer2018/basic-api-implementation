package com.thoughtworks.rslist.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Transactional
@Builder
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

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.REMOVE)
    private List<RsEventEntity> rsEvents;

}
