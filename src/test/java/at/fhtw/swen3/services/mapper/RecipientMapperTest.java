package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class RecipientMapperTest {
    private static final Logger log = LoggerFactory.getLogger(RecipientMapperTest.class);

    @Test
    @DisplayName("Recipient Entity: DTO to Entity")
    void dtoToEntity(){
        final Recipient recipient = new Recipient("12", "34", "56", "78", "90");

        RecipientEntity result = RecipientMapper.INSTANCE.dtoToEntity(recipient);

        assertEquals(recipient.getStreet(), result.getStreet());
        assertEquals(recipient.getName(), result.getName());
        assertEquals(recipient.getCity(), result.getCity());
        assertEquals(recipient.getCountry(), result.getCountry());
        assertEquals(recipient.getPostalCode(), result.getPostalCode());
    }

    @Test
    @DisplayName("GeoCoordinate Entity: Entity to DTO")
    void entityToDto(){
        final RecipientEntity recipient = new RecipientEntity(1L, "12", "34", "56", "78", "90");

        Recipient result = RecipientMapper.INSTANCE.entityToDto(recipient);

        assertEquals(recipient.getStreet(), result.getStreet());
        assertEquals(recipient.getName(), result.getName());
        assertEquals(recipient.getCity(), result.getCity());
        assertEquals(recipient.getCountry(), result.getCountry());
        assertEquals(recipient.getPostalCode(), result.getPostalCode());
    }
}