package at.fhtw.swen3.persistence.entities;

import lombok.*;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransferwarehouseEntity {
    private String regionGeoJson;

    private String logisticsPartner;

    private String logisticsPartnerUrl;
}
