package com.example.gps.tracker.repositories;

import com.example.gps.tracker.models.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByDevicesId(Long deviceId);
}
