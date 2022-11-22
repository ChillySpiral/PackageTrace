package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HopEntity {
    @Id
    private Long id;

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

    @NotNull
    @OneToOne
    private GeoCoordinateEntity locationCoordinates;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
