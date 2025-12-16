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

    private static boolean isPostalCodeValid(String postalCode) {
        if (postalCode.length() != 6) {
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
