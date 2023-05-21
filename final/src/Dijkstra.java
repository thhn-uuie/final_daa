import java.util.*;

public class Dijkstra {
    private int V;
    private List<List<Node>> adj;

    public Dijkstra(int V){
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public Dijkstra(int V, List<List<Node>> adj){
        this.V = V;
        this.adj = adj;
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Node(v, w));
        adj.get(v).add(new Node(u, w));
    }
    void dijkstra(int src, int dest){
        PriorityQueue<Node> queue = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.distance));
        Map<Integer, Integer> prev = new HashMap<>();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean visited[] = new boolean[V];
        queue.add(new Node(src, 0));
        dist[src] = 0;

        while (!queue.isEmpty()) {
            int u = queue.remove().id;
            visited[u] = true;

            if (u == dest && dist[u] != Integer.MAX_VALUE){
                System.out.print("Đường đi ngắn nhất từ " + src + " đến " + dest + " là: ");

                List<Integer> path = new ArrayList<>();
                int node = dest;
                while (node != src) {
                    path.add(node);
                    node = prev.get(node);
                }
                path.add(src);
                Collections.reverse(path);
                System.out.println(path + ", với chi phí " + dist[dest]);
                break;
            }

            for (int i = 0; i < adj.get(u).size(); i++) {
                Node v = adj.get(u).get(i);
                if (!visited[v.id] && dist[v.id] > dist[u] + v.distance) {
                    dist[v.id] = dist[u] + v.distance;
                    queue.add(new Node(v.id, dist[v.id]));
                    prev.put(v.id, u);
                }
            }
        }

    }

    public static void main(String[] args) {
        int V = 5;
        Dijkstra g = new Dijkstra(V);
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 1);
        g.addEdge(1, 2, 7);
        g.addEdge(1, 3, 4);
        g.addEdge(1, 4, 1);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 4, 7);

        g.dijkstra(0, 3);

    }
    public static List<List<Node>> adjacencyList(int[][] matrix) {
        int n = matrix.length;
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (matrix[u][v] != 0) {
                    adj.get(u).add(new Node(v, matrix[u][v]));
                    adj.get(v).add(new Node(u, matrix[u][v]));
                }
            }
        }
        return adj;
    }
}
class Node {
    int id;
    int distance;
    public Node(int id, int distance){
        this.id = id;
        this.distance = distance;
    }
}
