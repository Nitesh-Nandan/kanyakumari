package org.kanyakumari.app.health;

import org.kanyakumari.dao.service.DummyDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @Value("spring.datasource.hikari.jdbc-url")
    private String value;

    @Autowired
    private DummyDaoService dummyDaoService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        dummyDaoService.create();
        return ResponseEntity.ok(value);
    }
}
