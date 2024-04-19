package com.example.gps.tracker.controllers;

import com.example.gps.tracker.models.CarStatistics;
import com.example.gps.tracker.services.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RequestMapping(value = "/statistics")
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/totalCarStatistics")
    public CarStatistics getTotalStatistics(Timestamp timestamp, Integer userId) {
        return statisticsService.getTotalStatistics(timestamp, userId);
    }
}
