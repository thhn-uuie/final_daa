import java.awt.*;

public class Node {
    private int x;
    private int y;
    private Color color;
    public Node() {
        this.color = color.RED;
        x = 0;
        y = 0;
    }
    public Node(int x, int y) {
        this.color = color.RED;
        this.x = x;
        this.y = y;
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

    public String getColor() {
        return color.toString();
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public boolean isEmpty() {
        if (this.x == 0 && this.y == 0) {
            return true;
        }
        return false;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(x, y, 20, 20);
    }
}
