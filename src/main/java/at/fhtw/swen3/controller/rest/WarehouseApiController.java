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
        WarehouseEntity warehouseEntity = service.exportWarehouses();
        Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code) {
        HopEntity hopEntity = service.getWarehouse(code);

        if(hopEntity != null) {
            Hop hop = HopMapper.INSTANCE.entityToDto(hopEntity);
            return new ResponseEntity<>(hop, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
        /* ToDo: Activate with Sprint 4
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

         */

    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        boolean success = service.importWarehouses(warehouseEntity);

        if(success){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.OK);
        /* ToDo: Activate with Sprint 4
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         */

    }

}
