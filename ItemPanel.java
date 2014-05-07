import java.util.* ;
import java.io.*;
import javax.swing.*;    // JPanel
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent

/**
 * CHECK-LIST:
 * []
 * To Add/Implement:
 * []
 */

public class ItemPanel extends JPanel {
    
    private ArrayList<Rectangle2D.Double> boxes;
    private ArrayList<Item> items;
    public Item itemTouched;
    
    // only used in the MainWindow constructor
    public ItemPanel() {
        setOpaque(false);
        this.boxes = new ArrayList<Rectangle2D.Double>();
    }
    
    public ItemPanel(int[] coords, int offsetX, int offsetY) {
        this.createBoxes(coords, offsetX, offsetY);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.GREEN);
        for (Rectangle2D.Double box : this.boxes) {
            pen.draw(box);
        }
        // makes background transparent
        g.setColor(getBackground());
    }
 
    // move boxes
    public void moveLines(int dx, int dy) {
//        for (Rectangle2D.Double box : this.boxes) {
//            line.setLine(line.getX1() + dx, 
//                         line.getY1() + dy, 
//                         line.getX2() + dx, 
//                         line.getY2() + dy);
//        }
    }
    
    // tests if a Rectangle intersected any of the boundary lines
    public boolean boundaryCrossed(Rectangle2D.Double box) {
        for (Line2D.Double line : this.lines) {
            if (box.intersectsLine(line)) {
                this.itemTouched = item;
                return true;
            }
        }
        return false;
    }
    
    public Item getitemTouched() {
        return this.itemTouched;
    }
    
    public void createBoxes(int[] coords, int offsetX, int offsetY) {
        this.boxes = new ArrayList<Rectangle2D.Double>();
        this.boxTouched = null;
        // sets the background and lines invisible
        setOpaque(false);    
//        setVisible(false);
        
        // create boundary lines
        for (int i = 0; i < this.boundaryPoints.length; i++) {
            for (int j = 3; j < this.boundaryPoints[i].length; j+=3) {
                Line2D.Double newLine = new Line2D.Double(
                                                          this.boundaryPoints[i][j-3] - offsetX,
                                                          this.boundaryPoints[i][j-2] - offsetY,
                                                          this.boundaryPoints[i][j-1] - offsetX,
                                                          this.boundaryPoints[i][j] - offsetY
                                                         );
                this.lines.add(newLine);
            }
        }
        // create gate lines
        int gateIterator = 0;
        for (int i = 0; i < this.gatePoints.length; i++) {
            for (int j = 3; j < this.gatePoints[i].length; j+=3) {
                this.gates.get(gateIterator).loadLine(
                                                  this.gatePoints[i][j-3] - offsetX,
                                                  this.gatePoints[i][j-2] - offsetY,
                                                  this.gatePoints[i][j-1] - offsetX,
                                                  this.gatePoints[i][j] - offsetY
                                                 );
            }
            gateIterator++;
        }
    }
}