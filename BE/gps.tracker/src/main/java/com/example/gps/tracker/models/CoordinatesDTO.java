package com.example.gps.tracker.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CoordinatesDTO {
    private Double lat;
    private Double lon;
    private Double speed;
    private Timestamp timestamp;
    private Integer usersId;
}
