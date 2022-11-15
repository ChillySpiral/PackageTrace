package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HopEntity {
    private String hopType;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s\\d-]+$")
    private String description;

    private Integer processingDelayMins;

    private String locationName;

    private GeoCoordinate locationCoordinates;

}
