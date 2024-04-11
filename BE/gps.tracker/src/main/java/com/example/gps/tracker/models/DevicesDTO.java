package com.example.gps.tracker.models;

import lombok.Data;

@Data
public class DevicesDTO {
    private Long device_id;
    private Double maxConsumption;
    private Double minConsumption;
    private Double avgConsumption;
    private Integer serialNoRpi;
}
