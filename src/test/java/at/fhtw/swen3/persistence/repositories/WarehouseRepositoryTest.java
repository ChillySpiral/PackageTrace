package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class WarehouseRepositoryTest {
    @Autowired
    WarehouseRepository repository;

    WarehouseEntity warehouseEntity;

    @BeforeEach
    public void setupTest(){
        warehouseEntity = WarehouseEntity.builder().code("ABCD123").description("hi").locationCoordinates(GeoCoordinateEntity.builder().lon(45D).lat(46D).build()).processingDelayMins(12).locationName("Vienne").hopType("sth").nextHops(new ArrayList<>()).build();
        System.out.println(warehouseEntity.toString());
        repository.save(warehouseEntity);
        System.out.println(warehouseEntity.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(warehouseEntity);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<WarehouseEntity> result = repository.findById(warehouseEntity.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get());
        assertEquals(warehouseEntity.getCode(), result.get().getCode());

    }
}