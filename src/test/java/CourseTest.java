import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pierrevertej.Course;
import org.pierrevertej.Department;

public class CourseTest {
    @DisplayName("isAssignmentValid : 30-30-40 -> true")
    @Test
    void isAssignmentValid1() {
        Department department = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, department);
        course.addAssignment("Assignment01", 30, 100);
        course.addAssignment("Assignment02", 30, 100);
        course.addAssignment("Assignment03", 40, 100);
        boolean expected = true;
        boolean actual = course.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("isAssignmentValid : 30-30-30 -> false")
    @Test
    void isAssignmentValid2() {
        Department department = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, department);
        course.addAssignment("Assignment01", 30, 100);
        course.addAssignment("Assignment02", 30, 100);
        course.addAssignment("Assignment03", 30, 100);
        boolean expected = false;
        boolean actual = course.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("isAssignmentValid : no assignment -> false")
    @Test
    void isAssignmentValid3() {
        Department department = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, department);
        boolean expected = false;
        boolean actual = course.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }


    @DisplayName("addAssignment : Assignment01 but it already contains it -> false")
    @Test
    void addAssignment1() {
        Department department = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, department);
        course.addAssignment("Assignment01", 30, 100);
        boolean expected = false;
        boolean actual = course.addAssignment("Assignment01", 20, 95);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("addAssignment : Assignment02 and it doesn't already contain it -> true")
    @Test
    void addAssignment2() {
        Department department = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, department);
        course.addAssignment("Assignment01", 30, 100);
        boolean expected = true;
        boolean actual = course.addAssignment("Assignment02", 20, 95);
        Assertions.assertEquals(expected, actual);
    }
}
