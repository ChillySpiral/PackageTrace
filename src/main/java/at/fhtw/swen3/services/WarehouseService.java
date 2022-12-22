package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exceptions.BLDataNotFoundException;
import at.fhtw.swen3.services.exceptions.BLValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface WarehouseService {
    boolean importWarehouses(HopEntity hopEntity) throws BLValidationException;

    Optional<WarehouseEntity> exportWarehouses() throws BLDataNotFoundException;

    Optional<HopEntity> getWarehouse(String code) throws BLDataNotFoundException;

}
