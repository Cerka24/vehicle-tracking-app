package com.example.gps.tracker.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "coordinates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lon;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "user_id")
    private Long usersId;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Devices device;
}
