public class BellmanFord {
    private Graph graph;
    public static void bellmanFord(Graph graph, int source) {
        int[] dist = new int[graph.getVertices()];
        for (int i = 0; i < graph.getVertices(); i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[source] = 0;

        for (int i = 1; i < graph.getVertices(); i++) {
            for (int u = 0; u < graph.getVertices(); u++) {
                for (int v = 0; v < graph.getVertices(); v++) {
                    if (graph.edge(u, v) != 0 && dist[u] != Integer.MAX_VALUE
                    && dist[u] + graph.edge(u,v) < dist[v]) {
                        dist[v] = dist[u] + graph.edge(u, v);
                    }
                }
            }
        }

        for (int i = 0; i < graph.getVertices(); i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + " is unreachable from source");
            } else {
                System.out.println("Shortest distance from source to vertex " + i + " is " + dist[i]);
            }
        }
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
        bellmanFord(g, 0);
    }
}
