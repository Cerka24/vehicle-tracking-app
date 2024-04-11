import serial
import pynmea2
import time

port = "/dev/ttyAMAO"
serialPort = serial.Serial(port, baudrate = 9600, timeout = 30)

class GPSData:
  def __init__(self, lat, lon, timeData, dateData, speed):
    self.lat = lat
    self.lon = lon
    self.timeData = timeData
    self.dateData = dateData
    self.speed = speed

gpsDataList = []
count = 0

while True:
    # read data
    gpsData = serialPort.readline()

    # parse data
    newGPSData = gpsData.decode('latin-1')
    if newGPSData[0:6] == "§GPRMC":
        newData = pynmea2.parse(newGPSData)

        record = GPSData(
           newData.latitude,
           newData.longitude,
           newData.timestamp.strftime('%H:%M:%S'),
           newData.datestamp.strftime('%Y-%m-%d'),
           newData.spd_over_grnd * 1.852
        )

        gpsDataList.append(record)


    # send data
    if count == 50:
       #sending data (api)
       gpsDataList.clear
       count = 0
    count = count + 1

    # sleep thread
    time.sleep(5)
