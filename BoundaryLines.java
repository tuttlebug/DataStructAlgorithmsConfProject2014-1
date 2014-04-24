import java.util.* ;
import java.io.*;
import javax.swing.*;    // JPanel
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent

/**
 * CHECK-LIST:
 * []
 * 
 * To Add/Implement:
 * [] collided
 * [] collided handles diagonal lines
 */

public class BoundaryLines extends JPanel implements Colideable {
    
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
                this.collided(box, line);
                return true;
            }
        }
        return false;
    }
    
    public void collided(Rectangle2D.Double box, Line2D.Double line) {
        /**
         * if ( (box.x  = line.x1) && ( (box.y >= line.y1) && (box.y <= line.y2);
         */
        System.out.println("IN COLLIDED");
        System.out.printf("box: x1 = %f, y1 = %f, x2 = %f, y2 = %f\nline = x1 = %f, y1 = %f, x2 = %f, y2 = %f\n", 
                          box.getX(), box.getY(), box.getX() + box.getWidth(), box.getHeight(),
                          line.getX1(), line.getY1(), line.getX2(), line.getY2());
        // check if line is to the left of box
        if ( ( (box.getX() >= line.getX1()) && (box.getX() <= line.getX2()) ) 
                && ( (box.getX() >= line.getY1()) && (box.getX() <= line.getY2()) ) ) {
            System.out.println("left");
        }
        
        // check if line is to the right of the box
        if ( ( (box.getX() <= line.getX1()) && (box.getX() >= line.getX2()) ) 
                && ( (box.getX() >= line.getY1()) && (box.getX() <= line.getY2()) )) {
            System.out.println("right");
        }
        
        // check if box is on top
        if ( ( (box.getY() - box.getHeight() <= line.getY1()) && (box.getY() - box.getHeight() >=  line.getY2()) )
                && ( (box.getY() - box.getHeight() >= line.getX1()) && (box.getY() - box.getHeight() <= line.getX2()) )) {
            System.out.println("top");
        }
        
        // check id box is at bottom
        if ( ( (box.getY() >= line.getY1()) && (box.getY() <=  line.getY2()) )
                && ( (box.getY() >= line.getX1()) 
                        && (box.getY()  <= line.getX2()) )) {
            System.out.println("bottom");
        }
    }
}