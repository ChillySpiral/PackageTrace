package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class GeoCoordinateEntity {
    @Id
    private Long id;

    private Double lat;

    private Double lon;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
