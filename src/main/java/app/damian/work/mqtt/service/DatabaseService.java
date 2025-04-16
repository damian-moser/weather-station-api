package app.damian.work.mqtt.service;

import app.damian.work.mqtt.model.SensorDataDto;
import app.damian.work.mqtt.model.SensorData;
import app.damian.work.mqtt.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final SensorDataRepository sensorDataRepository;

    public DatabaseService(SensorDataRepository temperatureRepository) {
        this.sensorDataRepository = temperatureRepository;
    }

    public List<SensorDataDto> getAllSensorData() {
        final List<SensorData> sensorData = sensorDataRepository.findAll();
        return sensorData.stream()
                .map(SensorDataDto::new)
                .toList();
    }

    public List<SensorDataDto> getSensorDataByType(String type) {
        final List<SensorData> sensorData = sensorDataRepository.findAllByType(type);
        return sensorData.stream()
                .map(SensorDataDto::new)
                .toList();
    }

    public void insertData(SensorData sensorData) {
        this.sensorDataRepository.insert(sensorData);
    }
}
