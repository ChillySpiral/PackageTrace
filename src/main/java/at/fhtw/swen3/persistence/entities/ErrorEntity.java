package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorEntity {

    @Id
    private Long id;

    private String errorMessage;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
