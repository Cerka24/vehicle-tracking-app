package com.example.gps.tracker.services;

import com.example.gps.tracker.models.CarStatistics;
import com.example.gps.tracker.repositories.CoordinatesRepository;
import com.example.gps.tracker.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class StatisticsService {
    private final DeviceRepository deviceRepository;
    private final CoordinatesRepository coordinatesRepository;

    public StatisticsService(DeviceRepository deviceRepository, CoordinatesRepository coordinatesRepository) {
        this.deviceRepository = deviceRepository;
        this.coordinatesRepository = coordinatesRepository;
    }

    public CarStatistics getTotalStatistics(Timestamp timestamp, Integer userId) {
        CarStatistics carStatistics;
        carStatistics = coordinatesRepository.findByTimestampAndUsersId(timestamp, userId);
        return carStatistics;
    }
}
