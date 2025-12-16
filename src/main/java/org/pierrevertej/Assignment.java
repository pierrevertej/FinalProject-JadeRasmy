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
    private int maxScore;
    static int nextId = 1;

    public Assignment(String assignmentName, double weight, int maxScore) {
        this.assignmentId = String.format("%02d", nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = new ArrayList<>();
    }

    public int calcAssignmentAvg() {
        if (scores == null || scores.size() == 0) {
            return 0;
        }

        double sum = 0;
        for (Integer score : scores) {
            sum += score;
        }

        return (int) (sum / scores.size());
    }

    public void generateRandomScore(int size) {
        ArrayList<Integer> randomScores = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            int num = random.nextInt(11);

            int score = switch (num) {
                case 0 -> random.nextInt(60);
                case 1, 2 -> random.nextInt(60, 70);
                case 3, 4 -> random.nextInt(70, 80);
                case 5, 6, 7, 8 -> random.nextInt(80, 90);
                case 9, 10 -> random.nextInt(90, 101);
                default -> -1;
            };

            randomScores.add(Math.min(maxScore, score));
        }

        this.scores = randomScores;
    }

    public String toString() {
        return "{assignmentId=" + assignmentId +
                ", assignmentName=" + assignmentName +
                ", weight=" + weight + "}";
    }
}
