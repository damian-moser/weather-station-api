package app.damian.work.mqtt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "data")
public class SensorData {

    @Id
    private String id;
    private String sensorId;
    private String type;
    private double value;
    private String unit;
    private long timestamp;
}
