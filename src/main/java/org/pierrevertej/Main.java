package org.pierrevertej;

public class Main {
    public static void main(String[] args) {
        Department compsci = new Department("Computer Science");

        Course course = new Course("Introduction to Programming", 2, compsci);
        System.out.println(course.getDepartment().getDepartmentId());
    }
}
