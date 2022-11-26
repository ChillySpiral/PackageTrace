package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.rest.WarehouseApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class WarehouseApiControllerTest {

    @Autowired
    WarehouseApiController controller;

    @Test
    void exportWarehouses() {
    }

    @Test
    void getWarehouse() {
    }

    @Test
    void importWarehouses() {
    }
}