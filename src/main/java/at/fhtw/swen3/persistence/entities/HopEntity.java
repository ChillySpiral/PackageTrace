package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String hopType;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-ZäüöÄßÜÖ\\s\\d-]+$")
    private String description;

    private Integer processingDelayMins;

    private String locationName;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private GeoCoordinateEntity locationCoordinates;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
