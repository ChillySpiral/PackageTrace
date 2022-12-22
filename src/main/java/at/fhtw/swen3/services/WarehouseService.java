package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;

import java.util.Optional;

public interface WarehouseService {
    boolean importWarehouses(HopEntity hopEntity) throws BLValidationException;

    Optional<WarehouseEntity> exportWarehouses() throws BLDataNotFoundException;

    Optional<HopEntity> getWarehouse(String code) throws BLDataNotFoundException;

}
