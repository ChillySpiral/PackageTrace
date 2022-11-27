package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class GeoCoordinateRepositoryTest {
    @Autowired
    GeoCoordinateRepository repository;

    GeoCoordinateEntity geoCoordinate;

    @BeforeEach
    public void setupTest(){
        geoCoordinate = GeoCoordinateEntity.builder().lat(45D).lon(48D).build();
        System.out.println(geoCoordinate.toString());
        repository.save(geoCoordinate);
        System.out.println(geoCoordinate.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(geoCoordinate);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<GeoCoordinateEntity> result = repository.findById(geoCoordinate.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get());
        assertEquals(geoCoordinate.getLat(), result.get().getLat());
        assertEquals(geoCoordinate.getLon(), result.get().getLon());

    }
}