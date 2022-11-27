package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.StateEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class HopArrivalRepositoryTest {
    @Autowired
    HopArrivalRepository repository;

    HopArrivalEntity hopArrivalEntity;

    @BeforeEach
    public void setupTest(){
        RecipientEntity recipient = RecipientEntity.builder().name("Anna Kleber").country("Germany").postalCode("D-3140").city("Hamburg").street("Landstrasse 3").build();
        RecipientEntity sender = RecipientEntity.builder().name("Franz Kleber").country("Norwegen").postalCode("N-6555").city("Oslo").street("Hundegasse 4").build();
        ParcelEntity parcel = ParcelEntity.builder().recipient(recipient).sender(sender).weight(2.4f).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).state(StateEnum.PICKUP).build();

        hopArrivalEntity = HopArrivalEntity.builder().code("ADBC33").dateTime(OffsetDateTime.MAX).description("test").parcel(parcel).build();
        System.out.println(hopArrivalEntity.toString());
        repository.save(hopArrivalEntity);
        System.out.println(hopArrivalEntity.toString());
    }

    @AfterEach
    public void testCleanup(){
       repository.delete(hopArrivalEntity);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<HopArrivalEntity> result = repository.findById(hopArrivalEntity.getId());

        //Assert
        assertTrue(result.isPresent());
        //ToDo ToString causing Stackoverflow
        assertEquals(hopArrivalEntity.getCode(), result.get().getCode());

    }
}