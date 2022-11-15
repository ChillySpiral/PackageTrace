package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.validation.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RecipientEntityTest {

    private static final Logger log = LoggerFactory.getLogger(RecipientEntityTest.class);

    @Autowired
    InputValidator validator;

    @Test
    @DisplayName("RecipientEntity: Validation")
    void validateWithAustria(){
       RecipientEntity recipient = new RecipientEntity();
       recipient.setCountry("Austria");

       recipient.setCity("Vienna");
       recipient.setStreet("Höchstädtpl. 6");
       recipient.setPostalCode("A-1200");
       recipient.setName("Max Mustermann");

        validator.validate(recipient);
    }

    @Test
    @DisplayName("RecipientEntity: Validation")
    void validateWithÖsterreich(){
        RecipientEntity recipient = new RecipientEntity();
        recipient.setCountry("Österreich");

        recipient.setCity("Vienna");
        recipient.setStreet("Höchstädtpl. 6");
        recipient.setPostalCode("A-1200");
        recipient.setName("Max Mustermann");

        validator.validate(recipient);
    }

    @Test
    @DisplayName("RecipientEntity: Validation")
    void failValidateWithAustria(){
        RecipientEntity recipient = new RecipientEntity();
        recipient.setCountry("Austria");

        recipient.setCity("Vien%na");
        recipient.setStreet("Höchstädtpl. 6%");
        recipient.setPostalCode("1200");
        recipient.setName("Max Mustermann");

        assertThrows(ConstraintViolationException.class, ()-> {
            validator.validate(recipient);
        });
    }

    @Test
    @DisplayName("RecipientEntity: Validation")
    void ignoreValidateWithoutAustria(){
        RecipientEntity recipient = new RecipientEntity();
        recipient.setCountry("Germany");

        recipient.setCity("Vien%na");
        recipient.setStreet("Höchstädtpl. 6%");
        recipient.setPostalCode("1200");
        recipient.setName("Max Mustermann");

        validator.validate(recipient);
    }
}