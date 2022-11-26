package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ErrorMapperTest {

    private static final Logger log = LoggerFactory.getLogger(ErrorMapperTest.class);

    @Test
    @DisplayName("Error Entity: DTO to Entity")
    void dtoToEntity(){
        final Error error = new Error("This is a test");

        ErrorEntity result = ErrorMapper.INSTANCE.dtoToEntity(error);

        assertEquals(error.getErrorMessage(), result.getErrorMessage());
    }

    @Test
    @DisplayName("Error Entity: Entity to DTO")
    void entityToDto(){
        final ErrorEntity error = new ErrorEntity(1L, "This is a test");

        Error result = ErrorMapper.INSTANCE.entityToDto(error);

        assertEquals(error.getErrorMessage(), result.getErrorMessage());
    }
}