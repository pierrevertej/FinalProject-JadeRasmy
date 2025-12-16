import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pierrevertej.Department;

public class DepartmentTest {
    @DisplayName("isDepartmentNameValid: Introduction to Programming -> true")
    @Test
    void isDepartmentNameValid1() {
        String departmentName = "Introduction to Programming";
        boolean expected = true;
        boolean actual = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("isDepartmentNameValid: Introduction to Programming! -> false")
    @Test
    void isDepartmentNameValid2() {
        String departmentName = "Introduction to Programming!";
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("isDepartmentNameValid: null -> false")
    @Test
    void isDepartmentNameValid3() {
        String departmentName = null;
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, actual);
    }
}
