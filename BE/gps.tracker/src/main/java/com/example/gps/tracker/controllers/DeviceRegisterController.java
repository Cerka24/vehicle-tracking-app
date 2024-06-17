package com.example.gps.tracker.controllers;

import com.example.gps.tracker.models.DevicesDTO;
import com.example.gps.tracker.models.entities.Devices;
import com.example.gps.tracker.services.DeviceRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/deviceRegistration")
@RestController
public class DeviceRegisterController {

    private final DeviceRegisterService deviceRegisterService;

    public DeviceRegisterController(DeviceRegisterService deviceRegisterService) {
        this.deviceRegisterService = deviceRegisterService;
    }
    @PostMapping
    public Devices deviceRegistration(@RequestBody DevicesDTO devicesDTO) {
        return deviceRegisterService.deviceRegistration(devicesDTO);
    }
}
