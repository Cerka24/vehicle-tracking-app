Vehicle Tracking Application


API Specification:

•	POST-COORDINATES (longitude, latitude, timestamp, speed, deviceId)

•	POST-REGISTER (username, password)

•	POST-DEVICE-REGISTER (maxConsumption, minConsumption, avgConsumption, serialNoRpi)

•	POST-LOGIN (username, password(hashPassword))

•	GET-TOTAL (Date, userID) – returns object (CarStatistics(avgFuelConsumption, avgKilometers, avgTimeUsage, avgSpeed)

•	GET-AVERAGE-KM (Date, userID) – returns list of Objects(serialNoDevice, avgKilometersForThatDay) 

•	GET-AVERAGE-SPEED (Date, userID) - returns list of Objects(serialNoDevice, avgSpeedForThatDay)

•	GET-AVERAGE-TIMEUSAGE (Date, userID) - returns list of Objects(serialNoDevice, avgTimeUsageForThatDay)

•	GET-AVERAGE-FUEL-CONSUMPTION (Date, userID) - returns list of Objects(serialNoDevice, avgFuelConsumptionForThatDay)

ERD

<img width="250" alt="image" src="https://github.com/Cerka24/vehicle-tracking-app/assets/105603113/c9f3b0c7-8462-4876-8afb-06e78880bd0e">


<img width="330" alt="image" src="https://github.com/Cerka24/vehicle-tracking-app/assets/105603113/e388bdf1-8e2c-4a62-b48b-f6fe9c54c744">
