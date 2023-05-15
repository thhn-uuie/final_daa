import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawNode {
    private int x, y;
    private boolean isClicked = false;

    public DrawNode() {
        x = 0;
        y = 0;
    }
    public DrawNode(int a, int b) {
        x = a;
        y = b;

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void draw(Graphics graph) {
        if (isClicked) {
            graph.fillOval(x, y, 10, 10);
            isClicked = false;
        }
    }
}
