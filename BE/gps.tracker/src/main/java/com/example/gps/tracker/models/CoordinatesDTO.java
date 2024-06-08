package com.example.gps.tracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class CoordinatesDTO {
    private Integer serialNo;
    private Double lat;
    private Double lon;
    private Double speed;
    private Timestamp timestamp;
    private Date date;
    private Time time;
}
