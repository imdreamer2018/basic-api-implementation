package com.thoughtworks.rslist.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rs_event")
public class RsEventEntity {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    private String eventName;

    private String keyWord;

}
