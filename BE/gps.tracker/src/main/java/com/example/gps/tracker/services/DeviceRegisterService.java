package com.example.gps.tracker.services;

import com.example.gps.tracker.models.DevicesDTO;
import com.example.gps.tracker.models.entities.Devices;
import com.example.gps.tracker.models.entities.User;
import com.example.gps.tracker.repositories.UserRepository;
import com.example.gps.tracker.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceRegisterService {

    private final DeviceRepository deviceRepository;

    private final UserRepository userRepository;

    public DeviceRegisterService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<Devices> deviceRegistration(List<DevicesDTO> devicesDTOList) {
        List<Devices> devicesList = new ArrayList<>();
        for (DevicesDTO devicesDTO : devicesDTOList) {
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

            devicesList.add(devices);
            deviceRepository.save(devices);
        }
        return devicesList;
    }
}
