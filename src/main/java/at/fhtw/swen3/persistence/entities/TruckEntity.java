package at.fhtw.swen3.persistence.entities;

import lombok.*;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TruckEntity {
    private String regionGeoJson;

    private String numberPlate;
}
