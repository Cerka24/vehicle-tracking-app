package com.example.gps.tracker.services;

import com.example.gps.tracker.models.DevicesDTO;
import com.example.gps.tracker.models.entities.Devices;
import com.example.gps.tracker.models.entities.User;
import com.example.gps.tracker.models.entities.Vehicle;
import com.example.gps.tracker.repositories.UserRepository;
import com.example.gps.tracker.repositories.DeviceRepository;
import com.example.gps.tracker.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceRegisterService {

    private final DeviceRepository deviceRepository;

    private final UserRepository userRepository;

    public DeviceRegisterService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }
    public Devices deviceRegistration(DevicesDTO devicesDTO) {
        Optional<User> user = userRepository.findById(devicesDTO.getUserId());

        if (user.isEmpty()) {
            throw new RuntimeException("No registered user with provided id");
        }
        Devices devices = new Devices();

        devices.setMaxConsumption(devicesDTO.getMaxConsumption());
        devices.setMinConsumption(devicesDTO.getMinConsumption());
        devices.setAvgConsumption(devicesDTO.getAvgConsumption());
        devices.setSerialNoRpi(devicesDTO.getSerialNoRpi());
        devices.setUser(user.get());

        deviceRepository.save(devices);
        return devices;
    }
}
