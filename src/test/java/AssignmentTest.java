import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pierrevertej.Assignment;

import java.util.ArrayList;

public class AssignmentTest {
    @DisplayName("calcAssignmentAvg: {60,80} -> 70")
    @Test
    void calcAssignmentAvg1() {
        Assignment assignment = new Assignment("Assignment01", 50, 100);
        ArrayList<Integer> scores = new  ArrayList<>();
        scores.add(60);
        scores.add(80);
        assignment.setScores(scores);
        int expected = 70;
        int actual = assignment.calcAssignmentAvg();
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("calcAssignmentAvg: {} -> 0")
    @Test
    void calcAssignmentAvg2() {
        Assignment assignment = new Assignment("Assignment01", 50, 100);
        ArrayList<Integer> scores = new  ArrayList<>();
        assignment.setScores(scores);
        int expected = 0;
        int actual = assignment.calcAssignmentAvg();
        Assertions.assertEquals(expected, actual);
    }@DisplayName("calcAssignmentAvg: {100,100} -> 100")
    @Test
    void calcAssignmentAvg3() {
        Assignment assignment = new Assignment("Assignment01", 50, 100);
        ArrayList<Integer> scores = new  ArrayList<>();
        scores.add(100);
        scores.add(100);
        assignment.setScores(scores);
        int expected = 100;
        int actual = assignment.calcAssignmentAvg();
        Assertions.assertEquals(expected, actual);
    }
}
