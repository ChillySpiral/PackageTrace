package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class WarehouseEntity extends HopEntity {

    private Integer level;

    @OneToMany
    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

}
