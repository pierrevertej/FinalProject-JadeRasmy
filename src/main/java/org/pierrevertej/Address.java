package org.pierrevertej;

public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    private static boolean isPostalCodeValid(String postalCode) {
        if (postalCode.length() != 6) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0 && !Character.isDigit(postalCode.charAt(i))) {
                return false;
            }

            else if (i % 2 == 1 && !Character.isLetter(postalCode.charAt(i))) {
                return false;
            }
        }

        return true;

    }

    enum Province{
        QC, ON, AB, BC, MB, NB, NL, NS, PE, SK
    }
}
