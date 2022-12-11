package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface WarehouseService {
    boolean importWarehouses(HopEntity hopEntity);

    Optional<WarehouseEntity> exportWarehouses();

    Optional<HopEntity> getWarehouse(String code);

}
