package ru.netology.springboot;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private final static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private final static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> entityDev = restTemplate.getForEntity("http://localhost:" +
                devApp.getMappedPort(8080), String.class);
        System.out.println("Dev: " + entityDev.getBody());


        ResponseEntity<String> entityProd = restTemplate.getForEntity("http://localhost:" +
                devApp.getMappedPort(8081), String.class);
        System.out.println("Prod: " + entityProd.getBody());
    }

}