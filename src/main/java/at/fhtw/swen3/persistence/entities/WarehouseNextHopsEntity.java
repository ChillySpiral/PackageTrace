package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer traveltimeMins;

    @OneToOne(cascade = CascadeType.ALL)
    private HopEntity hop;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
