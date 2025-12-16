package org.pierrevertej;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static org.pierrevertej.Util.toTitleCase;

@Getter
@Setter
@EqualsAndHashCode
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Integer> finalScores;
    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseName = toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.courseId = generateId(); // I need the id to initialize after the department to use departmentId
        assignments = new ArrayList<Assignment>();
        registeredStudents = new ArrayList<Student>();
        finalScores = new ArrayList<Integer>();
    }

    /**
     * checks if the sum of weights of all assignments of that course equals to 100%.
     * @return true if the weights are valid, false if they aren't
     */
    public boolean isAssignmentWeightValid() {
        if (assignments == null || assignments.isEmpty()) {
            return false;
        }

        double sum = 0;
        for (Assignment assignment : assignments) {
            sum += assignment.getWeight();
        }

        return sum == 100;
    }

    /**
     * adds a student to the student list of the course,
     * also add a new `null` element to each assignment of this course,
     * and add a new `null` element for the `finalScores`.
     * @param student student to be added to the course
     * @return true if the student is already in the course, false if they are
     */
    public boolean registerStudent(Student student) {
        for (Student registeredStudent : registeredStudents) {
            if (registeredStudent.toSimplifiedString().equals(student.toSimplifiedString())) {
                return false;
            }
        }

        ArrayList<Student> students = new ArrayList<>();
        students.addAll(registeredStudents);
        students.add(student);
        this.setRegisteredStudents(students);

        ArrayList<Course> courses = new ArrayList<>();
        courses.addAll(student.getRegisteredCourses());
        courses.add(this);
        student.setRegisteredCourses(courses);

        finalScores.add(null);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }

        return true;
    }

    /**
     * calculates the weighted average score of every students in a course
     * @return an array of integers corresponding to the averages
     */
    public int[] calcStudentsAverage() {
        int[] avgs = new int[registeredStudents.size()];

        for (int i = 0; i < avgs.length; i++) {
            double sum = 0;

            for (Assignment assignment : assignments) {
                if (assignment.getScores().get(i) != null) {
                    sum += assignment.getScores().get(i) * assignment.getWeight();
                }
            }

            avgs[i] = (int) (sum / 100);
        }

        return avgs;
    }

    /**
     * adds a new assignment to the course.
     * @param assignmentName the name of the assignment to be added
     * @param weight the weight of the assignment to be added
     * @param maxScore the max score of the assignment
     * @return false if the assignment already exists for that course, true if it doesn't
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        for (Assignment assignment : assignments) {
            if (assignment.getAssignmentName().equals(toTitleCase(assignmentName))) {
                return false;
            }
        }

        Assignment assignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(assignment);
        return true;
    }

    /**
     * generates random scores for each assignment and student,
     * and calculates the final score for each student.
     */
    public void generateScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore(registeredStudents.size());
        }

        this.finalScores = new ArrayList<Integer>();
        for (int avg : calcStudentsAverage()) {
            finalScores.add(avg);
        }
    }

    /**
     * helper method to automatically generate course IDs
     * ID must be in the form:
     * `C-departmentId-twoDigitCourseId`, e.g.: `C-D01-01`, `C-D01-05`
     * @return the courseId
     */
    private String generateId() {
        return String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
    }

    /**
     * helper method to calculate the overall class average for a course
     * @return the class average
     */
    private int calculateClassAverage() {
        if (finalScores == null || finalScores.size() == 0) {
            return 0;
        }

        int sum = 0;
        for (Integer finalScore : finalScores) {
            sum += finalScore;
        }

        return (int) (sum /  finalScores.size());
    }

    /**
     * displays the scores of a course in a table,
     * with the assignment averages and student weighted average
     */
    public void displayScores() {
        System.out.printf("Course: %s(%s)\n", this.courseName, this.courseId);
        String assignmentsLine = String.format("%20s", "");
        for (Assignment assignment : assignments) {
            assignmentsLine += String.format("%15s", assignment.getAssignmentName());
        }

        assignmentsLine += String.format("%15s", "Final Score");

        System.out.printf("%s\n", assignmentsLine);

        for (int i = 0; i < registeredStudents.size(); i++) {
            String studentLine = String.format("%20s", registeredStudents.get(i).getStudentName());
            for (Assignment assignment : assignments) {
                studentLine += String.format("%15d", assignment.getScores().get(i));
            }

            studentLine += String.format("%15d", finalScores.get(i));
            System.out.printf("%s\n", studentLine);
        }

        String averageLine = String.format("%20s", "Average");
        for (Assignment assignment : assignments) {
            averageLine += String.format("%15d", assignment.calcAssignmentAvg());
        }

        averageLine += String.format("%15d", calculateClassAverage());
        System.out.printf("%s\n", averageLine);
    }

    /**
     * toString method that only contains the courseId, courseName, credits and departmentName
     * @return the string containing these fields
     */
    public String toSimplifiedString() {
        return "{courseId=" + this.courseId + ", courseName=" + this.courseName +
                ", credits=" + this.credits + ", departmentName=" + this.department.getDepartmentName() + "}";
    }

    @Override
    public String toString() {
        String studentsList = "{";
        for (Student student : registeredStudents) {
            studentsList += student.toSimplifiedString() + ",";
        }

        studentsList = studentsList.substring(0, studentsList.length() - 1) + "}";

        return "{courseId=" + this.courseId + ", courseName=" + this.courseName +
                ", credits=" + this.credits + ", departmentName=" + this.department.getDepartmentName() +
                ", assignments=" + assignments.toString() + ", registeredStudents=" + studentsList + "}";

    }

    public void setCourseName(String courseName) {
        this.courseName = toTitleCase(courseName);
    }
}
