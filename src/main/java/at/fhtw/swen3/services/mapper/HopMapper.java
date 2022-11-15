package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HopMapper extends BaseMapper<HopEntity, Hop> {
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);
}
