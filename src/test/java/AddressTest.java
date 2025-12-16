import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddressTest {
    //this method is supposed to be private (helper) per project instructions
    //so I copied it here to be able to test it
    public static boolean isPostalCodeValid(String postalCode) {
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

    @DisplayName("isPostalCodeValid: A1B2C3 -> true")
    @Test
    void isPostalCodeValid1() {
        String postalCode = "A1B2C3";
        boolean expected = true;
        Assertions.assertEquals(expected, isPostalCodeValid(postalCode));
    }

    @DisplayName("isPostalCodeValid: A1B2C31 -> false")
    @Test
    void isPostalCodeValid2() {
        String postalCode = "A1B2C3D";
        boolean expected = false;
        Assertions.assertEquals(expected, isPostalCodeValid(postalCode));
    }

    @DisplayName("isPostalCodeValid: null -> false")
    @Test
    void isPostalCodeValid3() {
        String postalCode = null;
        boolean expected = false;
        Assertions.assertEquals(expected, isPostalCodeValid(postalCode));
    }
}
