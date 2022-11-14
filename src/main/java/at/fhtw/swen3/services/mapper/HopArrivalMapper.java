package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HopArrivalMapper extends BaseMapper<HopArrivalEntity, HopArrival> {
    HopArrivalMapper INSTANCE = Mappers.getMapper(HopArrivalMapper.class);
}
