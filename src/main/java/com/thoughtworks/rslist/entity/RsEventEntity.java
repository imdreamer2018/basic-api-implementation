package com.thoughtworks.rslist.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Data
@Transactional
@Builder
@Table(name = "rs_event")
public class RsEventEntity {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    private String eventName;

    private String keyWord;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private UserEntity userEntity;

}
