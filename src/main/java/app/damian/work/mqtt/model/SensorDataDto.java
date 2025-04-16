package app.damian.work.mqtt.model;

import lombok.Data;

@Data
public class SensorDataDto {

    public SensorDataDto(SensorData sensorData) {
        this.sensorId = sensorData.getSensorId();
        this.type = sensorData.getType();
        this.value = sensorData.getValue();
        this.unit = sensorData.getUnit();
        this.timestamp = sensorData.getTimestamp();
    }

    private String sensorId;
    private String type;
    private double value;
    private String unit;
    private long timestamp;
}
