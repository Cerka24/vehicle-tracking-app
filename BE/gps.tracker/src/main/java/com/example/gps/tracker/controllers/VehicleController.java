package com.example.gps.tracker.controllers;

import com.example.gps.tracker.models.VehicleDTO;
import com.example.gps.tracker.models.entities.Vehicle;
import com.example.gps.tracker.services.VehicleService;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/vehicleRegistration")
@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping
    public Vehicle deviceRegistration(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.addVehicle(vehicleDTO);
    }

    @GetMapping(value = "/{deviceId}")
    public Vehicle getTotal(
            @PathVariable Integer deviceId) {
        return vehicleService.getVehicle(deviceId);
    }
}
