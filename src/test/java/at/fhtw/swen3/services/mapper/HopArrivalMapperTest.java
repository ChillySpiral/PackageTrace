package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class HopArrivalMapperTest {
    private static final Logger log = LoggerFactory.getLogger(HopArrivalMapperTest.class);

    @Test
    @DisplayName("Hop Arrival Entity: DTO to Entity")
    void dtoToEntity(){
        final HopArrival hopArrival = new HopArrival("123", "333", OffsetDateTime.MAX);

        HopArrivalEntity result = HopArrivalMapper.INSTANCE.dtoToEntity(hopArrival);

        assertEquals(hopArrival.getDateTime(), result.getDateTime());
        assertEquals(hopArrival.getCode(), result.getCode());
        assertEquals(hopArrival.getDescription(), result.getDescription());
    }

    @Test
    @DisplayName("Hop Arriva Entity: Entity to DTO")
    void entityToDto(){
        final HopArrivalEntity hopArrival = new HopArrivalEntity(1L, "123", "333", OffsetDateTime.MAX, new ParcelEntity());

        HopArrival result = HopArrivalMapper.INSTANCE.entityToDto(hopArrival);

        assertEquals(hopArrival.getDateTime(), result.getDateTime());
        assertEquals(hopArrival.getCode(), result.getCode());
        assertEquals(hopArrival.getDescription(), result.getDescription());
    }
}