package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends JpaRepository<RecipientEntity, Long> {

    List<RecipientEntity> findAllByPostalCode(String postalCode);
    List<RecipientEntity> findAllByCity(String city);
    List<RecipientEntity> findAllByCountry(String country);
    List<RecipientEntity> findAllByStreet(String street);
    List<RecipientEntity> findAllByName(String name);

}
