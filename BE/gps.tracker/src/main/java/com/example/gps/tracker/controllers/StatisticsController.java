package com.example.gps.tracker.controllers;

import com.example.gps.tracker.models.*;
import com.example.gps.tracker.services.StatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/statistics")
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/total/{userId}")
    public CarStatistics getTotal(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @PathVariable Long userId) {
        return statisticsService.getTotal(date, userId);
    }

    @GetMapping(value = "/avgSpeed/{userId}")
    public List<AvgSpeed> getAverageSpeed(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @PathVariable Long userId) {
        return statisticsService.getAverageSpeed(date, userId);
    }

    @GetMapping(value = "/avgKilometers/{userId}")
    public List<AvgKilometers> getAverageKilometers(
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
    @PathVariable Long userId) {
        return statisticsService.getAverageKilometers(date, userId);
    }

    @GetMapping(value = "/avgTimeUsage/{userId}")
    public List<AvgTimeUsage> getAverageTimeUsage(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @PathVariable Long userId) {
        return statisticsService.getAverageTimeUsage(date, userId);
    }

    @GetMapping(value = "/avgFuelConsumption/{userId}")
    public List<AvgFuelConsumption> getAverageFuelConsumption(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @PathVariable Long userId) {
        return statisticsService.getAverageFuelConsumption(date, userId);
    }
}
