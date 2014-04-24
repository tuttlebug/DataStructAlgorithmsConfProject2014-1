import java.util.* ;
import java.io.*;
import javax.swing.*;    // JPanel
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent

public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    private int[][] points;
    
    public BoundaryLines(int[][] points, int offsetX, int offsetY) {
        this.points = points;
        this.lines = new ArrayList<Line2D.Double>();
        setOpaque(false);
        
        // create lines
        for (int i = 0; i < this.points.length; i++) {
            for (int j = 3; j < this.points[i].length; j+=3) {
                Line2D.Double newLine = new Line2D.Double(
                                                          this.points[i][j-3] - offsetX,
                                                          this.points[i][j-2] - offsetY,
                                                          this.points[i][j-1] - offsetX,
                                                          this.points[i][j] - offsetY
                                                         );
                this.lines.add(newLine);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.RED);
        for (Line2D.Double line : this.lines) {
//            System.out.printf("x1 = %f, y1 = %f, x2 = %f, y2 = %f\n", 
//                              line.getX1(), line.getY1(), line.getX2(), line.getY2());
            pen.draw(line);
        }
        // makes background transparent
        g.setColor(getBackground());
    }

    public void moveLines(int dx, int dy) {
        for ( Line2D.Double line : this.lines) {
            line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
        }
    }
    
    public boolean crossed(Rectangle2D.Double box) {
        for (Line2D.Double line : this.lines) {
            if (box.intersectsLine(line)) {
                return true;
            }
        }
        return false;
    }
}