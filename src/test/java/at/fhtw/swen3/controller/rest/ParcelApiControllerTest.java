package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParcelApiControllerTest {

    @Autowired
    ParcelApiController controller;

    private static ParcelRepository repository;

    @Autowired
    private ParcelRepository repositoryInit;

    @PostConstruct
    private void initStaticDao () {
        repository = this.repositoryInit;
    }
    @AfterAll
    public static void cleanup(){
        var parcelOne = repository.findByTrackingId("437898104");
        var parcelTwo = repository.findByTrackingId("PYJRB4HZ6");

        repository.delete(parcelOne);
        repository.delete(parcelTwo);
    }

    @Order(1)
    @Test
    void submitParcel() {
        //Arrange
        Recipient recipient = Recipient.builder().name("Anna Kleber").country("Germany").postalCode("D-3140").city("Hamburg").street("Landstrasse 3").build();
        Recipient sender = Recipient.builder().name("Franz Kleber").country("Norwegen").postalCode("N-6555").city("Oslo").street("Hundegasse 4").build();
        Parcel parcel = Parcel.builder().recipient(recipient).sender(sender).weight(2.4f).build();

        //Act
        ResponseEntity<NewParcelInfo> result = controller.submitParcel(parcel);

        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertTrue(result.hasBody());
        assertEquals("PYJRB4HZ6", result.getBody().getTrackingId());
    }

    @Order(2)
    @Test
    void trackParcel() {
        //Arrange
        String trackingId = "PYJRB4HZ6";

        //Act
        ResponseEntity result = controller.trackParcel(trackingId);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
    }

    @Test
    @Order(3)
    void reportParcelDelivery() {
        //Arrange
        String trackingId = "PYJRB4HZ6";

        //Act
        ResponseEntity<Void> result = controller.reportParcelDelivery(trackingId);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @Order(4)
    void reportParcelHop() {
        //Arrange
        String trackingId = "PYJRB4HZ6";
        String code = "MOON991";

        //Act
        ResponseEntity<Void> result = controller.reportParcelHop(trackingId, code);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void transitionParcel() {
        //Arrange
        Recipient recipient = Recipient.builder().name("Klara Hanger").country("Polen").postalCode("P-3340").city("Warschau").street("Landstrasse 30").build();
        Recipient sender = Recipient.builder().name("Leni Jaeger").country("Frankreich").postalCode("F-4231").city("Lyon").street("Rue de Place 15").build();
        Parcel parcel = Parcel.builder().recipient(recipient).sender(recipient).sender(sender).weight(5.4f).build();

        //Act
        ResponseEntity<NewParcelInfo> result = controller.transitionParcel( "437898104", parcel);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
        assertEquals("437898104", result.getBody().getTrackingId());
    }
}