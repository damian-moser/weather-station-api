package app.damian.work.mqtt.service;

import app.damian.work.mqtt.model.SensorDataDto;
import app.damian.work.mqtt.model.SensorData;
import app.damian.work.mqtt.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private final SensorDataRepository sensorDataRepository;
    private final TopicService topicService;

    public DataServiceImpl(SensorDataRepository temperatureRepository, TopicService topicService) {
        this.sensorDataRepository = temperatureRepository;
        this.topicService = topicService;
    }

    public List<SensorDataDto> getAllSensorData() {
        final List<SensorData> sensorData = this.sensorDataRepository.findAll();
        return sensorData.stream()
                .map(SensorDataDto::new)
                .toList();
    }

    public List<SensorDataDto> getSensorDataByType(String type) {
        final List<SensorData> sensorData = this.sensorDataRepository.findAllByType(type);
        return sensorData.stream()
                .map(SensorDataDto::new)
                .toList();
    }

    public void insertData(SensorData sensorData) {
        this.sensorDataRepository.insert(sensorData);
    }

    public List<String> getTopics() {
        return this.topicService.getTopics();
    }
}
