package app.damian.work.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MqttApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttApplication.class, args);
	}
}
