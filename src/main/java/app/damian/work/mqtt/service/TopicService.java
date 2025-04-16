package app.damian.work.mqtt.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class TopicService {

    @Value("${mqtt.topics.filename}")
    private String TOPIC_FILENAME;

    @Getter
    private List<String> topics;

    @PostConstruct
    public void init() throws IOException {
        Path path = new ClassPathResource(TOPIC_FILENAME).getFile().toPath();
        this.topics = Files.readAllLines(path);
    }
}
