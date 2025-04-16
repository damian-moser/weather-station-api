package app.damian.work.mqtt.controller;

import app.damian.work.mqtt.service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService databaseService) {
        this.dataService = databaseService;
    }

    @GetMapping
    public ResponseEntity<?> getData(@RequestParam(required = false) String type) {
        if (type != null) {
            return ResponseEntity.ok(this.dataService.getSensorDataByType(type));
        } else {
            return ResponseEntity.ok(this.dataService.getAllSensorData());
        }
    }

    @GetMapping("/topics")
    public ResponseEntity<?> getTopics() {
        return ResponseEntity.ok(this.dataService.getTopics());
    }
}
