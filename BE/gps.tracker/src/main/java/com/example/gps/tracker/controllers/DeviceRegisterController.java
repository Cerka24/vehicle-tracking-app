package com.example.gps.tracker.controllers;

import com.example.gps.tracker.models.DevicesDTO;
import com.example.gps.tracker.models.entities.Devices;
import com.example.gps.tracker.services.DeviceRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/deviceRegistration")
@RestController
public class DeviceRegisterController {

    private final DeviceRegisterService deviceRegisterService;

    public DeviceRegisterController(DeviceRegisterService deviceRegisterService) {
        this.deviceRegisterService = deviceRegisterService;
    }
    @PostMapping
    public List<Devices> deviceRegistration(@RequestBody List<DevicesDTO> devicesDTOList) {
        return deviceRegisterService.deviceRegistration(devicesDTOList);
    }
}
