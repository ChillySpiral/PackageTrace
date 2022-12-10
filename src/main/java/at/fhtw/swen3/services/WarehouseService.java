package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface WarehouseService {
    public boolean importWarehouses(HopEntity hopEntity);

    public WarehouseEntity exportWarehouses();

    public HopEntity getWarehouse(String code);

}
