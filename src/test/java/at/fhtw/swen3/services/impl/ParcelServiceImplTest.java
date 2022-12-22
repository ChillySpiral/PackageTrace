package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.StateEnum;
import at.fhtw.swen3.services.exceptions.BLDataNotFoundException;
import at.fhtw.swen3.services.exceptions.BLValidationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParcelServiceImplTest {

    @Autowired
    public ParcelService service;

    private static ParcelRepository repository;

    @Autowired
    private ParcelRepository repositoryInit;

    @PostConstruct
    private void initStaticDao () {
        repository = this.repositoryInit;
    }

    public ParcelEntity parcel;
    @BeforeEach
    public void initParcel(){
        var hop1 = HopArrivalEntity.builder()
                .code("WTTA089")
                .dateTime(OffsetDateTime.now())
                .description("Hop 1")
                .build();

        var hop2 = HopArrivalEntity.builder()
                .code("WTTA01")
                .dateTime(OffsetDateTime.now())
                .description("Hop 2")
                .build();

        var hop3 = HopArrivalEntity.builder()
                .code("WENB01")
                .dateTime(OffsetDateTime.now())
                .description("Hop 3")
                .build();

        var visitedHopsInitList = new ArrayList<HopArrivalEntity>();
        visitedHopsInitList.add(hop1);

        var futureHopsInitList = new ArrayList<HopArrivalEntity>();
        futureHopsInitList.add(hop2);
        futureHopsInitList.add(hop3);

        var recipient = RecipientEntity.builder()
                .name("Anna Kleber")
                .country("Germany")
                .postalCode("D-3140")
                .city("Hamburg")
                .street("Landstrasse 3")
                .build();

        var sender = RecipientEntity.builder()
                .name("Franz Kleber")
                .country("Norwegen")
                .postalCode("N-6555")
                .city("Oslo")
                .street("Hundegasse 4")
                .build();

        var initParcel = ParcelEntity.builder()
                .recipient(recipient)
                .sender(sender)
                .weight(2.4f)
                .state(StateEnum.UNKNOWN)
                .visitedHops(visitedHopsInitList)
                .futureHops(futureHopsInitList)
                .build();

        initParcel.getVisitedHops().get(0).setParcel(initParcel);
        initParcel.getFutureHops().get(0).setParcel(initParcel);
        initParcel.getFutureHops().get(1).setParcel(initParcel);

        parcel = initParcel;
    }

    @AfterAll
    public static void cleanup(){
        var parcelOne = repository.findByTrackingId("ZEHH489IS");
        var parcelTwo = repository.findByTrackingId("PYJRB4HZ6");

        repository.delete(parcelOne);
        repository.delete(parcelTwo);
    }

    @Test
    @Order(1)
    public void submitParcel(){
        //Arrange
        var submitParcel = parcel;

        //Act
        Optional<ParcelEntity> res = null;
        try {
            res = service.submitParcel(submitParcel);
        } catch (BLValidationException e) {
            fail("Exception thrown");
        }

        //Assert
        assertTrue(res.isPresent());
        assertEquals("PYJRB4HZ6",res.get().getTrackingId());

    }

    @Test
    @Order(2)
    public void trackParcel(){
        //Arrange
        var trackingId = "PYJRB4HZ6";
        //Act
        Optional<ParcelEntity> res = null;
        try {
            res = service.trackParcel(trackingId);
        } catch (BLDataNotFoundException e) {
            fail("Exception thrown");
        }

        //Assert
        assertTrue(res.isPresent());
        assertEquals("PYJRB4HZ6", res.get().getTrackingId());
        assertEquals(parcel.getSender().getName(), res.get().getSender().getName());
        assertEquals(parcel.getRecipient().getName(), res.get().getRecipient().getName());
    }

    @Test
    @Order(3)
    public void reportParcelHop(){
        //Arrange
        var trackingId = "PYJRB4HZ6";
        var visitedHop = "WTTA01";

        //Act
        boolean res = false;
        try {
            res = service.reportParcelHop(trackingId, visitedHop);
        } catch (BLDataNotFoundException e) {
            fail("Exception thrown");
        }

        //Assert
        assertTrue(res);
    }

    @Test
    @Order(4)
    public void trackParcelNewHop(){
        //Arrange
        var trackingId = "PYJRB4HZ6";

        //Act
        Optional<ParcelEntity> res = null;
        try {
            res = service.trackParcel(trackingId);
        } catch (BLDataNotFoundException e) {
            fail("Exception thrown");
        }

        //Assert
        assertTrue(res.isPresent());
        /* ToDo: Issue with List Saving
        assertEquals(2, res.get().getVisitedHops().size());
        assertEquals(1, res.get().getFutureHops().size());
        assertEquals(parcel.getFutureHops().get(0).toString(), res.get().getVisitedHops().get(1).toString());
         */
    }

    @Test
    @Order(5)
    public void reportParcelDelivery(){
        //Arrange
        var trackingId = "PYJRB4HZ6";

        //Act
        boolean res = false;
        try {
            res = service.reportParcelDelivery(trackingId);
        } catch (BLDataNotFoundException e) {
            fail("Exception thrown");
        }

        //Assert
        assertTrue(res);
    }

    @Test
    @Order(7)
    public void trackParcelDeliverd(){
        //Arrange
        var trackingId = "PYJRB4HZ6";

        //Act
        Optional<ParcelEntity> res = null;
        try {
            res = service.trackParcel(trackingId);
        } catch (BLDataNotFoundException e) {
            fail("Exception thrown");
        }

        //Assert
        assertTrue(res.isPresent());
        assertEquals(StateEnum.DELIVERED, res.get().getState());
    }

    @Test
    @Order(6)
    public void transitionParcel(){
        //Arrange
        var transitionParcel = parcel;
        transitionParcel.setTrackingId("ZEHH489IS");

        //Act
        Optional<ParcelEntity> res = null;
        try {
            res = service.transitionParcel(parcel);
        } catch (BLValidationException e) {
            fail("Exception thrown");
        }

        //Assert
        assertTrue(res.isPresent());
    }
}