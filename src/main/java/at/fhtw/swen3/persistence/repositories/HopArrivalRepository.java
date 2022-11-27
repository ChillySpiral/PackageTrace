package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface HopArrivalRepository extends JpaRepository<HopArrivalEntity, Long> {

    HopArrivalEntity findByCode(String code);
    HopArrivalEntity findByDateTime(OffsetDateTime dateTime);
    HopArrivalEntity findByDescription(String description);

}
