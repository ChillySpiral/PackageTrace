package at.fhtw.swen3.services.mapper.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ListMapper<ENT, DTO> implements BaseMapper<ENT, DTO> {
    public List<DTO> entityToDto(Collection<ENT> entities){
        List<DTO> result = new ArrayList<>();
        for(ENT entity : entities){
            result.add(entityToDto(entity));
        }
        return result;
    }

    public List<ENT> dtoToEntity(Collection<DTO> dtos){
        List<ENT> result = new ArrayList<>();
        for(DTO dto : dtos){
            result.add(dtoToEntity(dto));
        }
        return result;
    }
}
