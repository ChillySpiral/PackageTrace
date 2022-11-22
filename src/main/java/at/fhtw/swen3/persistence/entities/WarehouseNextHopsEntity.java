package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WarehouseNextHopsEntity {
    @Id
    private Long id;

    private Integer traveltimeMins;

    @OneToOne
    private HopEntity hop;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
