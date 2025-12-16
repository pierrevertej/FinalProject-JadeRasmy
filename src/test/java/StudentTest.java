import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pierrevertej.Address;
import org.pierrevertej.Course;
import org.pierrevertej.Department;
import org.pierrevertej.Student;

public class StudentTest {
    @DisplayName("registerCourse: register student1 to course he isn't already in -> true")
    @Test
    void registerCourse1() {
        Department department = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, department);
        Address address = new Address(1, "Main street", "Montreal", Address.Province.QC, "A1B2C3");
        Student student1 = new Student("Jade Rasmy", Student.Gender.MALE, address, department);

        boolean expected = true;
        boolean actual = student1.registerCourse(course);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("registerCourse: register student1 to course he already is in -> false")
    @Test
    void registerCourse2() {
        Department department = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, department);
        Address address = new Address(1, "Main street", "Montreal", Address.Province.QC, "A1B2C3");
        Student student1 = new Student("Jade Rasmy", Student.Gender.MALE, address, department);
        course.registerStudent(student1);

        boolean expected = false;
        boolean actual = student1.registerCourse(course);
        Assertions.assertEquals(expected, actual);
    }
}
