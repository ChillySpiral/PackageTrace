package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecipientEntity {
    private String name;
    //@Pattern(regexp = "^[A-Z][a-z]*([-\\s][a-zA-Z][a-z]*)*\\s\\w[-\\w\\/]*$")
    private String street;

    //@Pattern(regexp = "^A-\\d{4}$")
    private String postalCode;

    //@Pattern(regexp = "^[A-Z][a-z]*([-\\s][a-zA-Z][a-z]*)*$")
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
}
