package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
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

            if (warehouseEntity.isPresent()) {
                var warehouse = (Warehouse) HopMapper.INSTANCE.entityToDto(warehouseEntity.get());
                log.info("Completed: exportWarehouses was successfully executed, HttpStatus.OK");
                return new ResponseEntity<>(warehouse, HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp) {
            //ToDo Sprint 5: Return Error
            log.error("Message: " +  exp.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code) {
        log.info("Request: Get Warehouse with code: " + code);
        try{
            var hopEntity = service.getWarehouse(code);

            if(hopEntity.isPresent()){
                var hop = HopMapper.INSTANCE.entityToDto(hopEntity.get());
                log.info("Completed: getWarehouse with code " + code + " was successfully executed, HttpStatus.OK");
                return new ResponseEntity<>(hop, HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp){
            log.error("Request Parameters: code: "+code+" Message: " +  exp.getMessage());
            //ToDo Sprint 5: Return Error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        log.info("Import Warehouses: " + warehouse.getCode());
        try {
            var warehouseEntity = HopMapper.INSTANCE.dtoToEntity(warehouse);

            var success = service.importWarehouses(warehouseEntity);

            if (success) {
                log.info("Completed: importWarehouses with warehouse " + warehouse.getCode() + " was successfully executed, HttpStatus.OK");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp) {
            log.error("Request Parameters: warehouse(code): "+warehouse.getCode()+" Message: " +  exp.getMessage());
            //ToDo Sprint 5: Return Error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
