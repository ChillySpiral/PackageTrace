package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ParcelApiControllerTest {

    @Autowired
    ParcelApiController controller;

    @Test
    void reportParcelDelivery() {
    }

    @Test
    void reportParcelHop() {
    }

    @Test
    void submitParcel() {
    }

    @Test
    void trackParcel() {
    }

    @Test
    void transitionParcel() {
    }
}