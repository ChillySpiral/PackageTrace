package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.TransferwarehouseRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final InputValidator validator;
    private final WarehouseRepository warehouseRepository;
    private final TruckRepository truckRepository;
    private final TransferwarehouseRepository tansferwarehouseRepository;

    private final HopRepository hopRepository;


    @Override
    public boolean importWarehouses(HopEntity hopEntity) {
        log.info("called importWarehouses with warehouse " + warehouse.toString());
        if(hopEntity instanceof WarehouseEntity warehouse) {
            validator.validate(warehouse);
        } else if (hopEntity instanceof TruckEntity truck) {
            validator.validate(truck);
        } else if(hopEntity instanceof TransferwarehouseEntity transferwarehouse){
            validator.validate(transferwarehouse);
        }
        hopRepository.save(hopEntity);
        return true;
    }

    @Override
    public Optional<WarehouseEntity> exportWarehouses() {
        log.info("called exportWarehouses");
        return Optional.of(new WarehouseEntity());
    }

    @Override
    public Optional<HopEntity> getWarehouse(String code) {
        log.info("called getWarehouse with code " + code);
        WarehouseEntity wareEntity = warehouseRepository.findByCode(code);
        if(null != wareEntity) {
            log.info("returning WarehouseEntity");
            return Optional.of(wareEntity);
        }

        TruckEntity truckEntity = truckRepository.findByCode(code);
        if(null != truckEntity) {
            log.info("returning TruckEntity");
            return Optional.of(truckEntity);
        }

        TransferwarehouseEntity transferwarehouseEntity = tansferwarehouseRepository.findByCode(code);
        if(null != transferwarehouseEntity) {
            log.info("returning TransferwarehouseEntity");
            return Optional.of(transferwarehouseEntity);
        }
        log.info("returning null");
        return null;
    }
}
