import java.util.*;

public class Dijkstra {
    private int V;
    private List<List<Edge>> adj;

    public Dijkstra(int V){
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public Dijkstra(int V, List<List<Edge>> adj){
        this.V = V;
        this.adj = adj;
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(v, w));
        adj.get(v).add(new Edge(u, w));
    }
    void dijkstra(int src){
        PriorityQueue<Edge> queue = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.dest));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.add(new Edge(src, 0));
        dist[src] = 0;

        while (!queue.isEmpty()) {
            int u = queue.remove().dest;

            for (int i = 0; i < adj.get(u).size(); i++) {
                Edge v = adj.get(u).get(i);
                if (dist[v.dest] > dist[u] + v.weight) {
                    dist[v.dest] = dist[u] + v.weight;
                    queue.add(new Edge(v.dest, dist[v.dest]));
                }
            }
        }
        printShortPath(V, dist);
    }

    public static void main(String[] args) {
        int V = 9;
        int[][] matrix = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {0, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 0, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 0, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 0, 0, 10, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        List<List<Edge>> adj = adjacencyList(matrix);
        Dijkstra g = new Dijkstra(V, adj);

//        Dijkstra g = new Dijkstra(V);
//        g.addEdge(0, 1, 4);
//        g.addEdge(0, 7, 8);
//        g.addEdge(1, 2, 8);
//        g.addEdge(1, 7, 11);
//        g.addEdge(2, 3, 7);
//        g.addEdge(2, 8, 2);
//        g.addEdge(2, 5, 4);
//        g.addEdge(3, 4, 9);
//        g.addEdge(3, 5, 14);
//        g.addEdge(4, 5, 10);
//        g.addEdge(5, 6, 2);
//        g.addEdge(6, 7, 1);
//        g.addEdge(6, 8, 6);
//        g.addEdge(7, 8, 7);
//        System.out.println(g.adj.get(0).get(1));
        g.dijkstra(0);
    }
    public static List<List<Edge>> adjacencyList(int[][] matrix) {
        int n = matrix.length;
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (matrix[u][v] != 0) {
                    adj.get(u).add(new Edge(v, matrix[u][v]));
                    adj.get(v).add(new Edge(u, matrix[u][v]));
                }
            }
        }
        return adj;
    }

    void printShortPath(int V, int[] dist){
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public void printAdj(){
        for (int u = 0; u < adj.size(); u++) {
            System.out.print(u + ": ");
            for (Edge e : adj.get(u)) {
                System.out.print("(" + e.dest + ", " + e.weight + ") ");
            }
            System.out.println();
        }
    }
}
class Edge {
    int dest;
    int weight;
    public Edge(){}
    public Edge(int dest, int weight){
        this.dest = dest;
        this.weight = weight;
    }
}
