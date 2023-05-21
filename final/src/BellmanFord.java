import java.util.*;

public class BellmanFord {
    public List<Integer> bellmanFord(Graph graph, int source, int end) {
        int[] dist = new int[graph.getVertices()];
        Map<Integer, Integer> prev = new HashMap<>();
        for (int i = 0; i < graph.getVertices(); i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        for (int i = 1; i < graph.getVertices(); i++) {
            for (int u = 0; u < graph.getVertices(); u++) {
                for (int v = 0; v < graph.getVertices(); v++) {
                    if (graph.edge(u, v) != 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u] + graph.edge(u, v) < dist[v]) {
                        dist[v] = dist[u] + graph.edge(u, v);
                        prev.put(v, u);
                    }
                }
            }
        }

        for (int u = 0; u < graph.getVertices(); u++) {
            for (int v = 0; v < graph.getVertices(); v++) {
                if (graph.edge(u, v) != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph.edge(u,v) < dist[v]) {
                    System.out.println("Do thi co chu trinh am");
                }
            }
        }

        System.out.print("Đường đi ngắn nhất từ " + source + " đến " + end + " là: ");
        if (dist[end] == Integer.MAX_VALUE) {
            System.out.println("Không tồn tại đường đi.");
        } else {
            List<Integer> path = new ArrayList<>();
            int node = end;
            while (node != source) {
                path.add(node);
                node = prev.get(node);
            }
            path.add(source);
            Collections.reverse(path);
            System.out.println(path + ", với chi phí " + dist[end]);
            return path;
        }
        return null;
    }


    public static void main(String[] args) {
        Graph g = new Graph(5);
        int[][] graph = {
                {0, -1, 4, 0, 0},
                {0, 0, 3, 2, 2},
                {0, 0, 0, 0, 0},
                {0, 1, 5, 0, 0},
                {0, 0, 0, -3, 0}
        };
        g.setGraph_matrix(graph);
        BellmanFord gr = new BellmanFord();
        List<Integer> s = gr.bellmanFord(g, 0, 2);
        System.out.println(s);
    }
}