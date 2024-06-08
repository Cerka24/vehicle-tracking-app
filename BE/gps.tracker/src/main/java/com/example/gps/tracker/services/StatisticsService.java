package com.example.gps.tracker.services;

import com.example.gps.tracker.models.*;
import com.example.gps.tracker.models.entities.Coordinates;
import com.example.gps.tracker.repositories.CoordinatesRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {
    private final CoordinatesRepository coordinatesRepository;

    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    public StatisticsService(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    public CarStatistics getTotal(Date date, Long userId) {

        Date date2 = new Date();

        setToStartDay(date2);

        List<Coordinates> coordinatesList = coordinatesRepository.findByTimestampAndUsersIdd(
                convertDateToTimestamp(setToStartDay(date)),
                userId);

        CarStatistics carStatistics = new CarStatistics();

        double timeUsage = 0;
        double totalSpeed = 0;
        double totalFuelConsumption = 0;
        double totalKilometers = 0;
        double distanceInKm = 0;
        int count = coordinatesList.size();
        if (count == 0) {
            return new CarStatistics();
        }
        double maxTime = coordinatesList.get(0).getTimestamp().getTime();
        double minTime = coordinatesList.get(0).getTimestamp().getTime();
        double minutes = 0;
        double hours = 0;

        for (int i = 1; i < coordinatesList.size(); i++) {

            double fuelConsumption = (coordinatesList.get(i).getDevice().getMaxConsumption()
                    + coordinatesList.get(i).getDevice().getMinConsumption()
                    + coordinatesList.get(i).getDevice().getAvgConsumption());

            totalKilometers += calculateDistance(coordinatesList.get(i).getLat(), coordinatesList.get(i - 1).getLat(),
                    coordinatesList.get(i).getLon(), coordinatesList.get(i - 1).getLon());

            if (coordinatesList.get(i).getTimestamp().getTime() < minTime) {
                minTime = coordinatesList.get(i).getTimestamp().getTime();
            } else if (coordinatesList.get(i).getTimestamp().getTime() > maxTime){
                maxTime = coordinatesList.get(i).getTimestamp().getTime();
            }

            timeUsage = maxTime - minTime;

            minutes = (timeUsage / 1000) / 60;

            hours = minutes / 60;

            totalSpeed += coordinatesList.get(i).getSpeed();
            totalFuelConsumption += fuelConsumption;
            totalKilometers += distanceInKm;

            carStatistics.setAverageSpeed(totalSpeed / count);
            carStatistics.setAverageFuelConsumption((totalFuelConsumption / totalKilometers) * 100);
            carStatistics.setAvgKilometers(totalKilometers);
            carStatistics.setAverageTimeUsage(hours);

        }
        return carStatistics;
    }

    public List<AvgKilometers> getAverageKilometers(Date date, Long userId) {
        Date date2 = new Date();

        setToStartDay(date2);

        List<Coordinates> coordinatesList = coordinatesRepository.findByTimestampAndUsersIdd(
                convertDateToTimestamp(setToStartDay(date)),
                userId);

        List<AvgKilometers> avgKilometersList = new ArrayList<>();

        double totalDistance = 0;
        for (int i = 1; i < coordinatesList.size(); i++) {

            AvgKilometers avgKilometers = new AvgKilometers();

            avgKilometers.setSerialNoRpi(coordinatesList.get(i).getDevice().getSerialNoRpi());

            totalDistance += calculateDistance(coordinatesList.get(i).getLat(), coordinatesList.get(i - 1).getLat(),
                    coordinatesList.get(i).getLon(), coordinatesList.get(i - 1).getLon());

            avgKilometers.setAvgKilometers(totalDistance);

            if (!avgKilometersList.contains(avgKilometers)) {
                avgKilometersList.add(avgKilometers);
            }

            totalDistance = 0;
        }

        return avgKilometersList;
    }

    public List<AvgTimeUsage> getAverageTimeUsage(Date date, Long userId) {
        Date date2 = new Date();

        setToStartDay(date2);

        List<Coordinates> coordinatesList = coordinatesRepository.findByTimestampAndUsersId(
                convertDateToTimestamp(setToStartDay(date)),
                convertDateToTimestamp(setToStartDay(date2)), userId);

        List<AvgTimeUsage> avgTimeUsageList = new ArrayList<>();

        double timeUsage = 0;

        double maxTime = coordinatesList.get(0).getTimestamp().getTime();

        double minTime = coordinatesList.get(0).getTimestamp().getTime();

        double minutes = 0;

        double hours = 0;

        for (Coordinates coordinates : coordinatesList) {

            AvgTimeUsage avgTimeUsage = new AvgTimeUsage();

            avgTimeUsage.setSerialNoRpi(coordinates.getDevice().getSerialNoRpi());

            if (coordinates.getTimestamp().getTime() < minTime) {
                minTime = coordinates.getTimestamp().getTime();
            } else if (coordinates.getTimestamp().getTime() > maxTime){
                maxTime = coordinates.getTimestamp().getTime();
            }

            timeUsage = maxTime - minTime;

            minutes = (timeUsage / 1000) / 60;

            hours = minutes / 60;

            avgTimeUsage.setAvgTimeUsage(hours);

            if (!avgTimeUsageList.contains(avgTimeUsage)) {
                avgTimeUsageList.add(avgTimeUsage);
            }
        }
        return avgTimeUsageList;
    }

    public List<AvgFuelConsumption> getAverageFuelConsumption(Date date, Long userId) {
        Date nextDate = new Date();

        setToStartDay(nextDate);

        List<Coordinates> coordinatesList = coordinatesRepository.findByTimestampAndUsersId(
                convertDateToTimestamp(setToStartDay(date)),
                convertDateToTimestamp(setToStartDay(nextDate)), userId);

        ArrayList<AvgFuelConsumption> avgFuelConsumptionsList = new ArrayList<>();

        double totalFuelConsumption = 0;
        double totalKilometers = 0;

        for (int i = 1; i < coordinatesList.size(); i++) {

            AvgFuelConsumption avgFuelConsumption = new AvgFuelConsumption();

            avgFuelConsumption.setSerialNoRpi(coordinatesList.get(i).getDevice().getSerialNoRpi());

            double fuelConsumption = (coordinatesList.get(i).getDevice().getMaxConsumption()
                    + coordinatesList.get(i).getDevice().getMinConsumption() +
                    coordinatesList.get(i).getDevice().getAvgConsumption());


            totalKilometers += calculateDistance(coordinatesList.get(i).getLat(), coordinatesList.get(i - 1).getLat(),
                    coordinatesList.get(i).getLon(), coordinatesList.get(i - 1).getLon());

            totalFuelConsumption += fuelConsumption;

            avgFuelConsumption.setAvgFuelConsumption((totalFuelConsumption / totalKilometers) * 100);

            if (!avgFuelConsumptionsList.contains(avgFuelConsumption)) {
                avgFuelConsumptionsList.add(avgFuelConsumption);
            }

            totalFuelConsumption = 0;
            totalKilometers = 0;

        }

        return avgFuelConsumptionsList;
    }

    public List<AvgSpeed> getAverageSpeed(Date date, Long userId) {
        Date nextDate = new Date();

        setToStartDay(nextDate);

        List<Coordinates> coordinatesList = coordinatesRepository.findByTimestampAndUsersId(
                convertDateToTimestamp(setToStartDay(date)),
                convertDateToTimestamp(setToStartDay(nextDate)), userId);

        double totalSpeed = 0;

        int count = coordinatesList.size();

        List<AvgSpeed> avgSpeedList = new ArrayList<>();

        for (int i = 1; i < coordinatesList.size(); i++) {

            AvgSpeed avgSpeed = new AvgSpeed();

            avgSpeed.setSerialNoRpi(coordinatesList.get(i).getDevice().getSerialNoRpi());

            totalSpeed += coordinatesList.get(i).getSpeed();

            avgSpeed.setAvgSpeed(totalSpeed / count);

            if (!avgSpeedList.contains(avgSpeed)) {
                avgSpeedList.add(avgSpeed);
            }
            totalSpeed = 0;
        }

        return avgSpeedList;
    }

    private Double calculateDistance(Double lat1, Double lat2, Double long1, Double long2) {

        double longDistance = Math.toRadians(long1 - long2);
        double latDistance = Math.toRadians(lat1 - lat2);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(longDistance / 2) * Math.sin(longDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return AVERAGE_RADIUS_OF_EARTH_KM * c;
    }

    private Timestamp convertDateToTimestamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);

        return new Timestamp(cal.getTimeInMillis());
    }

    private Date setToStartDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
