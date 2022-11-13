package at.fhtw.swen3.persistence.entities;

import lombok.*;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipientEntity {
    private String name;

    private String street;

    private String postalCode;

    private String city;

    private String country;
}
