package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HopMapperTest {
    private static final Logger log = LoggerFactory.getLogger(HopMapperTest.class);

    @Test
    @DisplayName("Hop Entity: DTO to Entity")
    void dtoToEntity(){
        final Hop hop = new Hop("123", "456", "789", 1, "ABC",new GeoCoordinate(45d, 46d));

        HopEntity result = HopMapper.INSTANCE.dtoToEntity(hop);

        assertEquals(hop.getHopType(), result.getHopType());
        assertEquals(hop.getCode(), result.getCode());
        assertEquals(hop.getDescription(), result.getDescription());
        assertEquals(hop.getProcessingDelayMins(), result.getProcessingDelayMins());
        assertEquals(hop.getLocationName(), result.getLocationName());
        assertEquals(hop.getLocationCoordinates().getLon(), result.getLocationCoordinates().getLon());
        assertEquals(hop.getLocationCoordinates().getLat(), result.getLocationCoordinates().getLat());
    }

    @Test
    @DisplayName("Hop Entity: Entity to DTO")
    void entityToDto(){
        final HopEntity hop = new HopEntity(1L, "123", "456", "789", 1, "ABC",new GeoCoordinateEntity(1L, 45d, 46d));

        Hop result = HopMapper.INSTANCE.entityToDto(hop);

        assertEquals(hop.getHopType(), result.getHopType());
        assertEquals(hop.getCode(), result.getCode());
        assertEquals(hop.getDescription(), result.getDescription());
        assertEquals(hop.getProcessingDelayMins(), result.getProcessingDelayMins());
        assertEquals(hop.getLocationName(), result.getLocationName());
        assertEquals(hop.getLocationCoordinates().getLon(), result.getLocationCoordinates().getLon());
        assertEquals(hop.getLocationCoordinates().getLat(), result.getLocationCoordinates().getLat());
    }
}