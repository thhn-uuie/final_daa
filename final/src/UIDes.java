import gui.Edge;
import gui.EdgeList;
import gui.Node;
import gui.NodeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class UIDes extends JFrame {
    private JButton btDFS;
    private JButton btBellmanFord;
    private JPanel panelMain;
    private JRadioButton button_AddNode;

    private JRadioButton button_AddEdge;
    private JButton button_randomGraph;
    private JScrollPane panel_show;
    private JPanel panel;
    private JButton btDijkstra;
    Node node1;
    Node node2;

    Edge edge = new Edge();
    EdgeList<Edge> listEdge = new EdgeList<>();
    NodeList<Node> listNode = new NodeList<>();

    ClickListenerNode clickNode = new ClickListenerNode();
    ClickListenerEdge clickEdge = new ClickListenerEdge();
    ClickListener1 cl1 =new ClickListener1();
//    ClickListener4 cl4 =new ClickListener4();
    public UIDes() {
        setFrame();
    }

    public void setFrame() {
        this.setContentPane(this.panelMain);
        this.setTitle("Find Shortest Path in Graph");

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        this.setBackground(Color.WHITE);

        this.setButtons();

        this.setVisible(true);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        // vẽ đỉnh
        if (listNode.getSize() > 0) {
            for (int i = 0; i < listNode.getSize(); i++) {
                listNode.getNode(i).draw(graphics);
                listNode.getNode(i).insertString(graphics, i);
            }
        }

        // vẽ cạnh
        if (listEdge.getSize() > 0) {
            for (int i = 0; i < listEdge.getSize(); i++) {
                listEdge.getEdge(i).draw(graphics);
            }
        }
    }

    public void setButtons() {

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(button_AddNode);
        buttonGroup.add(button_AddEdge);
        buttonGroup.add(btDijkstra);
        buttonGroup.add(button_randomGraph);

        // button để thêm đỉnh
        button_AddNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeMouseListener(clickEdge);
                panel.removeMouseListener(cl1);
                panel.addMouseListener(clickNode);
            }
        });

        // button để thêm cạnh
        button_AddEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeMouseListener(clickNode);
                panel.addMouseListener(clickEdge);
                panel.removeMouseListener(cl1);
            }
        });
        btDijkstra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edgeDefaultColor();

                node1=new Node();
                node2=new Node();

                panel.removeMouseListener(clickNode);
                panel.removeMouseListener(clickEdge);
                panel.addMouseListener(cl1);

            }
        });
        button_randomGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edgeDefaultColor();

                node1=new Node();
                node2=new Node();

                panel.removeMouseListener(clickNode);
                panel.removeMouseListener(clickEdge);
                panel.removeMouseListener(cl1);

                Random rand = new Random();
                String userInput = JOptionPane.showInputDialog("Nhập vào số đỉnh của đồ thị:");
                int number = Integer.parseInt(userInput);
                int size = number;
                for (int i = 0; i < size; i++){
                    Node node = new Node();
                    int x = rand.nextInt(panel.getSize().width-30) + 185;
                    int y = rand.nextInt(panel.getSize().height-20) + 20;
                    node.setX(x);
                    node.setY(y);
                    listNode.addNode(node);
                    repaint();
                }

                listEdge=new EdgeList();

                for(int i=0;i<size;i++) {
                    for(int j=i+1;j<size;j++) {
//                        if (size < 10){
//                            int weight = rand.nextInt(50) ;
//                            edge = new Edge(listNode.getNode(i),listNode.getNode(j));
//                            listEdge.addEdge(edge);
//                            edge.setWeight(weight);
//                            repaint();
//                        }else{
                            int weight = rand.nextInt(101) - 50;
                            if(weight > 0){
                                edge = new Edge(listNode.getNode(i),listNode.getNode(j));
                                listEdge.addEdge(edge);
                                edge.setWeight(weight);
                                repaint();
                            }
//                        }


                    }
                }

            }
        });
    }

    public void nodeDefaultColor() {
        for (int i = 0; i < listNode.getSize(); i++) {
            Node a = listNode.getNode(i);
            a.setColor(Node.parseColor("#CC99FF"));
        }
    }

    public void edgeDefaultColor() {
        for (int i = 0; i < listEdge.getSize(); i++) {
            listEdge.getEdge(i).setColor(Color.BLUE);
        }
    }

    protected class ClickListenerNode extends MouseAdapter {
        ClickListenerNode() {
            super();
        }

        public void mouseClicked(MouseEvent e) {
            Node node = new Node();
            int x = e.getX() + 185;

            int y = e.getY() + 20;

            node.setX(x);
            node.setY(y);
            listNode.addNode(node);
            repaint();
        }
    }
    protected class ClickListenerEdge extends MouseAdapter {
        int i = 0;
        ClickListenerEdge() {
            super();
        }
        public void mouseClicked(MouseEvent e) {
            Node nodes = new Node();
            int x = e.getX() + 185;

            int y = e.getY() + 20;


            for (i = 0; i < listNode.getSize(); i++) {

                int z1 = listNode.getNode(i).getX();

                int z2 = listNode.getNode(i).getY();


                if ( ((x <= z1 + 15) && (x >= z1 - 15))
                        && ((y <= z2 + 15) && (y >= z2 - 15)) ) {
                    listNode.getNode(i).setColor(Node.parseColor("#9C27B0"));
                    repaint();

                    if (edge.hasA()) {
                        nodes.setX(z1);
                        nodes.setY(z2);
                        edge.setNode2(nodes);
                        break;
                    } else {
                        nodes.setX(z1);
                        nodes.setY(z2);
                        edge.setNode1(nodes);
                        break;
                    }
                }
            }

            if (edge.hasA() && edge.hasB()) {
                listEdge.addEdge(edge);
                String userInput = JOptionPane.showInputDialog("Nhập vào trọng số của cạnh:");
                int number = Integer.parseInt(userInput);
                edge.setWeight(number);

                edge = new Edge();
                nodeDefaultColor();
                repaint();
            }

        }

    }

    protected class ClickListener1 extends MouseAdapter
    {
        public ClickListener1() {
            super();
        }

        public void mouseClicked(MouseEvent e) {
            edgeDefaultColor();
            int x=e.getX()+185;
            int y=e.getY()+20;
            int i;
            node2=new Node();
            for(i=0; i< listNode.getSize(); i++) {
                int z1= listNode.getNode(i).getX();
                int z2= listNode.getNode(i).getY();

                if(((x<=z1+15)&&(x>=z1-15))&&((y<=z2+15)&&(y>=z2-15))) {

                    if(node1.isEmpty()) {
                        node1= listNode.getNode(i);
                        listNode.getNode(i).setColor(Node.parseColor("#9C27B0"));
                    }
                    else if(node2.isEmpty())
                    {node2= listNode.getNode(i);
                        listNode.getNode(i).setColor(Node.parseColor("#9C27B0"));
                    }
                    repaint();
                    break;
                }

            }

            //add the methods from dijkstras algorithm
            if(!node1.isEmpty()&&!node2.isEmpty()){
                Dijkstra dj=new Dijkstra();
                dj.execute(node1);
                //dj.getMinimalTree();
                dj.getPath(node1,node2);
            }
        }
    }

    protected class Dijkstra{
        private NodeList<Node> nodes;
        private EdgeList<Edge> edges;
        private Set<Node> visitedNodes;
        private Set<Node> unvisitedNodes;
        private Map<Node, Integer> totalWeight;
        private Map<Node, Node> prevNodes;

        public Dijkstra() {
            this.nodes = listNode;
            this.edges= listEdge;
        }

        public void execute(Node start) {
            visitedNodes=new HashSet<>();
            unvisitedNodes=new HashSet<>();
            totalWeight=new HashMap<>();
            prevNodes=new HashMap<>();
            this.totalWeight.put(start, 0);
            unvisitedNodes.add(start);
            while(unvisitedNodes.size()>0) {
                Node node=getMinimum(unvisitedNodes);
                visitedNodes.add(node);
                unvisitedNodes.remove(node);
                findMinimalWeights(node);
            }
        }

        private Node getMinimum(Set<Node> nodes) {
            Node minimum=null;
            for(Node node:nodes) {
                if(minimum==null) {
                    minimum=node;
                }else{
                    if(this.getShortestDistance(node)<this.getShortestDistance(minimum)) {
                        minimum=node;
                    }
                }
            }
            return minimum;
        }

        public void findMinimalWeights(Node node) {
            NodeList<Node> adjNodes=getNeighbours(node);
            for(int i=0;i<adjNodes.getSize();i++) {
                Node target=adjNodes.getNode(i);
                if(getShortestDistance(target)>getShortestDistance(node)+getDistance(node,target)) {
                    totalWeight.put(target, getShortestDistance(node)+getDistance(node,target));
                    prevNodes.put(target, node);
                    unvisitedNodes.add(target);
                }
            }
        }

        private int getDistance(Node node, Node target)  {
            int weight=0;
            Edge edge=new Edge(node,target);
            int index=this.getIndexOf(edge);
            if(index!=-1)
                weight=edges.getEdge(index).getWeight();
            else {
                throw new RuntimeException("no such edge");
            }
            return weight;
        }

        private int getShortestDistance(Node node) {
            Integer d=totalWeight.get(node);
            if(d==null) {
                return Integer.MAX_VALUE;
            }
            else
                return d;
        }

        private NodeList<Node> getNeighbours(Node node) {
            NodeList<Node> temp=new NodeList<>();
            for(int i=0;i<nodes.getSize();i++) {
                if(!visitedNodes.contains(nodes.getNode(i))) {
                    if(this.checkNeighbour(new Edge(node,nodes.getNode(i)))==true){
                        temp.addNode(nodes.getNode(i));
                    }
                }

            }

            return temp;
        }

        public boolean checkNeighbour(Edge e) {
            int x1,x2,y1,y2,a,b,c,d;//a=x1 b=x2 c=y1 d=y2 where abcd are point of edge from edgelist
            boolean check=false;
            x1=e.getNode1().getX();
            x2=e.getNode2().getX();
            y1=e.getNode1().getY();
            y2=e.getNode2().getY();
            for(int i=0;i<edges.getSize();i++) {
                a=edges.getEdge(i).getNode1().getX();
                b=edges.getEdge(i).getNode2().getX();
                c=edges.getEdge(i).getNode1().getY();
                d=edges.getEdge(i).getNode2().getY();
                if(a==x1&&b==x2&&c==y1&&d==y2) {
                    check= true;
                    break;
                }
                if(a==x2&&b==x1&&c==y2&&d==y1) {
                    check= true;
                    break;
                }
            }
            return check;
        }
        public int getIndexOf(Edge e) {
            int x1,x2,y1,y2,a,b,c,d,index=-1;//a=x1 b=x2 c=y1 d=y2 where abcd are point of edge from edgelist
            x1=e.getNode1().getX();
            x2=e.getNode2().getX();
            y1=e.getNode1().getY();
            y2=e.getNode2().getY();
            for(int i=0;i<edges.getSize();i++) {
                a=edges.getEdge(i).getNode1().getX();
                b=edges.getEdge(i).getNode2().getX();
                c=edges.getEdge(i).getNode1().getY();
                d=edges.getEdge(i).getNode2().getY();
                if(a==x1&&b==x2&&c==y1&&d==y2) {
                    index=i;
                    break;
                }
                if(a==x2&&b==x1&&c==y2&&d==y1) {
                    index= i;
                    break;
                }
            }
            return index;
        }

        //get the shortest path from one node to another
        public void getPath(Node a, Node b) {
            nodeDefaultColor();
            if(!b.isEmpty()) {
                int x,y;
                ArrayList<Node> keys=new ArrayList<>(prevNodes.keySet());
                LinkedList<Node> path=new LinkedList<>();
                Node start=b;//was b
                for(int i=0;i<keys.size();i++) {
                    x=keys.get(i).getX();
                    y=keys.get(i).getY();
                    if(x==start.getX()&&y==start.getY()) {
                        start=keys.get(i);
                        break;
                    }
                }
                if(prevNodes.get(start)==null) {
                    return;
                }
                path.add(start);
                while(prevNodes.get(start)!=null) {
                    path.add(prevNodes.get(start));
                    start=prevNodes.get(start);
                    start.setColor(Node.parseColor("#9C27B0"));
                }
                node2.setColor(Node.parseColor("#9C27B0"));
                //reverse the linklist
                Collections.reverse(path);

                int i;
                for(i=0;i<path.size();i++) {
                    try {
                        int index=this.getIndexOf(new Edge(path.get(i),path.get(i+1)));
                        listEdge.getEdge(index).setColor(Color.RED);

                    }
                    catch(IndexOutOfBoundsException e){

                    }
                }
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        UIDes g= new UIDes();
//        g.setContentPane(g.panelMain);
//        g.setTitle("Demo");
//        g.setVisible(true);
//        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        g.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
