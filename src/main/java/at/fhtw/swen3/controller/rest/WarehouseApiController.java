package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exceptions.BLDataNotFoundException;
import at.fhtw.swen3.services.exceptions.BLValidationException;
import at.fhtw.swen3.services.exceptions.DALDataNotFondException;
import at.fhtw.swen3.services.mapper.HopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.Optional;

@Generated(value = "at.fhtw.swen3.openapitools.codegen.languages.SpringCodegen", date = "2022-10-14T19:35:25.076618Z[Etc/UTC]")
@Controller
@RequiredArgsConstructor
@Slf4j
public class WarehouseApiController implements WarehouseApi {
    private NativeWebRequest request;

    private final WarehouseService service;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses() {
        log.info("Request: Export Warehouses");
        try {
            var warehouseEntity = service.exportWarehouses();

            var warehouse = (Warehouse) HopMapper.INSTANCE.entityToDto(warehouseEntity.get());
            log.info("Completed: Export Warehouses was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(warehouse, HttpStatus.OK);

        } catch (BLDataNotFoundException exp) {
            log.info("Service: Export Warehouses failed with errormessage: " +  exp.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception exp) {
            log.info("Service: Export Warehouses failed with errormessage: " +  exp.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code) {
        log.info("Request: Get Warehouse with code: " + code);
        try{
            var hopEntity = service.getWarehouse(code);

            var hop = HopMapper.INSTANCE.entityToDto(hopEntity.get());
            log.info("Completed: getWarehouse with code " + code + " was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(hop, HttpStatus.OK);

        } catch (BLDataNotFoundException exp) {
            log.info("Request: Get Warehouse with code: " + code + " failed with errormessage: " +  exp.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception exp) {
            log.error("Request Parameters: code: "+code+" Message: " +  exp.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        log.info("Import Warehouses: " + warehouse.getCode());
        try {
            var warehouseEntity = HopMapper.INSTANCE.dtoToEntity(warehouse);

            service.importWarehouses(warehouseEntity);

            log.info("Completed: importWarehouses with warehouse " + warehouse.getCode() + " was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (BLValidationException exp) {
            log.error("Import Warehouses with code " + warehouse.getCode() +" could not be imported because validation failed with errormessage " +  exp.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception exp) {
            log.error("Request Parameters: warehouse(code): "+warehouse.getCode()+" Message: " +  exp.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
