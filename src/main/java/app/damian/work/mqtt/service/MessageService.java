package app.damian.work.mqtt.service;

import app.damian.work.mqtt.model.DataFromSensor;
import app.damian.work.mqtt.model.SensorData;
import app.damian.work.mqtt.handler.WebSocketHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private static final Logger logger = LogManager.getLogger(MessageService.class);

    private final TopicService topicService;
    private final DataServiceImpl databaseService;

    public MessageService(TopicService topicService, DataServiceImpl databaseService) {
        this.topicService = topicService;
        this.databaseService = databaseService;
    }

    public void handleMessage(Message<?> message) {
        final List<String> topics = this.topicService.getTopics();
        final String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();

        if (!topics.contains(topic)) {
            logger.warn("Topic {} is not in active topics list", topic);
            return;
        }

        try {
            final String json = message.getPayload().toString();
            final ObjectMapper mapper = new ObjectMapper();
            final DataFromSensor sensor = mapper.readValue(json, DataFromSensor.class);

            final SensorData sensorData = new SensorData();
            sensorData.setSensorId(sensor.getSensorId());
            sensorData.setType(topic);
            sensorData.setValue(sensor.getValue());
            sensorData.setUnit(sensor.getUnit());
            sensorData.setTimestamp(sensor.getTimestamp());

            final String sensorDataJson = mapper.writeValueAsString(sensorData);
            WebSocketHandler.sendToAll(sensorDataJson);
            this.databaseService.insertData(sensorData);
        } catch (JsonProcessingException e) {
            logger.error("Json processing error", e);
        }
    }
}
