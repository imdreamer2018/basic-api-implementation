package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Data
@Transactional
@Builder
@Table(name = "rs_event")
@AllArgsConstructor
@NoArgsConstructor
public class RsEventEntity {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String eventName;

    private String keyWord;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
