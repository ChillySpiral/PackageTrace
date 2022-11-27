package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class WarehouseNextHopsRepositoryTest {
    @Autowired
    WarehouseNextHopsRepository repository;

    WarehouseNextHopsEntity warehouseNextHopsEntity;

    @BeforeEach
    public void setupTest(){
        warehouseNextHopsEntity = WarehouseNextHopsEntity.builder()
                .hop(
                        HopEntity.builder()
                        .hopType("Station")
                        .code("ABCZ66")
                        .description("Test")
                        .locationName("Australia")
                        .locationCoordinates(
                                GeoCoordinateEntity.builder()
                                .lon(45D)
                                .lat(46D)
                                .build())
                        .processingDelayMins(3)
                        .build()
                )
                .traveltimeMins(44)
                .build();
        System.out.println(warehouseNextHopsEntity.toString());
        repository.save(warehouseNextHopsEntity);
        System.out.println(warehouseNextHopsEntity.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(warehouseNextHopsEntity);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<WarehouseNextHopsEntity> result = repository.findById(warehouseNextHopsEntity.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get());
        assertEquals(warehouseNextHopsEntity.getTraveltimeMins(), result.get().getTraveltimeMins());

    }
}