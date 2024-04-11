package com.example.gps.tracker.services;

import com.example.gps.tracker.models.CoordinatesDTO;
import com.example.gps.tracker.models.entities.Coordinates;
import com.example.gps.tracker.repositories.CoordinatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;

    public CoordinatesService(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    public void postGPSData(List<CoordinatesDTO> coordinatesDTOList) {
        for (CoordinatesDTO coordinatesDTO : coordinatesDTOList) {
            Coordinates coordinates = new Coordinates();

            coordinates.setLat(coordinatesDTO.getLat());
            coordinates.setLon(coordinatesDTO.getLon());
            coordinates.setSpeed(coordinatesDTO.getSpeed());
            coordinates.setTimestamp(coordinatesDTO.getTimestamp());
            coordinates.setUsersId(coordinatesDTO.getUsersId());

            coordinatesRepository.save(coordinates);
        }
    }
}
