package com.example.gps.tracker.services;

import com.example.gps.tracker.models.VehicleDTO;
import com.example.gps.tracker.models.entities.Devices;
import com.example.gps.tracker.models.entities.Vehicle;
import com.example.gps.tracker.repositories.DeviceRepository;
import com.example.gps.tracker.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DeviceRepository deviceRepository;

    public VehicleService(VehicleRepository vehicleRepository, DeviceRepository deviceRepository) {
        this.vehicleRepository = vehicleRepository;
        this.deviceRepository = deviceRepository;
    }
    public Vehicle addVehicle(VehicleDTO vehicleDTO) {
        Optional<Devices> devices = Optional.ofNullable(deviceRepository.findBySerialNoRpi(vehicleDTO.getDeviceId()));

        if (devices.isEmpty()) {
            throw new RuntimeException("No registered devices with provided id");
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleBrand(vehicleDTO.getVehicleBrand());
        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setYearOfProduction(vehicleDTO.getYearOfProduction());
        vehicle.setDevices(devices.get());

        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle getVehicle(Integer deviceSerialId) {
        Optional<Devices> devices = Optional.ofNullable(deviceRepository.findBySerialNoRpi(deviceSerialId));

        if (devices.isEmpty()) {
            throw new RuntimeException("No registered devices with provided id");
        }

        Optional<Vehicle> vehicleOptional = Optional.ofNullable(vehicleRepository.findByDevicesId(devices.get().getId()));
        if(vehicleOptional.isPresent()){
            return vehicleOptional.get();
        }
        throw new RuntimeException("Vehicle with device id:" + deviceSerialId + " does not exist!");
    }
}
