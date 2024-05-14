import java.util.*;

// Class to represent each node in the graph
class Node implements Comparable<Node> {
    int id; // Node id
    int distance; // Distance from the start node
    int heuristic; // Heuristic value (estimated distance to the goal node)
    Node parent; // Parent node in the path

    // Constructor
    Node(int id, int distance, int heuristic, Node parent) {
        this.id = id;
        this.distance = distance;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    // Compare nodes based on total cost (distance + heuristic)
    public int compareTo(Node other) {
        return (this.distance + this.heuristic) - (other.distance + other.heuristic);
    }
}

public class AStarShortestPath {
    static int[][] graph; // Adjacency matrix representation of the graph

    // Function to find the shortest path using A* algorithm
    static List<Integer> findShortestPath(int start, int goal) {
        int n = graph.length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        // Add start node to priority queue
        pq.add(new Node(start, 0, heuristic(start, goal), null));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentId = current.id;

            // Check if current node is the goal node
            if (currentId == goal) {
                // Reconstruct the path from the goal node to the start node
                List<Integer> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.id);
                    current = current.parent;
                }
                return path;
            }

            // Mark current node as visited
            visited.add(currentId);

            // Explore neighbors of the current node
            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (graph[currentId][neighbor] != 0 && !visited.contains(neighbor)) {
                    // Calculate total distance from start to neighbor
                    int distance = current.distance + graph[currentId][neighbor];
                    // Add neighbor to priority queue with updated distance and heuristic
                    pq.add(new Node(neighbor, distance, heuristic(neighbor, goal), current));
                }
            }
        }

        // If goal node is not reachable from start node
        return null;
    }

    // Heuristic function (Manhattan distance)
    static int heuristic(int current, int goal) {
        // In this example, we use a simple heuristic of returning the absolute difference between current and goal
        return Math.abs(current - goal);
    }

    public static void main(String[] args) {
        // Example graph (adjacency matrix)
        graph = new int[][]{
                {0, 2, 0, 0, 0, 0, 0},
                {2, 0, 1, 0, 3, 0, 0},
                {0, 1, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 2, 0},
                {0, 3, 0, 0, 0, 0, 1},
                {0, 0, 0, 2, 0, 0, 3},
                {0, 0, 0, 0, 1, 3, 0}
        };

        int startNode = 0; // Start node
        int goalNode = 6; // Goal node

        List<Integer> shortestPath = findShortestPath(startNode, goalNode);

        if (shortestPath != null) {
            System.out.println("Shortest path from " + startNode + " to " + goalNode + ": " + shortestPath);
        } else {
            System.out.println("No path found from " + startNode + " to " + goalNode);
        }
    }
}
