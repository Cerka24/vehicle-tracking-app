package com.example.gps.tracker.repositories;

import com.example.gps.tracker.models.entities.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CoordinatesRepository extends JpaRepository<com.example.gps.tracker.models.entities.Coordinates, Long> {

    @Query("select c from Coordinates c where c.usersId = ?2 and DATE(c.timestamp) = ?1")
    List<Coordinates> findByTimestampAndUsersIdd(Timestamp timestamp1, Long userId);

    @Query("select c from Coordinates c where c.usersId = ?3 and c.timestamp >= ?1 and c.timestamp < ?2")
    List<Coordinates> findByTimestampAndUsersId(Timestamp timestamp1, Timestamp timestamp2, Long userId);;;;;
}
