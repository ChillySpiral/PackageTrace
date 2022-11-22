package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferwarehouseMapperTest {
    private static final Logger log = LoggerFactory.getLogger(TransferwarehouseMapperTest.class);

    @Test
    @DisplayName("Transferwarehouse Entity: DTO to Entity")
    void dtoToEntity(){
        final Transferwarehouse transferwarehouse = new Transferwarehouse("123","456","789");

        TransferwarehouseEntity result = TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);

        assertEquals(transferwarehouse.getRegionGeoJson(), result.getRegionGeoJson());
        assertEquals(transferwarehouse.getLogisticsPartner(), result.getLogisticsPartner());
        assertEquals(transferwarehouse.getLogisticsPartnerUrl(), result.getLogisticsPartnerUrl());
    }

    @Test
    @DisplayName("Transferwarehouse Entity: Entity to DTO")
    void entityToDto(){
        final TransferwarehouseEntity transferwarehouse = new TransferwarehouseEntity(1L, "123","456","789");

        Transferwarehouse result = TransferwarehouseMapper.INSTANCE.entityToDto(transferwarehouse);

        assertEquals(transferwarehouse.getRegionGeoJson(), result.getRegionGeoJson());
        assertEquals(transferwarehouse.getLogisticsPartner(), result.getLogisticsPartner());
        assertEquals(transferwarehouse.getLogisticsPartnerUrl(), result.getLogisticsPartnerUrl());
    }
}