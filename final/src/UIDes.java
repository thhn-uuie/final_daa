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

public class UIDes extends JFrame {
    private JButton btDFS;
    private JButton btDijkstra;
    private JButton btBellmanFord;
    private JPanel panelMain;
    private JRadioButton button_AddNode;

    private JRadioButton button_AddEdge;
    private JButton button_Run;
    private JButton button_randomGraph;
    private JScrollPane panel_show;
    private JPanel panel;

    Node node1;
    Node node2;

    Edge edge = new Edge();
    EdgeList<Edge> listEdge = new EdgeList<>();
    NodeList<Node> listNode = new NodeList<>();

    ClickListenerNode clickNode = new ClickListenerNode();
    ClickListenerEdge clickEdge = new ClickListenerEdge();
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
        if (listNode.getSize() > 0) {
            for (int i = 0; i < listNode.getSize(); i++) {
                listNode.getNode(i).draw(graphics, i);
            }
        }

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

        // radio button to add gui.Node
        button_AddNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeMouseListener(clickEdge);
                panel.addMouseListener(clickNode);
            }
        });

        // radio button to add gui.Edge
        button_AddEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeMouseListener(clickNode);
                panel.addMouseListener(clickEdge);
            }
        });
    }

    public void nodeDefaultColor() {
        for (int i = 0; i < listNode.getSize(); i++) {
            listNode.getNode(i).setColor(Color.RED);
        }
    }

    public void edgeDefaultColor() {
        for (int i = 0; i < listEdge.getSize(); i++) {
            listEdge.getEdge(i).setColor(Color.BLUE);
        }
    }

    public void removeMouseAdapters() {
        panel.removeMouseListener(clickNode);
        panel.removeMouseListener(clickEdge);
    }
    protected class ClickListenerNode extends MouseAdapter {
        ClickListenerNode() {
            super();
        }

        public void mouseClicked(MouseEvent e) {
            Node node = new Node();
            int x = e.getX() + 185;

            int y = e.getY() + 20;

            node.setColor(Color.RED);
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
                    listNode.getNode(i).setColor(Color.GREEN);
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
