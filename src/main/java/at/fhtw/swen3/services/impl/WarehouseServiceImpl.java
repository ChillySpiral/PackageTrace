package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final InputValidator validator;

    private final HopRepository hopRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public boolean importWarehouses(HopEntity hopEntity) {
        log.info("Service: Import Warehouses with warehouse " + hopEntity.getCode());
        if (hopEntity instanceof WarehouseEntity warehouse) {
            validator.validate(warehouse);
        } else if (hopEntity instanceof TruckEntity truck) {
            validator.validate(truck);
        } else if (hopEntity instanceof TransferwarehouseEntity transferwarehouse) {
            validator.validate(transferwarehouse);
        }
        hopRepository.save(hopEntity);
        return true;
    }

    @Override
    public Optional<WarehouseEntity> exportWarehouses() {
        log.info("Service: Export Warehouses");
        var hopStart = warehouseRepository.findByLevel(0);
        return Optional.ofNullable(hopStart);
    }

    @Override
    public Optional<HopEntity> getWarehouse(String code) {
        log.info("Service: Get Warehouse with code " + code);
        var hopEntity = hopRepository.findByCode(code);

        return Optional.ofNullable(hopEntity);
    }
}
