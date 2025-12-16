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
    static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseId = generateId();
        this.courseName = toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        assignments = new ArrayList<Assignment>();
        registeredStudents = new ArrayList<Student>();
        finalScores = new ArrayList<Integer>();
    }

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

    public boolean registerStudent(Student student) {
        for (Student registeredStudent : registeredStudents) {
            if (registeredStudent.toSimplifiedString().equals(student.toSimplifiedString())) {
                return false;
            }
        }

        registeredStudents.add(student);
        finalScores.add(null);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }

        return true;
    }

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

    private String generateId() {
        return String.format("C-%s-%02d", this.department.getDepartmentId(), nextId++);
    }

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
