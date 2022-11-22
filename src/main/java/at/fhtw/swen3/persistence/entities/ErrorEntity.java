package at.fhtw.swen3.persistence.entities;

import lombok.*;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorEntity {
    private String errorMessage;
}
