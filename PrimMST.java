import java.util.*;

// Class to represent an edge
class Edge {
    int src; // Source vertex
    int dest; // Destination vertex
    int weight; // Weight of the edge

    Edge(int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

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
    public void addEdge(int u, int v, int weight) {
        Edge edge1 = new Edge(v, weight);
        Edge edge2 = new Edge(u, weight);
        adj.get(u).add(edge1);
        adj.get(v).add(edge2);
    }

    // Function to find the minimum spanning tree using Prim's algorithm
    public List<Edge> primMST() {
        List<Edge> mst = new ArrayList<>();
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Start from the first vertex
        pq.offer(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            int u = minEdge.dest;

            // If the vertex is already visited, continue to the next iteration
            if (visited[u]) continue;

            // Mark the vertex as visited
            visited[u] = true;
            mst.add(minEdge);

            // Add all adjacent edges of the current vertex to the priority queue
            for (Edge edge : adj.get(u)) {
                if (!visited[edge.dest]) {
                    pq.offer(edge);
                }
            }
        }

        return mst;
    }
}

public class PrimMST {
    public static void main(String[] args) {
        int V = 5; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges to the graph
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        // Find the minimum spanning tree using Prim's algorithm
        List<Edge> mst = graph.primMST();

        // Print the edges of the minimum spanning tree
        System.out.println("Edges of the minimum spanning tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }
}
