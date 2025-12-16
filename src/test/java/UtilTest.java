import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pierrevertej.Util;

public class UtilTest {
    @DisplayName("toTitleCase: introduction to programming -> Introduction To Programming")
    @Test
    void toTitleCase1() {
        String str = "introduction to programming";
        String expected = "Introduction To Programming";
        String actual = Util.toTitleCase(str);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("toTitleCase: jADE rASMY -> Jade Rasmy")
    @Test
    void toTitleCase2() {
        String str = "jADE rASMY";
        String expected = "Jade Rasmy";
        String actual = Util.toTitleCase(str);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("toTitleCase: \"\" -> \"\"")
    @Test
    void toTitleCase3() {
        String str = "";
        String expected = "";
        String actual = Util.toTitleCase(str);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("toTitleCase: null -> null")
    @Test
    void toTitleCase4() {
        String str = null;
        String expected = null;
        String actual = Util.toTitleCase(str);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("toTitleCase: mathematics -> Mathematics")
    @Test
    void toTitleCase5() {
        String str = "mathematics";
        String expected = "Mathematics";
        String actual = Util.toTitleCase(str);
        Assertions.assertEquals(expected, actual);
    }
}
