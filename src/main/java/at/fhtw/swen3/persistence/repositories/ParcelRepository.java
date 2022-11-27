package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

    List<ParcelEntity> findAllByWeight(Float weight);
    ParcelEntity findByTrackingId(String trackingId);
    List<ParcelEntity> findAllByState(StateEnum state);

}
