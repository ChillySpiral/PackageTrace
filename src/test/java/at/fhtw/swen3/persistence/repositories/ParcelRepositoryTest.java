package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.StateEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ParcelRepositoryTest {
    @Autowired
    ParcelRepository repository;

    ParcelEntity parcel;

    @BeforeEach
    public void setupTest(){
        RecipientEntity recipient = RecipientEntity.builder().name("Anna Kleber").country("Germany").postalCode("D-3140").city("Hamburg").street("Landstrasse 3").build();
        RecipientEntity sender = RecipientEntity.builder().name("Franz Kleber").country("Norwegen").postalCode("N-6555").city("Oslo").street("Hundegasse 4").build();
        parcel = ParcelEntity.builder().recipient(recipient).sender(sender).weight(2.4f).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).state(StateEnum.PICKUP).build();


        System.out.println(this.parcel.toString());
        repository.save(this.parcel);
        System.out.println(this.parcel.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(parcel);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<ParcelEntity> result = repository.findById(parcel.getId());

        //Assert
        assertTrue(result.isPresent());
        assertEquals(parcel.getTrackingId(), result.get().getTrackingId());
        assertEquals(parcel.getWeight(), result.get().getWeight());


    }
}