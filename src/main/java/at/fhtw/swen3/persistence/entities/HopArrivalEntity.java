package at.fhtw.swen3.persistence.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HopArrivalEntity {
    private String code;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateTime;
}
