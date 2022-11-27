package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class WarehouseMapperTest {
    private static final Logger log = LoggerFactory.getLogger(GeoCoordinateMapperTest.class);

    @Test
    @DisplayName("GeoCoordinate Entity: DTO to Entity")
    void dtoToEntity(){
        final List<WarehouseNextHops> wareHouseNextHop = new ArrayList<WarehouseNextHops>(){
            {
                add(new WarehouseNextHops(1, new Hop("123", "456", "789", 1, "abc", new GeoCoordinate(45d, 46d))));
                add(new WarehouseNextHops(2, new Hop("abc", "def", "ghi", 2, "123", new GeoCoordinate(78d, 99d))));
            }
        };

        final Warehouse warehouse = new Warehouse(3, wareHouseNextHop);

        WarehouseEntity result = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        assertEquals(warehouse.getLevel(), result.getLevel());

        assertEquals(warehouse.getNextHops().get(0).getTraveltimeMins(), result.getNextHops().get(0).getTraveltimeMins());
        assertEquals(warehouse.getNextHops().get(0).getHop().getLocationCoordinates().getLat(), result.getNextHops().get(0).getHop().getLocationCoordinates().getLat());
        assertEquals(warehouse.getNextHops().get(0).getHop().getLocationCoordinates().getLon(), result.getNextHops().get(0).getHop().getLocationCoordinates().getLon());
        assertEquals(warehouse.getNextHops().get(0).getHop().getLocationName(), result.getNextHops().get(0).getHop().getLocationName());
        assertEquals(warehouse.getNextHops().get(0).getHop().getHopType(), result.getNextHops().get(0).getHop().getHopType());
        assertEquals(warehouse.getNextHops().get(0).getHop().getCode(), result.getNextHops().get(0).getHop().getCode());
        assertEquals(warehouse.getNextHops().get(0).getHop().getDescription(), result.getNextHops().get(0).getHop().getDescription());
        assertEquals(warehouse.getNextHops().get(0).getHop().getProcessingDelayMins(), result.getNextHops().get(0).getHop().getProcessingDelayMins());

        assertEquals(warehouse.getNextHops().get(1).getTraveltimeMins(), result.getNextHops().get(1).getTraveltimeMins());
        assertEquals(warehouse.getNextHops().get(1).getHop().getLocationCoordinates().getLat(), result.getNextHops().get(1).getHop().getLocationCoordinates().getLat());
        assertEquals(warehouse.getNextHops().get(1).getHop().getLocationCoordinates().getLon(), result.getNextHops().get(1).getHop().getLocationCoordinates().getLon());
        assertEquals(warehouse.getNextHops().get(1).getHop().getLocationName(), result.getNextHops().get(1).getHop().getLocationName());
        assertEquals(warehouse.getNextHops().get(1).getHop().getHopType(), result.getNextHops().get(1).getHop().getHopType());
        assertEquals(warehouse.getNextHops().get(1).getHop().getCode(), result.getNextHops().get(1).getHop().getCode());
        assertEquals(warehouse.getNextHops().get(1).getHop().getDescription(), result.getNextHops().get(1).getHop().getDescription());
        assertEquals(warehouse.getNextHops().get(1).getHop().getProcessingDelayMins(), result.getNextHops().get(1).getHop().getProcessingDelayMins());
    }

    @Test
    @DisplayName("GeoCoordinate Entity: Entity to DTO")
    void entityToDto(){
        final List<WarehouseNextHopsEntity> wareHouseNextHop = new ArrayList<WarehouseNextHopsEntity>(){
            {
                add(new WarehouseNextHopsEntity(1L, 1, new HopEntity(3L, "123", "456", "789", 1, "abc", new GeoCoordinateEntity(5L, 45d, 46d))));
                add(new WarehouseNextHopsEntity(2L, 2, new HopEntity(4L, "abc", "def", "ghi", 2, "123", new GeoCoordinateEntity(5L, 78d, 99d))));
            }
        };

        final WarehouseEntity warehouse = new WarehouseEntity(3, wareHouseNextHop);

        Warehouse result = WarehouseMapper.INSTANCE.entityToDto(warehouse);

        assertEquals(warehouse.getLevel(), result.getLevel());

        assertEquals(warehouse.getNextHops().get(0).getTraveltimeMins(), result.getNextHops().get(0).getTraveltimeMins());
        assertEquals(warehouse.getNextHops().get(0).getHop().getLocationCoordinates().getLat(), result.getNextHops().get(0).getHop().getLocationCoordinates().getLat());
        assertEquals(warehouse.getNextHops().get(0).getHop().getLocationCoordinates().getLon(), result.getNextHops().get(0).getHop().getLocationCoordinates().getLon());
        assertEquals(warehouse.getNextHops().get(0).getHop().getLocationName(), result.getNextHops().get(0).getHop().getLocationName());
        assertEquals(warehouse.getNextHops().get(0).getHop().getHopType(), result.getNextHops().get(0).getHop().getHopType());
        assertEquals(warehouse.getNextHops().get(0).getHop().getCode(), result.getNextHops().get(0).getHop().getCode());
        assertEquals(warehouse.getNextHops().get(0).getHop().getDescription(), result.getNextHops().get(0).getHop().getDescription());
        assertEquals(warehouse.getNextHops().get(0).getHop().getProcessingDelayMins(), result.getNextHops().get(0).getHop().getProcessingDelayMins());

        assertEquals(warehouse.getNextHops().get(1).getTraveltimeMins(), result.getNextHops().get(1).getTraveltimeMins());
        assertEquals(warehouse.getNextHops().get(1).getHop().getLocationCoordinates().getLat(), result.getNextHops().get(1).getHop().getLocationCoordinates().getLat());
        assertEquals(warehouse.getNextHops().get(1).getHop().getLocationCoordinates().getLon(), result.getNextHops().get(1).getHop().getLocationCoordinates().getLon());
        assertEquals(warehouse.getNextHops().get(1).getHop().getLocationName(), result.getNextHops().get(1).getHop().getLocationName());
        assertEquals(warehouse.getNextHops().get(1).getHop().getHopType(), result.getNextHops().get(1).getHop().getHopType());
        assertEquals(warehouse.getNextHops().get(1).getHop().getCode(), result.getNextHops().get(1).getHop().getCode());
        assertEquals(warehouse.getNextHops().get(1).getHop().getDescription(), result.getNextHops().get(1).getHop().getDescription());
        assertEquals(warehouse.getNextHops().get(1).getHop().getProcessingDelayMins(), result.getNextHops().get(1).getHop().getProcessingDelayMins());
    }
}