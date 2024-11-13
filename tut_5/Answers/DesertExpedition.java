import java.util.*;
public class DesertExpedition {

    // Class to represent an edge (path between wells)
    static class Edge {
        String destination;
        int distance;
        int waterConsumption;
        Edge(String destination, int distance, int waterConsumption) {
            this.destination = destination;
            this.distance = distance;
            this.waterConsumption = waterConsumption;
        }
    }
    // Class to represent the state at each node
    static class State implements Comparable<State> {
        String node;
        int totalDistance;
        int totalWaterConsumption;
        State(String node, int totalDistance, int totalWaterConsumption) {
            this.node = node;
            this.totalDistance = totalDistance;
            this.totalWaterConsumption = totalWaterConsumption;
        }
        // Lexicographical ordering: first by totalDistance, then by totalWaterConsumption
        @Override
        public int compareTo(State other) {
            if (this.totalDistance != other.totalDistance) {
                return Integer.compare(this.totalDistance, other.totalDistance);
            } else {
                return Integer.compare(this.totalWaterConsumption, other.totalWaterConsumption);
            }
        }
    }
    // Graph representation
    private Map<String, List<Edge>> graph;
    private int capacity; // Initial water capacity (maximum capacity)
    private String startNode;
    private String endNode;
    public DesertExpedition() {
        graph = new HashMap<>();
    }
    // Method to add an edge to the graph
    public void addEdge(String source, String destination, int distance, int waterConsumption) {
        graph.computeIfAbsent(source, k -> new ArrayList<>())
                .add(new Edge(destination, distance, waterConsumption));
    }
    // Method to set the starting and ending nodes
    public void setStartAndEndNodes(String start, String end) {
        this.startNode = start;
        this.endNode = end;
    }
    // Method to set the water capacity
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    // Modified Dijkstra's algorithm
    public void findOptimalPath() {
        // Priority Queue ordered by totalDistance and totalWaterConsumption
        PriorityQueue<State> queue = new PriorityQueue<>();
        // Map to store the best totalDistance and totalWaterConsumption to reach a node
        Map<String, State> bestStates = new HashMap<>();
        // Map to reconstruct the path
        Map<String, String> predecessors = new HashMap<>();
        // Initialize with the starting node
        State startState = new State(startNode, 0, 0);
        queue.offer(startState);
        bestStates.put(startNode, startState);

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            String currentNode = currentState.node;
            // If we reached the destination
            if (currentNode.equals(endNode)) {
                reconstructPath(predecessors, currentState);
                return;
            }
            // Skip if we have already found a better path to this node
            if (currentState.totalDistance > bestStates.get(currentNode).totalDistance) {
                continue;
            }
            // Explore neighbors
            List<Edge> edges = graph.getOrDefault(currentNode, new ArrayList<>());
            for (Edge edge : edges) {
                // Check if the edge can be traversed with current capacity
                if (edge.waterConsumption > capacity) {
                    continue; // Cannot traverse this edge
                }
                String neighbor = edge.destination;
                // At each node, we refill to full capacity
                int waterRemaining = capacity - edge.waterConsumption;
                // Calculate new total distance and water consumption
                int newTotalDistance = currentState.totalDistance + edge.distance;
                int newTotalWaterConsumption = currentState.totalWaterConsumption + edge.waterConsumption;
                State neighborState = bestStates.get(neighbor);
                if (neighborState == null ||
                        newTotalDistance < neighborState.totalDistance ||
                        (newTotalDistance == neighborState.totalDistance && newTotalWaterConsumption < neighborState.totalWaterConsumption)) {
                    // Update the state
                    State newState = new State(neighbor, newTotalDistance, newTotalWaterConsumption);
                    queue.offer(newState);
                    bestStates.put(neighbor, newState);
                    predecessors.put(neighbor, currentNode);
                }
            }
        }
        // If we reach here, no path was found
        System.out.println("No valid path found from " + startNode + " to " + endNode + ".");
    }

    // Method to reconstruct and display the path
    private void reconstructPath(Map<String, String> predecessors, State endState) {
        List<String> path = new ArrayList<>();
        String currentNode = endState.node;
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = predecessors.get(currentNode);
        }
        Collections.reverse(path);
        System.out.println("Optimal Path: " + String.join(" -> ", path));
        System.out.println("Total Distance: " + endState.totalDistance + " km");
        System.out.println("Total Water Consumed: " + endState.totalWaterConsumption + " liters");
    }

    // Main method with example input
    public static void main(String[] args) {
        DesertExpedition expedition = new DesertExpedition();
        // Example Input:
        // Nodes: A, B, C, D, E
        // Edges:
        // A -> B (Distance: 5 km, Water Consumption: 3 liters)
        // A -> C (Distance: 10 km, Water Consumption: 7 liters)
        // B -> D (Distance: 8 km, Water Consumption: 5 liters)
        // C -> D (Distance: 3 km, Water Consumption: 2 liters)
        // D -> E (Distance: 4 km, Water Consumption: 3 liters)
        // Adding edges to the graph
        expedition.addEdge("A", "B", 5, 3);
        expedition.addEdge("A", "C", 10, 7);
        expedition.addEdge("B", "D", 8, 5);
        expedition.addEdge("C", "D", 3, 2);
        expedition.addEdge("D", "E", 4, 3);
        // Setting start and end nodes
        expedition.setStartAndEndNodes("A", "E");
        // Setting initial water capacity
        expedition.setCapacity(10);
        // Finding the optimal path
        expedition.findOptimalPath();
    }
}
