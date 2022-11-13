package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.*;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HopEntity {
    private String hopType;

    private String code;

    private String description;

    private Integer processingDelayMins;

    private String locationName;

    private GeoCoordinate locationCoordinates;

}
