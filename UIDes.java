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


    public UIDes() {
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        UIDes g= new UIDes();
        g.setContentPane(g.panelMain);
        g.setTitle("Demo");
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
