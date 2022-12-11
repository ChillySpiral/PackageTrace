package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WarehouseApiControllerTest {

    @Autowired
    WarehouseApiController controller;

    private static HopRepository repository;

    @Autowired
    private HopRepository repositoryInit;

    @PostConstruct
    private void initStaticDao () {
        repository = this.repositoryInit;
    }
    @AfterAll
    public static void cleanup(){
        var hopOne = repository.findByCode("ABCD123");

        repository.delete(hopOne);
    }

    @Test
    @Order(1)
    void importWarehouses() {
        Warehouse warehouse = Warehouse.builder().level(0).code("ABCD123").description("hi").locationCoordinates(new GeoCoordinate(12D,1D)).processingDelayMins(12).locationName("Vienne").hopType("sth").nextHops(new ArrayList<>()).build();

        ResponseEntity<Void> result = controller.importWarehouses(warehouse);
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    @Order(2)
    void getWarehouse() {
        String code = "ABCD123";
        ResponseEntity<Hop> result = controller.getWarehouse(code);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
        assertEquals("ABCD123", result.getBody().getCode());
    }

    @Test
    @Order(3)
    void exportWarehouses() {
        ResponseEntity<Warehouse> result = controller.exportWarehouses();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
    }
}