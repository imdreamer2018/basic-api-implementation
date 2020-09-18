package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Transactional
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    private Integer age;

    private String gender;

    private String email;

    private String phone;

    @Column(name = "vote_num")
    private Integer voteNum;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<RsEventEntity> rsEvents;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<VoteEntity> votes;

    public UserEntity(String userName, Integer age, String gender, String email, String phone) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.voteNum = 10;
    }
}
