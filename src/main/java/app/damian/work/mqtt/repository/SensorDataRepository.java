package app.damian.work.mqtt.repository;

import app.damian.work.mqtt.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SensorDataRepository extends MongoRepository<SensorData, String> {

    List<SensorData> findAllByType(String type);
}
