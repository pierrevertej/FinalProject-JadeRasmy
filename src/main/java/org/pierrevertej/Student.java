package org.pierrevertej;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static org.pierrevertej.Util.toTitleCase;

@EqualsAndHashCode
@Getter
public class Student {
    private String studentId;
    private String studentName;
    @Setter private Gender gender;
    @Setter private Address address;
    @Setter private Department department;
    @Setter private ArrayList<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.studentId = String.format("S%06d", nextId++);
        this.registeredCourses = new ArrayList<>();
    }

    /**
     * Registers a course, this method (1) adds the course to the student's `registeredCourses` list,
     * (2) adds the `student` to the course's `registeredStudents` list,
     * (3) appends a `null` for the `scores` of each assignment of the course.
     * If the course is already registered (exists in the student's `registeredCourses` list), directly returns `false` without adding anything.
     * @param course course to register to
     * @return false if the student is already registered to that course, true if he isn't
     */
    public boolean registerCourse(Course course) {
        for (Course registeredCourse : this.registeredCourses) {
            if (registeredCourse.toSimplifiedString().equals(course.toSimplifiedString())) {
                return false;
            }
        }

        this.registeredCourses.add(course);
        course.registerStudent(this);
        return true;
    }

    /**
     * drops a course, remove the course from the student's `registeredCourses` list, and remove the student from the course's `registeredStudents` list.
     * If the course is not registered yet (not in the student's `registeredCourses` list), directly returns `false` without removing anything.
     * @param course the course to drop
     * @return false if the student isn't registered to that course, true if he is
     */
    public boolean dropCourse(Course course) {
        boolean courseInRegisteredCourses = false;
        for (Course registeredCourse : this.registeredCourses) {
            if (registeredCourse.toSimplifiedString().equals(course.toSimplifiedString())) {
                courseInRegisteredCourses = true;
                break;
            }
        }

        if (!courseInRegisteredCourses) {
            return false;
        }

        this.registeredCourses.remove(course);
        ArrayList<Student> newRegisteredStudents = new ArrayList<>();
        for (Student student : course.getRegisteredStudents()) {
            if (!student.toSimplifiedString().equals(this.toSimplifiedString())) {
                newRegisteredStudents.add(student);
            }
        }

        course.setRegisteredStudents(newRegisteredStudents);
        return true;
    }

    /**
     * toString with only studentId, studentName and departmentName
     * @return string with fields
     */
    public String toSimplifiedString() {
        return "{studentId=" + studentId +
                ", studentName=" + studentName +
                ", departmentName=" + department.getDepartmentName() + "}";
    }

    @Override
    public String toString() {
        String coursesList = "{";
        for (Course course : registeredCourses) {
            coursesList += course.toSimplifiedString() + ",";
        }

        coursesList = coursesList.substring(0, coursesList.length() - 1) + "}";

        return "{studentId=" + studentId +
                ", studentName=" + studentName +
                ", gender=" + gender +
                ", address=" + address.toString() +
                ", department=" + department.toString() +
                ", registeredCourses=" + coursesList + "}";
    }

    public void setStudentName(String studentName) {
        this.studentName = toTitleCase(studentName);
    }

    public enum Gender {
        MALE, FEMALE
    }
}
