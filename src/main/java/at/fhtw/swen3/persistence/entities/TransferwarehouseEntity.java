package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class TransferwarehouseEntity extends HopEntity {

    @Column(length = 1000000)
    private String regionGeoJson;

    private String logisticsPartner;

    private String logisticsPartnerUrl;

}
