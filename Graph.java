public class Graph {
    private int vertices;
    private int[][] graph_matrix;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.graph_matrix = new int[vertices][vertices];
    }
    public void addEdge(int u, int v, int x) {
        if (getEdges(u, v) == false) {
            graph_matrix[u][v] = x;
        } else {
            System.out.println("Edges between " + u + " and " + v + " are existed");
        }
    }

    public boolean getEdges(int u, int v) {
        if (graph_matrix[u][v] != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int edge(int u, int v) {
        return graph_matrix[u][v];
    }
    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public int[][] getGraph_matrix() {
        return graph_matrix;
    }

    public void setGraph_matrix(int[][] graph_matrix) {
        this.graph_matrix = graph_matrix;
    }
}
