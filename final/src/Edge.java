import java.awt.*;

public class Edge {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int weight;
    Color color = Color.BLUE;

    Node node;

    public Edge() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        weight = Integer.MIN_VALUE;
        node = new Node();
    }

    public Edge(Node node1, Node node2, int weight) {
        x1 = node1.getX() + 10;
        y1 = node1.getY() + 10;

        x2 = node2.getX() + 10;
        y2 = node2.getY() + 10;
        this.weight = weight;

    }
    public void setNode1(Node node1) {
        x1 = node1.getX() + 10;
        y1 = node1.getY() + 10;
    }

    public void setNode2(Node node2) {
        x2 = node2.getX() + 10;
        y2 = node2.getY() + 10;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void draw(Graphics graphics) {
        int midPointX = (x1 + x2) / 2;
        int midPointY = (y1 + y2) / 2;
        graphics.setColor(Color.BLACK);
        graphics.drawString(Integer.toString(weight), midPointX, midPointY);
        graphics.setColor(color);
        graphics.drawLine(x1, y1, x2, y2);
    }
    public Node getPoint1() {
        Node node1 = new Node(x1, y1);
        return node1;
    }

    public Node getNode2() {
        Node node2 = new Node(x2, y2);
        return node2;
    }

    public boolean hasA() {
        return (x1 > 0 && y1 > 0);
    }
    public boolean hasB() {
        return (x2 > 0 && y2 > 0);
    }
    public boolean equalsNode1(int x, int y) {
        return (x == x1 && y == y1);
    }
    public boolean equalsNode2(int x, int y) {
        return (x == x2 && y == y2);
    }
    public boolean equalsEdge(int x, int y) {
        return (this.equalsNode1(x, y) || this.equalsNode2(x, y));
    }

    public boolean equal(int x, int y) {
        int c = ( y - y1 ) * ( x2 - x2 );
        int d = ( y2 - y1 ) * ( x - x1 );
        if (c > d - 20 && c < d + 20) {
            return true;
        }
        return false;
    }
    public boolean checkEdge(int x, int y) {
        int i;
        int a;
        int b;
        if(this.equal(x,y)) return true;

        for (i = 1; i <= 20; i++) {
            a = x;
            b = y + i;

            if (this.equal(a, b)) return true;
            a = x;
            b = y - i;
            if(this.equal(a,b)) return true;
            a=x-i;b=y;
            if(this.equal(a,b)) return true;
            a=x+i;b=y;
            if(this.equal(a,b)) return true;
            a=x+i;b=y+i;
            if(this.equal(a,b)) return true;
            a=x-i;b=y-i;
            if(this.equal(a,b)) return true;
            a=x+i;b=y-i;
            if(this.equal(a,b)) return true;
            a=x-i;b=y+i;
            if(this.equal(a,b)) return true;
        }
        if (i >= 10) {
            return false;
        }
        return true;
    }
}
