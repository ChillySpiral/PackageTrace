package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HopMapper{
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);

    @SubclassMapping(source = Warehouse.class, target = WarehouseEntity.class)
    @SubclassMapping(source = Truck.class, target = TruckEntity.class)
    @SubclassMapping(source = Transferwarehouse.class, target = TransferwarehouseEntity.class)
    public HopEntity dtoToEntity(Hop hop);

    @SubclassMapping(source = WarehouseEntity.class, target = Warehouse.class)
    @SubclassMapping(source = TruckEntity.class, target = Truck.class)
    @SubclassMapping(source = TransferwarehouseEntity.class, target = Transferwarehouse.class)
    public Hop entityToDto(HopEntity hopEntity);
}
