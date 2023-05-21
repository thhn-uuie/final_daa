package gui;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BranchAndBound {
    private NodeList<Node> nodes;
    private EdgeList<Edge> edges;
    private Set<Node> visitedNodes;

    Node node1;
    Node node2;
    private Set<Node> unvisitedNodes;
    private LinkedList<Node> path;

    private LinkedList<Node> bestPath;
    private int bestCost;

    private int cost = 0;

    public BranchAndBound(NodeList<Node> listNode, EdgeList<Edge> listEdge, Node node1, Node node2) {
        this.nodes = listNode;
        this.edges = listEdge;
        this.visitedNodes = new HashSet<>();
        visitedNodes.add(node1);
        this.node1 = node1;
        this.node2 = node2;
        this.path = new LinkedList<>();
        path.add(node1);
        this.bestPath = new LinkedList<>();
        this.bestCost = Integer.MAX_VALUE;
    }

    //        public LinkedList<Node> findShortestPath(Node start, Node end) {
//            // Add start node to the path and visited nodes set
//            for (int i = 0; i < unvisitedNodes.size(); i ++) {
//
//            }
//        }
    public void findShortestPath() {
        if (path.getLast().equals(node2)) {
            if (cost < bestCost) {
                bestCost = cost;
                bestPath = (LinkedList<Node>) path.clone();
            }
            return;
        }
        NodeList<Node> nei = getNeighbours(path.getLast());
        for (int i = 0; i < nei.getSize(); i++) {
            int cost1 = cost + getDistance(path.getLast(), nei.getNode(i));
            if (cost1 < bestCost) {
                cost = cost1;
                path.add(nei.getNode(i));
                visitedNodes.add(nei.getNode(i));
                if (path.getLast().equals(node2)) {

                    bestCost = cost;
                    bestPath = (LinkedList<Node>) path.clone();
                    Node remove = path.removeLast();
                    visitedNodes.remove(remove);
                    cost -= getDistance(path.getLast(), remove);
                    continue;
                } else {
                    findShortestPath();
                }
                Node remove = path.removeLast();
                visitedNodes.remove(remove);
                cost -= getDistance(path.getLast(), remove);
            }
        }

        }

    // }




    private int getDistance(Node node, Node target) {
        int weight = 0;
        Edge edge = new Edge(node, target);
        int index = this.getIndexOf(edge);
        if (index != -1)
            weight = edges.getEdge(index).getWeight();
        else {
            throw new RuntimeException("no such edge");
        }
        return weight;
    }

    private NodeList<Node> getNeighbours(Node node) {
        NodeList<Node> temp = new NodeList<>();
        for (int i = 0; i < nodes.getSize(); i++) {
            Node h = nodes.getNode(i);
            if (!visitedNodes.contains(nodes.getNode(i))) {
                if (this.checkNeighbour(new Edge(node, nodes.getNode(i))) == true) {
                    temp.addNode(nodes.getNode(i));
                }
            }

        }

        return temp;
    }

    public boolean checkNeighbour(Edge e) {
        int x1, x2, y1, y2, a, b, c, d;//a=x1 b=x2 c=y1 d=y2 where abcd are point of edge from edgelist
        boolean check = false;
        x1 = e.getNode1().getX();
        x2 = e.getNode2().getX();
        y1 = e.getNode1().getY();
        y2 = e.getNode2().getY();
        for (int i = 0; i < edges.getSize(); i++) {
            a = edges.getEdge(i).getNode1().getX();
            b = edges.getEdge(i).getNode2().getX();
            c = edges.getEdge(i).getNode1().getY();
            d = edges.getEdge(i).getNode2().getY();
            if (a == x1 && b == x2 && c == y1 && d == y2) {
                check = true;
                break;
            }
            if (a == x2 && b == x1 && c == y2 && d == y1) {
                check = true;
                break;
            }
        }
        return check;
    }

    public int getIndexOf(Edge e) {
        int x1, x2, y1, y2, a, b, c, d, index = -1;//a=x1 b=x2 c=y1 d=y2 where abcd are point of edge from edgelist
        x1 = e.getNode1().getX();
        x2 = e.getNode2().getX();
        y1 = e.getNode1().getY();
        y2 = e.getNode2().getY();
        for (int i = 0; i < edges.getSize(); i++) {
            a = edges.getEdge(i).getNode1().getX();
            b = edges.getEdge(i).getNode2().getX();
            c = edges.getEdge(i).getNode1().getY();
            d = edges.getEdge(i).getNode2().getY();
            if (a == x1 && b == x2 && c == y1 && d == y2) {
                index = i;
                break;
            }
            if (a == x2 && b == x1 && c == y2 && d == y1) {
                index = i;
                break;
            }
        }
        return index;
    }

    //get the shortest path from one node to another
    public void getPath() {
        findShortestPath();
        for (int i = 0; i < bestPath.size(); i++) {
            System.out.println(bestPath.get(i).getX());
        }

        if (!node2.isEmpty()) {
            node2.setColor(Node.parseColor("#9C27B0"));
            for (int i = 0; i < bestPath.size() - 1; i++) {
                bestPath.get(i).setColor(Node.parseColor("#9C27B0"));

            }

            for (int i = 0; i < bestPath.size() - 1; i++) {
                int index = this.getIndexOf(new Edge(bestPath.get(i), bestPath.get(i + 1)));
                edges.getEdge(index).setColor(Color.RED);
            }

        }
    }

    public static void main(String[] args) {
        Node node0 = new Node(0,1);
        Node node1 = new Node(1,6);
        Node node2 = new Node(2,3);
//        Node node3 = new Node(3,2);
//        Node node4 = new Node(4,2);

        Edge edge1 = new Edge(node0, node1);
        edge1.setWeight(15);
        Edge edge2 = new Edge(node1, node2);
        edge2.setWeight(1);
        Edge edge3 = new Edge(node0, node2);
        edge3.setWeight(1);
//        Edge edge4 = new Edge(node3, node4);
//        edge4.setWeight(98);
//        Edge edge5 = new Edge(node0, node2);
//        edge5.setWeight(72);
//        Edge edge6 = new Edge(node1, node3);
//        edge6.setWeight(35);
//        Edge edge7 = new Edge(node0, node3);
//        edge7.setWeight(38);
//        Edge edge8 = new Edge(node1, node4);
//        edge8.setWeight(69);
//        Edge edge9 = new Edge(node0, node4);
//        edge9.setWeight(60);
        NodeList nodes = new NodeList();
        nodes.addNode(node0);
        nodes.addNode(node1);
        nodes.addNode(node2);
//        nodes.addNode(node3);
//        nodes.addNode(node4);

        EdgeList edges = new EdgeList();
        edges.addEdge(edge1);
        edges.addEdge(edge2);
        edges.addEdge(edge3);
//        edges.addEdge(edge4);
//        edges.addEdge(edge5);
//        edges.addEdge(edge6);
//        edges.addEdge(edge7);
//        edges.addEdge(edge8);
//        edges.addEdge(edge9);

        System.out.print("hehe");

        BranchAndBound bb = new BranchAndBound(nodes, edges, node0, node1);
        bb.getPath();

    }
}

