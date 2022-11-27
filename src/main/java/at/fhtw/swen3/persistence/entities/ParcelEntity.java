package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.StateEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DecimalMin(value = "0.0")
    private Float weight;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private RecipientEntity recipient;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private RecipientEntity sender;

    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @NotNull
    private StateEnum state;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parcel", fetch = FetchType.EAGER)
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parcel", fetch = FetchType.EAGER)
    private List<HopArrivalEntity> futureHops = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
