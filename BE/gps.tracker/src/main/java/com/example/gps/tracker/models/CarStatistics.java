package com.example.gps.tracker.models;

import lombok.Data;

@Data
public class CarStatistics {
    private DevicesDTO devicesDTO;
    private Double travelledDistance;
    private Double averageTimeUsage;
}
