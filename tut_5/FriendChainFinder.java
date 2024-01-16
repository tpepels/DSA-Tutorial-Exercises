package tut_5;

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
        // TODO Implement this method
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
