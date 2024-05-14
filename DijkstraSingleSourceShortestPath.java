import java.util.*;

// Class to represent a graph
class Graph {
    private int V; // Number of vertices
    private List<List<Edge>> adj; // Adjacency list representation

    // Constructor
    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Function to add an edge to the graph
    public void addEdge(int src, int dest, int weight) {
        adj.get(src).add(new Edge(dest, weight));
    }

    // Function to find the shortest paths from a source vertex to all other vertices using Dijkstra's algorithm
    public int[] dijkstra(int src) {
        int[] dist = new int[V]; // Array to store shortest distances from the source vertex
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            for (Edge edge : adj.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }

    // Class to represent an edge in the graph
    private static class Edge {
        int dest; // Destination vertex
        int weight; // Weight of the edge

        // Constructor
        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Class to represent a node in the priority queue
    private static class Node {
        int vertex; // Vertex index
        int distance; // Distance from the source vertex

        // Constructor
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}

public class DijkstraSingleSourceShortestPath {
    public static void main(String[] args) {
        int V = 5; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges to the graph
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 3, 9);

        int source = 0; // Source vertex

        // Find the shortest paths from the source vertex using Dijkstra's algorithm
        int[] dist = graph.dijkstra(source);

        // Print the shortest distances from the source vertex to all other vertices
        System.out.println("Shortest distances from vertex " + source + " to all other vertices:");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + dist[i]);
        }
    }
}
