package app.damian.work.mqtt.service;

import app.damian.work.mqtt.model.SensorData;
import app.damian.work.mqtt.model.SensorDataDto;

import java.util.List;

public interface DataService {

    List<SensorDataDto> getAllSensorData();

    List<SensorDataDto> getSensorDataByType(String type);

    void insertData(SensorData sensorData);

    List<String> getTopics();
}
