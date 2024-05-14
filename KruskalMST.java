import java.util.*;

// Class to represent an edge in the graph
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // Constructor
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Compare edges based on their weights
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

// Class to represent a disjoint set (Union-Find) data structure
class DisjointSet {
    private int[] parent;
    private int[] rank;

    // Constructor
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the root of the set to which a node belongs
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Merge two sets by rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

public class KruskalMST {
    // Function to find the minimum spanning tree using Kruskal's algorithm
    public static List<Edge> kruskalMST(List<Edge> edges, int n) {
        // Sort the edges in non-decreasing order of their weights
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(n);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            int srcRoot = ds.find(edge.src);
            int destRoot = ds.find(edge.dest);

            // If including this edge does not form a cycle, add it to the MST
            if (srcRoot != destRoot) {
                mst.add(edge);
                ds.union(srcRoot, destRoot);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        // Create a sample graph
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        int n = 4; // Number of vertices

        // Find the minimum spanning tree using Kruskal's algorithm
        List<Edge> mst = kruskalMST(edges, n);

        // Print the edges of the minimum spanning tree
        System.out.println("Edges of the minimum spanning tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }
}

