package org.pierrevertej;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;
    static int nextId = 1;

    public void calcAssignmentAvg() {
        if (scores == null || scores.size() == 0) {
            double avg = 0;
        } else {
            double sum = 0;
            for (Integer score : scores) {
                sum += score;
            }

            double avg = sum / scores.size();
        }
    }

    public void generateRandomScore() {
        Random random = new Random();
        int num = random.nextInt(11);

        int score = switch(num) {
            case 0 -> random.nextInt(60);
            case 1, 2 -> random.nextInt(60, 70);
            case 3, 4 -> random.nextInt(70, 80);
            case 5, 6, 7, 8 -> random.nextInt(80, 90);
            case 9, 10 -> random.nextInt(90, 101);
            default -> -1;
        };
    }

    public String toString() {
        return "{assignmentId=" + assignmentId +
                ", assignmentName=" + assignmentName +
                ", weight=" + weight + "}";
    }
}
