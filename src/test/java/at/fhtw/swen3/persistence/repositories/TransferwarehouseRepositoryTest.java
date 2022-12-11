package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
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
class TransferwarehouseRepositoryTest {
    @Autowired
    TransferwarehouseRepository repository;

    TransferwarehouseEntity transferwarehouseEntity;

    @BeforeEach
    public void setupTest(){
        transferwarehouseEntity = TransferwarehouseEntity.builder().code("ABCD123").description("hi").locationCoordinates(GeoCoordinateEntity.builder().lon(45D).lat(46D).build()).processingDelayMins(12).locationName("Vienne").hopType("sth").logisticsPartner("Test1").logisticsPartnerUrl("...").build();
        System.out.println(transferwarehouseEntity.toString());
        repository.save(transferwarehouseEntity);
        System.out.println(transferwarehouseEntity.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(transferwarehouseEntity);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<TransferwarehouseEntity> result = repository.findById(transferwarehouseEntity.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get());
        assertEquals(transferwarehouseEntity.getCode(), result.get().getCode());

    }
}