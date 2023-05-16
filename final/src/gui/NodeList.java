package gui;

import java.util.ArrayList;
import java.util.Iterator;

public class NodeList<Node> {
    private ArrayList<Node> nodeArrayList;
    public NodeList() {
        nodeArrayList = new ArrayList<>();
    }
    public void addNode(Node node) {
        nodeArrayList.add(node);
    }
    public Node getNode(int i) {
        return nodeArrayList.get(i);
    }
    public int getSize() {
        return nodeArrayList.size();
    }

    public ArrayList<Node> getList() {
        return nodeArrayList;
    }

    public Iterator<Node> iterator() {
        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < nodeArrayList.size(); i++) {
            list.add(nodeArrayList.get(i));
        }
        return list.iterator();
    }
}
