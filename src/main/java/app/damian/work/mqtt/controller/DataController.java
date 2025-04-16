package app.damian.work.mqtt.controller;

import app.damian.work.mqtt.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DatabaseService databaseService;

    public DataController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping
    public ResponseEntity<?> getData(@RequestParam(required = false) String type) {
        if (type != null) {
            return ResponseEntity.ok(databaseService.getSensorDataByType(type));
        } else {
            return ResponseEntity.ok(databaseService.getAllSensorData());
        }
    }
}
