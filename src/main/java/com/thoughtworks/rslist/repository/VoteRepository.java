package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {

    List<VoteEntity> findAll();

    List<VoteEntity> findAllByVoteTimeBetween(Timestamp startTime, Timestamp endTime);
}
