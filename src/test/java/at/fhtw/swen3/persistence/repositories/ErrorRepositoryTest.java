package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ErrorRepositoryTest {
    @Autowired
    ErrorRepository repository;

    ErrorEntity error;

    @BeforeEach
    public void setupTest(){
        error = ErrorEntity.builder().errorMessage("Test message").build();
        System.out.println(error.toString());
        repository.save(error);
        System.out.println(error.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(error);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<ErrorEntity> result = repository.findById(error.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get());
        assertEquals(error.getErrorMessage(), result.get().getErrorMessage());

    }
}