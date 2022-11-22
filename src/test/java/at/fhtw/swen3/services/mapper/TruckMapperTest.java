package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TruckMapperTest {
    private static final Logger log = LoggerFactory.getLogger(GeoCoordinateMapperTest.class);

    @Test
    @DisplayName("Truck Entity: DTO to Entity")
    void dtoToEntity(){
        final Truck truck = new Truck("123", "456");

        TruckEntity result = TruckMapper.INSTANCE.dtoToEntity(truck);

        assertEquals(truck.getNumberPlate(), result.getNumberPlate());
        assertEquals(truck.getRegionGeoJson(), result.getRegionGeoJson());
    }

    @Test
    @DisplayName("Truck Entity: Entity to DTO")
    void entityToDto(){
        final TruckEntity truck = new TruckEntity(1L, "123", "456");

        Truck result = TruckMapper.INSTANCE.entityToDto(truck);

        assertEquals(truck.getNumberPlate(), result.getNumberPlate());
        assertEquals(truck.getRegionGeoJson(), result.getRegionGeoJson());
    }
}