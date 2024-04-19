package com.example.gps.tracker.repositories;

import com.example.gps.tracker.models.CarStatistics;
import com.example.gps.tracker.models.entities.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {

    CarStatistics findByTimestampAndUsersId(Timestamp timestamp, Integer usersId);
}
