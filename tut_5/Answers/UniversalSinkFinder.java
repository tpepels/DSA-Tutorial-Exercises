package tut_5.Answers;

public class UniversalSinkFinder {

    public static int findUniversalSink(boolean[][] adjMatrix) {
        int n = adjMatrix.length;
        int candidate = 0;

        // Find candidate vertex
        for (int i = 1; i < n; i++) {
            if (adjMatrix[candidate][i] || !adjMatrix[i][candidate]) {
                candidate = i;
            }
        }

        // Verify the candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate && (adjMatrix[candidate][i] || !adjMatrix[i][candidate])) {
                return -1; // Not a universal sink
            }
        }

        return candidate; // Universal sink found
    }

    public static void main(String[] args) {
        boolean[][] adjMatrix = {
                { false, false, false, true, false }, // Vertex 0
                { false, false, false, true, false }, // Vertex 1
                { false, false, false, true, false }, // Vertex 2
                { false, false, false, false, false }, // Vertex 3
                { true, true, true, true, false } // Vertex 4
        };
        int sink = findUniversalSink(adjMatrix);
        if (sink != -1) {
            System.out.println("Universal sink found at vertex: " + sink);
        } else {
            System.out.println("No universal sink found.");
        }
    }
}
