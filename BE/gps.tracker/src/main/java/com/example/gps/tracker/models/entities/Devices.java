package com.example.gps.tracker.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "devices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_consumption")
    private Double maxConsumption;

    @Column(name = "min_consumption")
    private Double minConsumption;

    @Column(name = "avg_consumption")
    private Double avgConsumption;

    @Column(name = "serial_no_device")
    private Integer serialNoRpi;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
