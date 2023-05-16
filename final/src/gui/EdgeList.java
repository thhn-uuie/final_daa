package gui;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EdgeList<Edge>{
    private ArrayList<Edge> edgeArrayList;
    public EdgeList() {
        edgeArrayList = new ArrayList<>();
    }
    public void addEdge(Edge e) {
        edgeArrayList.add(e);
    }
    public Edge getEdge(int x) {
        return edgeArrayList.get(x);
    }
    public ArrayList<Edge> getList() {
        return edgeArrayList;
    }

    public int getIndex(Edge edge) {
        return edgeArrayList.indexOf(edge);
    }
    public int getSize() {
        return edgeArrayList.size();
    }
}
