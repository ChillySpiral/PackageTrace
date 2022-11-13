package at.fhtw.swen3.persistence.entities;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WarehouseEntity {
    private Integer level;

    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

}
