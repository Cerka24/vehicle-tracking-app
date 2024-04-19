package com.example.gps.tracker.models;

import com.example.gps.tracker.models.entities.User;
import lombok.Data;

@Data
public class DevicesDTO {
    private Double maxConsumption;
    private Double minConsumption;
    private Double avgConsumption;
    private Integer serialNoRpi;
    private Long userId;
}
