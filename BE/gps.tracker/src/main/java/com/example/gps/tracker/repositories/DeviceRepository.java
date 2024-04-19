package com.example.gps.tracker.repositories;

import com.example.gps.tracker.models.entities.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Devices, Long> {

    Devices findBySerialNoRpi(Integer serialNoRpi);

}
