package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = ".*")
    private String name;
    @Pattern(regexp = ".*")
    private String street;

    @Pattern(regexp = ".*")
    private String postalCode;

    @Pattern(regexp = ".*")
    private String city;

    private String country;


    @AssertTrue(message = "Street Address does not match")
    private boolean isStreetOk(){
        if(country.equals("Austria") || country.equals("Österreich")){
            if(street.matches("^[A-ZÄÖÜ][a-zäöüß]*[.]*([-\\s][a-zA-Z][a-z]*[.]*)*(\\s\\w[-\\w/]*)*$")){
                return true;
            }
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Postal Code does not match")
    private boolean isPostalCodeOk(){
        if(country.equals("Austria") || country.equals("Österreich")){
            if(postalCode.matches("^A-\\d{4}$")){
                return true;
            }
            return false;
        }
        return true;
    }

    @AssertTrue(message = "City does not match")
    private boolean isCityOk(){
        if(country.equals("Austria") || country.equals("Österreich")){
            if(city.matches("^[A-ZÄÖÜ][a-zäöüß]*([-\\s][a-zA-ZÄÖÜäöü][a-zäöüß]*)*$")){
                return true;
            }
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Name does not match")
    private boolean isNameOk(){
        if(country.equals("Austria") || country.equals("Österreich")){
            if(name.matches("^[A-ZÄÖÜ][a-zäöüß]*([-\\s][a-zA-ZÄÖÜäöü][a-zäöüß]*)*$")){
                return true;
            }
            return false;
        }
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
