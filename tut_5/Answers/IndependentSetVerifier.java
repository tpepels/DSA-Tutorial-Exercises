package tut_5.Answers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IndependentSetVerifier {

    public static boolean testIndependentSet(boolean[][] adjacencyMatrix, Set<Integer> S) {
        for (int u : S) {
            for (int v : S) {
                if (u != v && adjacencyMatrix[u][v]) {
                    return false; // Found an edge between two vertices in S
                }
            }
        }
        return true; // No edges found in S, valid independent set
    }

    public static void main(String[] args) {
        boolean[][] adjacencyMatrix = { /* ... graph data ... */ };
        Set<Integer> S = new HashSet<>(
                Arrays.asList( /* ... vertex indices ... */ ));

        boolean isValidIndependentSet = testIndependentSet(adjacencyMatrix, S);
        System.out.println("Is the set a valid independent set? " + isValidIndependentSet);
    }
}