package tut_5.Answers;

import java.util.*;

public class IndependentSets {
    // To store all the independent sets of the graph
    static Set<Set<Integer>> independentSets = new HashSet<>();

    // To store all maximal independent sets in the graph
    static Set<Set<Integer>> maximalIndependentSets = new HashSet<>();

    static Map<int[], Boolean> edges = new HashMap<>();
    static List<Integer> vertices = new ArrayList<>();

    // Function to find all maximal independent sets
    static void findMaximalIndependentSets() {
        int maxCount = 0;
        for (Set<Integer> set : independentSets) {
            if (set.size() > maxCount) {
                maxCount = set.size();
            }
        }
        for (Set<Integer> set : independentSets) {
            if (set.size() == maxCount) {
                maximalIndependentSets.add(Collections.unmodifiableSet(new HashSet<>(set)));
            }
        }
    }

    // Function to check if a node is safe for independent set
    static boolean isSafeForIndependentSet(int vertex, Set<Integer> tempSolutionSet) {
        for (int node : tempSolutionSet) {
            if (edges.containsKey(new int[] { node, vertex })) {
                return false;
            }
        }
        return true;
    }

    // Recursive function to find all independent sets
    static void findAllIndependentSets(int currV, int setSize, Set<Integer> tempSolutionSet) {
        for (int i = currV; i <= setSize; i++) {
            if (isSafeForIndependentSet(vertices.get(i - 1), tempSolutionSet)) {
                tempSolutionSet.add(vertices.get(i - 1));
                findAllIndependentSets(i + 1, setSize, tempSolutionSet);
                tempSolutionSet.remove(vertices.get(i - 1));
            }
        }
        independentSets.add(Collections.unmodifiableSet(new HashSet<>(tempSolutionSet)));
    }

    // Driver Program

    public static void main(String[] args) {
        int V = 3, E = 2;
        for (int i = 1; i <= V; i++) {
            vertices.add(i);
        }

        List<int[]> inputEdges = new ArrayList<>();
        inputEdges.add(new int[] { 1, 2 });
        inputEdges.add(new int[] { 2, 3 });

        for (int i = 0; i < E; i++) {
            if (i < inputEdges.size()) {
                edges.put(inputEdges.get(i), true);
                edges.put(new int[] { inputEdges.get(i)[1], inputEdges.get(i)[0] }, true);
            }
        }

        Set<Integer> tempSolutionSet = new HashSet<>();
        findAllIndependentSets(1, V, tempSolutionSet);
        findMaximalIndependentSets();

        // Print all independent sets
        System.out.println("All Independent Sets:");
        for (Set<Integer> set : independentSets) {
            System.out.println(set);
        }

        // Print all maximal independent sets
        System.out.println("\nMaximal Independent Sets:");
        for (Set<Integer> set : maximalIndependentSets) {
            System.out.println(set);
        }
    }

}
