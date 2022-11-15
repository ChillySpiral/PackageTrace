package at.fhtw.swen3.persistence.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RecipientEntityTest {

    private static final Logger log = LoggerFactory.getLogger(RecipientEntityTest.class);

    @Test
    @DisplayName("RecipientEntity: Validation")
    void validateWithAustria(){
       RecipientEntity recipient = new RecipientEntity();
       recipient.setCountry("Austria");

       recipient.setCity("Vienna");
       recipient.setStreet("Höchstädtpl. 6");
       recipient.setPostalCode("A-1200");
       recipient.setName("Max Mustermann");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(recipient);
        for (ConstraintViolation<RecipientEntity> violation : violations)
        {
            log.error(violation.getMessage());
        }

        assertTrue(violations.isEmpty());
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

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(recipient);
        for (ConstraintViolation<RecipientEntity> violation : violations)
        {
            log.error(violation.getMessage());
        }

        assertTrue(violations.isEmpty());
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

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(recipient);
        for (ConstraintViolation<RecipientEntity> violation : violations)
        {
            log.error(violation.getMessage());
        }

        assertEquals(3, violations.size());
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

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(recipient);
        for (ConstraintViolation<RecipientEntity> violation : violations)
        {
            log.error(violation.getMessage());
        }

        assertTrue(violations.isEmpty());
    }
}