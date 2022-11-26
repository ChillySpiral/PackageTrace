package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class WarehouseNextHopsMapperTest {
    private static final Logger log = LoggerFactory.getLogger(GeoCoordinateMapperTest.class);

    @Test
    @DisplayName("GeoCoordinate Entity: DTO to Entity")
    void dtoToEntity(){
        final WarehouseNextHops warehouseNextHops = new WarehouseNextHops(1, new Hop("123", "456", "789", 1, "abc", new GeoCoordinate(45d, 46d)));

        WarehouseNextHopsEntity result = WarehouseNextHopsMapper.INSTANCE.dtoToEntity(warehouseNextHops);

        assertEquals(warehouseNextHops.getTraveltimeMins(), result.getTraveltimeMins());
        assertEquals(warehouseNextHops.getHop().getLocationCoordinates().getLat(), result.getHop().getLocationCoordinates().getLat());
        assertEquals(warehouseNextHops.getHop().getLocationCoordinates().getLon(), result.getHop().getLocationCoordinates().getLon());
        assertEquals(warehouseNextHops.getHop().getLocationName(), result.getHop().getLocationName());
        assertEquals(warehouseNextHops.getHop().getHopType(), result.getHop().getHopType());
        assertEquals(warehouseNextHops.getHop().getCode(), result.getHop().getCode());
        assertEquals(warehouseNextHops.getHop().getDescription(), result.getHop().getDescription());
        assertEquals(warehouseNextHops.getHop().getProcessingDelayMins(), result.getHop().getProcessingDelayMins());
    }

    @Test
    @DisplayName("GeoCoordinate Entity: Entity to DTO")
    void entityToDto(){
        final WarehouseNextHopsEntity warehouseNextHops = new WarehouseNextHopsEntity(1L, 1, new HopEntity(2L,"123", "456", "789", 1, "abc", new GeoCoordinateEntity(3L, 45d, 46d)));

        WarehouseNextHops result = WarehouseNextHopsMapper.INSTANCE.entityToDto(warehouseNextHops);

        assertEquals(warehouseNextHops.getTraveltimeMins(), result.getTraveltimeMins());
        assertEquals(warehouseNextHops.getHop().getLocationCoordinates().getLat(), result.getHop().getLocationCoordinates().getLat());
        assertEquals(warehouseNextHops.getHop().getLocationCoordinates().getLon(), result.getHop().getLocationCoordinates().getLon());
        assertEquals(warehouseNextHops.getHop().getLocationName(), result.getHop().getLocationName());
        assertEquals(warehouseNextHops.getHop().getHopType(), result.getHop().getHopType());
        assertEquals(warehouseNextHops.getHop().getCode(), result.getHop().getCode());
        assertEquals(warehouseNextHops.getHop().getDescription(), result.getHop().getDescription());
        assertEquals(warehouseNextHops.getHop().getProcessingDelayMins(), result.getHop().getProcessingDelayMins());
    }
}