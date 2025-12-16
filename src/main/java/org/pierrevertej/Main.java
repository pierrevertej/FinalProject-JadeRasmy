package org.pierrevertej;

public class Main {
    public static void main(String[] args) {
        // testing in main for displayScores() and generateScores()
        Department compsci = new Department("Computer Science");
        Course course = new Course("Introduction to Programming", 2, compsci);
        Address address = new Address(1, "Main street", "Montreal", Address.Province.QC, "A1B2C3");
        Student student1 = new Student("Jade Rasmy", Student.Gender.MALE, address, compsci);
        Student student2 = new Student("Mike Tyson", Student.Gender.MALE, address, compsci);
        Student student3 = new Student("Muhammad Ali", Student.Gender.MALE, address, compsci);

        course.addAssignment("Assignment01", 20, 100);
        course.addAssignment("Assignment02", 20, 100);
        course.addAssignment("Assignment03", 20, 100);
        course.addAssignment("Assignment04", 20, 100);
        course.addAssignment("Assignment05", 20, 100);

        course.registerStudent(student1);
        course.registerStudent(student2);
        course.registerStudent(student3);

        course.generateScores();

        course.displayScores();
    }
}
