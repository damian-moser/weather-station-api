package app.damian.work.mqtt.model;

import lombok.Data;

@Data
public class DataFromSensor {

    private String sensorId;
    private double value;
    private String unit;
    private long timestamp;
}
