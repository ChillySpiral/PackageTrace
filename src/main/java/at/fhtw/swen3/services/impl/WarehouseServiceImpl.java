package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.exceptions.BLDataNotFoundException;
import at.fhtw.swen3.services.exceptions.BLValidationException;
import at.fhtw.swen3.services.exceptions.DALDataNotFondException;
import at.fhtw.swen3.services.exceptions.DALException;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final InputValidator validator;

    private final HopRepository hopRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public boolean importWarehouses(HopEntity hopEntity) throws BLValidationException {
        try {
            log.info("Service: Import Warehouses with warehouse " + hopEntity.getCode());
            if (hopEntity instanceof WarehouseEntity warehouse) {
                validator.validate(warehouse);
            } else if (hopEntity instanceof TruckEntity truck) {
                validator.validate(truck);
            } else if (hopEntity instanceof TransferwarehouseEntity transferwarehouse) {
                validator.validate(transferwarehouse);
            } else {
                return false;
            }
            hopRepository.save(hopEntity);
            return true;
        } catch (ConstraintViolationException exp) {
            log.info("Service: Import Warehouses with warehouse " + hopEntity.getCode() + " failed wit errormessage: " +  exp.getMessage());
            throw new BLValidationException(exp.getMessage(), "importWarehouses", "WarehouseServiceImpl", exp.getConstraintViolations().toString());
        }

    }

    @Override
    public Optional<WarehouseEntity> exportWarehouses() throws BLDataNotFoundException {
        try {
            log.info("Service: Export Warehouses");
            var hopStart = warehouseRepository.findByLevel(0);

            if(null == hopStart) {
                throw new DALDataNotFondException("No Warehouses could be found", "exportWarehouses", "WarehouseRepository");
            }

            return Optional.ofNullable(hopStart);
        } catch (DALDataNotFondException exp) {
            log.info("Service: Export Warehouses failed with errormessage: " +  exp.getMessage());
            throw new BLDataNotFoundException(exp.toString(), "exportWarehouses", "WarehouseServiceImpl");
        }

    }

    @Override
    public Optional<HopEntity> getWarehouse(String code) throws BLDataNotFoundException {
        try {
            log.info("Service: Get Warehouse with code " + code);
            var hopEntity = hopRepository.findByCode(code);

            if(null == hopEntity) {

                throw new DALDataNotFondException("Hop with code " + code + " could not be found", "findByCode", "HopRepository");
            }

            return Optional.ofNullable(hopEntity);
        } catch (DALDataNotFondException exp) {
            log.info("Service: Get Warehouse with code " + code + " failed with errormessage: " +  exp.getMessage());
            throw new BLDataNotFoundException(exp.toString(), "getWarehouse", "WarehouseServiceImpl");
        }

    }
}
