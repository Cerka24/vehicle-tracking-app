package com.example.gps.tracker.services;

import com.example.gps.tracker.models.CoordinatesDTO;
import com.example.gps.tracker.models.entities.Coordinates;
import com.example.gps.tracker.models.entities.Devices;
import com.example.gps.tracker.repositories.CoordinatesRepository;
import com.example.gps.tracker.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;
    private final DeviceRepository deviceRepository;

    public CoordinatesService(CoordinatesRepository coordinatesRepository, DeviceRepository deviceRepository) {
        this.coordinatesRepository = coordinatesRepository;
        this.deviceRepository = deviceRepository;
    }

    public void postGPSData(List<CoordinatesDTO> coordinatesDTOList) {
        for (CoordinatesDTO coordinatesDTO : coordinatesDTOList) {
            Devices devices = deviceRepository.findBySerialNoRpi(coordinatesDTO.getSerialNo());

            if (devices == null) {
                throw new RuntimeException("No device registered with provided serial number");
            }

            Coordinates coordinates = new Coordinates();

            coordinates.setUsersId(devices.getUser().getId());
            coordinates.setLat(coordinatesDTO.getLat());
            coordinates.setLon(coordinatesDTO.getLon());
            coordinates.setSpeed(coordinatesDTO.getSpeed());
            coordinates.setTimestamp(coordinatesDTO.getTimestamp());
            coordinates.setDate(coordinatesDTO.getDate());
            coordinates.setTime(coordinatesDTO.getTime());
            coordinates.setDevice(devices);

            coordinatesRepository.save(coordinates);
        }
    }
}
