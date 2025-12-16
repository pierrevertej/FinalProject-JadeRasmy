package org.pierrevertej;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;
    static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.studentId = String.format("S%06d", nextId++);
        this.registeredCourses = new ArrayList<>();
    }

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

    public enum Gender {
        MALE, FEMALE
    }
}
