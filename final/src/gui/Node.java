package gui;

import java.awt.*;

public class Node {
    private int x;
    private int y;
    private Color color;
    public Node() {
        color = parseColor("#CC99FF");
        x = 0;
        y = 0;
    }
    public Node(int x, int y) {
        this.color = parseColor("#CC99FF");
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
        graphics.fillOval(x, y, 30, 30);
    }

    public void insertString(Graphics graphics, int i) {
        Font font = new Font("Arial", Font.BOLD, 14);
        graphics.setFont(font);

        graphics.setColor(Color.BLACK);
        FontMetrics fm = graphics.getFontMetrics();
        double t_width = fm.getStringBounds(String.valueOf(i), graphics).getWidth();
        graphics.drawString(String.valueOf(i), (int) (x - t_width / 2) + 15, (y + fm.getMaxAscent() / 2) + 15);

    }

    public static Color parseColor(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }
}
