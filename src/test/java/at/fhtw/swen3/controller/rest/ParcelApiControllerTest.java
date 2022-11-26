package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//ToDo: Fix with proper Implementation (Sprint 4)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParcelApiControllerTest {

    @Autowired
    ParcelApiController controller;

    @Test
    void reportParcelDelivery() {
    }

    @Test
    void reportParcelHop() {
    }

    @Order(1)
    @Test
    void submitParcel() {
        //Arrange
        Recipient recipient = Recipient.builder().name("Anna Kleber").country("Germany").postalCode("D-3140").city("Hamburg").street("Landstrasse 3").build();
        Recipient sender = Recipient.builder().name("Franz Kleber").country("Norwegen").postalCode("N-6555").city("Oslo").street("Hundegasse 4").build();
        Parcel parcel = Parcel.builder().recipient(recipient).sender(recipient).sender(sender).weight(2.4f).build();

        //Act
        ResponseEntity<NewParcelInfo> result = controller.submitParcel(parcel);

        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertTrue(result.hasBody());
        assertEquals("437898104", result.getBody().getTrackingId());
    }

    @Order(2)
    @Test
    void trackParcel() {
        //Arrange
        String trackingId = "437898104";

        //Act
        ResponseEntity<TrackingInformation> result = controller.trackParcel(trackingId);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
    }

    @Test
    void transitionParcel() {
    }
}