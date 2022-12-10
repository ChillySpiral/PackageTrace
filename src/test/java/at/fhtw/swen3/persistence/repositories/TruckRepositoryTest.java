package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class TruckRepositoryTest {
    @Autowired
    TruckRepository repository;

    TruckEntity truckEntity;

    @BeforeEach
    public void setupTest(){
        truckEntity = TruckEntity.builder().code("ABCD123").description("hi").locationCoordinates(GeoCoordinateEntity.builder().lon(45D).lat(46D).build()).processingDelayMins(12).locationName("Vienne").hopType("sth").numberPlate("123sdf").build();
        System.out.println(truckEntity.toString());
        repository.save(truckEntity);
        System.out.println(truckEntity.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(truckEntity);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<TruckEntity> result = repository.findById(truckEntity.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get());
        assertEquals(truckEntity.getCode(), result.get().getCode());

    }
}