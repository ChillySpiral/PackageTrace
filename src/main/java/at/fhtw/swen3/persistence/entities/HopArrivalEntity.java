package at.fhtw.swen3.persistence.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HopArrivalEntity {
    @Id
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s\\d-]+$")
    private String description;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateTime;

    //ToDo: From where?
    @OneToOne
    private ParcelEntity parcel;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
