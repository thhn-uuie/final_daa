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
    public ArrayList<Integer> mapNode(int u, int v) {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(u);
        newList.add(v);
        return newList;
    }
    ArrayList<Integer> listInteger = new ArrayList<>();
    public ArrayList<Integer> getListInt(Node node, NodeList list) {

        for (int i = 0; i < list.getSize(); i++) {
            if (node == list.getNode(i)) {
                listInteger.add(i);
            }
        }
        return listInteger;
    }

    public int getIndex(Node node) {
        for (int i = 0; i < nodeArrayList.size(); i++) {
            if (node.equals(nodeArrayList.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
