package at.fhtw.swen3.persistence.repositories;

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
class RecipientRepositoryTest
{

    @Autowired
    RecipientRepository repository;

    RecipientEntity recipient;

    @BeforeEach
    public void setupTest(){
        recipient = RecipientEntity.builder().name("Test").street("Testgasse 3").postalCode("A-1234").city("Vienna").country("Austria").build();
        System.out.println(recipient.toString());
        repository.save(recipient);
        System.out.println(recipient.toString());
    }

    @AfterEach
    public void testCleanup(){
        repository.delete(recipient);
    }

    @Test
    public void findByIdTest(){
        //Act
        java.util.Optional<RecipientEntity> result = repository.findById(recipient.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get().toString());

    }
    @Test
    public void findAllByName(){
        //Act
        java.util.List<RecipientEntity> result = repository.findAllByName(recipient.getName());

        //Assert
        assertEquals(1, result.size());
        System.out.println(result.get(0).toString());

    }

    @Test
    public void findByIdTest2(){
        //Arrange
        //RecipientEntity recipient = new RecipientEntity(1L, "Test", "Testgasse 3", "A-1234", "Vienna", "Austria");
        RecipientEntity recipient = RecipientEntity.builder().name("Abc").street("Abcgasse 3").postalCode("A-1234").city("Vienna").country("Austria").build();
        System.out.println(recipient.toString());
        repository.save(recipient);
        System.out.println(recipient.toString());
        //Act
        java.util.Optional<RecipientEntity> result = repository.findById(recipient.getId());

        //Assert
        assertTrue(result.isPresent());
        System.out.println(result.get().toString());
    }
}