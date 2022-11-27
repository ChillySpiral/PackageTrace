package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.TransferwarehouseRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final TruckRepository truckRepository;
    private final TransferwarehouseRepository tansferwarehouseRepository;
    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, TruckRepository truckRepository, TransferwarehouseRepository tansferwarehouseRepository) {
        this.warehouseRepository = warehouseRepository;
        this.truckRepository = truckRepository;
        this.tansferwarehouseRepository = tansferwarehouseRepository;
    }

    @Override
    public boolean importWarehouses(WarehouseEntity warehouse) {
        warehouseRepository.save(warehouse);
        return true;
    }

    @Override
    public WarehouseEntity exportWarehouses() {

        return new WarehouseEntity();
    }

    @Override
    public HopEntity getWarehouse(String code) {
        WarehouseEntity wareEntity = warehouseRepository.findByCode(code);
        if(null != wareEntity) {
            return wareEntity;
        }

        TruckEntity truckEntity = truckRepository.findByCode(code);
        if(null != truckEntity) {
            return truckEntity;
        }

        TransferwarehouseEntity transferwarehouseEntity = tansferwarehouseRepository.findByCode(code);
        if(null != transferwarehouseEntity) {
            return transferwarehouseEntity;
        }

        return null;
    }
}
