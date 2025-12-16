package org.pierrevertej;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Integer> finalScores;
    static int nextId = 1;

    public boolean isAssignmentWeightValid() {
        if (assignments == null || assignments.size() == 0) {
            return false;
        }

        double sum = 0;
        for (Assignment assignment : assignments) {
            sum += assignment.getWeight();
        }

        return sum == 100;
    }

    public void registerStudent(Student student) {
        registeredStudents.add(student);
        finalScores.add(null);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }
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
    }
}
