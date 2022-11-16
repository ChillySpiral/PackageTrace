package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransferwarehouseEntity {
    @Id
    private Long id;

    private String regionGeoJson;

    private String logisticsPartner;

    private String logisticsPartnerUrl;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
