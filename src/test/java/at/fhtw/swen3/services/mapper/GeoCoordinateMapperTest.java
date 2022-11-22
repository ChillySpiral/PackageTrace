package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GeoCoordinateMapperTest {
    private static final Logger log = LoggerFactory.getLogger(GeoCoordinateMapperTest.class);

    @Test
    @DisplayName("GeoCoordinate Entity: DTO to Entity")
    void dtoToEntity(){
        final GeoCoordinate coordinate = new GeoCoordinate(45d, 58d);

        GeoCoordinateEntity result = GeoCoordinateMapper.INSTANCE.dtoToEntity(coordinate);

        assertEquals(coordinate.getLat(), result.getLat());
        assertEquals(coordinate.getLon(), result.getLon());
    }

    @Test
    @DisplayName("GeoCoordinate Entity: Entity to DTO")
    void entityToDto(){
        final GeoCoordinateEntity coordinate = new GeoCoordinateEntity(1L, 45d, 58d);

        GeoCoordinate result = GeoCoordinateMapper.INSTANCE.entityToDto(coordinate);

        assertEquals(coordinate.getLat(), result.getLat());
        assertEquals(coordinate.getLon(), result.getLon());
    }
}