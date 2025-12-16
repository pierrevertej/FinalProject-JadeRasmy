package org.pierrevertej;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        }

        else {
            this.streetNo = -1;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }

    /**
     * checks if a postcode is valid or not. The length of a postal code can only be `6`.
     * 1. the postcode must follow the format: `CDCDCD`, where `C` is a character, while `D` is a digit, such as `A1B2C3`.
      * @param postalCode the postal code to check
     * @return true or false depending on if its valid
     */
    private static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null || postalCode.length() != 6) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 1 && !Character.isDigit(postalCode.charAt(i))) {
                return false;
            }

            else if (i % 2 == 0 && !Character.isLetter(postalCode.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public enum Province{
        QC, ON, AB, BC, MB, NB, NL, NS, PE, SK
    }
}
