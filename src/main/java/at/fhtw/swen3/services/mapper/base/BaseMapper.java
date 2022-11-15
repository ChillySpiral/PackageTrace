package at.fhtw.swen3.services.mapper.base;

public interface BaseMapper <ENT, DTO>{
    DTO entityToDto(ENT entity);
    ENT dtoToEntity(DTO dto);
}
