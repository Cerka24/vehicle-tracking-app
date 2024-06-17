package com.example.gps.tracker.models;

import lombok.Data;

@Data
public class VehicleDTO {
    private String vehicleBrand;
    private String vehicleName;
    private String vehicleType;
    private Integer yearOfProduction;
    private Integer deviceId;
}
