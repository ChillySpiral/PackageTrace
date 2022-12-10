package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.TransferwarehouseRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final InputValidator validator;
    private final WarehouseRepository warehouseRepository;
    private final TruckRepository truckRepository;
    private final TransferwarehouseRepository tansferwarehouseRepository;


    @Override
    public boolean importWarehouses(WarehouseEntity warehouse) {
        log.info("called importWarehouses with warehouse " + warehouse.toString());
        warehouseRepository.save(warehouse);
        return true;
    }

    @Override
    public WarehouseEntity exportWarehouses() {
        log.info("called exportWarehouses");
        log.info("returning empty");
        return new WarehouseEntity();
    }

    @Override
    public HopEntity getWarehouse(String code) {
        log.info("called getWarehouse with code " + code);

        WarehouseEntity wareEntity = warehouseRepository.findByCode(code);
        if(null != wareEntity) {
            log.info("returning WarehouseEntity");
            return wareEntity;
        }

        TruckEntity truckEntity = truckRepository.findByCode(code);
        if(null != truckEntity) {
            log.info("returning TruckEntity");
            return truckEntity;
        }

        TransferwarehouseEntity transferwarehouseEntity = tansferwarehouseRepository.findByCode(code);
        if(null != transferwarehouseEntity) {
            log.info("returning TransferwarehouseEntity");
            return transferwarehouseEntity;
        }

        log.info("returning null");
        return null;
    }
}
