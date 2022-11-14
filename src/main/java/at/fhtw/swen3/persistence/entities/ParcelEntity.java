package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.StateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParcelEntity {
    private Float weight;

    private RecipientEntity recipient;

    private RecipientEntity sender;

    private String trackingId;

    private StateEnum state;

    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

    private List<HopArrivalEntity> futureHops = new ArrayList<>();
}
