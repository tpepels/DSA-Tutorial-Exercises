package tut_5.Answers;

import java.util.*;

public class FriendChainFinder {

    private Map<Integer, List<Integer>> graph; // Adjacency list representation

    public FriendChainFinder() {
        graph = new HashMap<>();
    }

    // Method to add friendship relation
    public void addFriendship(int a, int b) {
        graph.putIfAbsent(a, new ArrayList<>());
        graph.putIfAbsent(b, new ArrayList<>());
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    // BFS algorithm to find chain of friends
    public List<Integer> findFriendChain(int x, int y) {
        Set<Integer> visited = new HashSet<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(x));
        visited.add(x);

        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int lastPerson = path.get(path.size() - 1);

            if (lastPerson == y) {
                return path; // Found the path to y
            }

            for (int friend : graph.getOrDefault(lastPerson, Collections.emptyList())) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(friend);
                    queue.add(newPath);
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    public static void main(String[] args) {
        // Example usage
        FriendChainFinder finder = new FriendChainFinder();
        finder.addFriendship(1, 2);
        finder.addFriendship(2, 3);
        finder.addFriendship(3, 4);

        System.out.println("Friend Chain: " + finder.findFriendChain(1, 4));
    }
}
