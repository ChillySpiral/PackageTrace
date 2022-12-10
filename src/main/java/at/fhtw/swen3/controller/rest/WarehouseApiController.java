package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.Optional;

@Generated(value = "at.fhtw.swen3.openapitools.codegen.languages.SpringCodegen", date = "2022-10-14T19:35:25.076618Z[Etc/UTC]")
@Controller
@RequiredArgsConstructor
public class WarehouseApiController implements WarehouseApi {
    private NativeWebRequest request;

    private final WarehouseService service;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses() {
        try {
            var warehouseEntity = service.exportWarehouses();

            if (warehouseEntity.isPresent()) {
                var warehouse = (Warehouse) HopMapper.INSTANCE.entityToDto(warehouseEntity.get());
                return new ResponseEntity<>(warehouse, HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp) {
            //ToDo Sprint 5: Return Error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code) {
        try{
            var hopEntity = service.getWarehouse(code);

            if(hopEntity.isPresent()){
                var hop = HopMapper.INSTANCE.entityToDto(hopEntity.get());
                return new ResponseEntity<>(hop, HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp){
            //ToDo Sprint 5: Return Error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        try {
            var warehouseEntity = HopMapper.INSTANCE.dtoToEntity(warehouse);

            var success = service.importWarehouses(warehouseEntity);

            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                throw new Exception("Unknown Error");
            }
        } catch (Exception e) {
            //ToDo Sprint 5: Return Error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
