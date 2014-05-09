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
 * [] Move as a subclass to / put in Place
 */

public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    private Color r;
    private Color b;
    private Color g;
    private Color img;
//    private ArrayList<Gate> gates;
//    private ArrayList<Item> items;
//    private int[][] boundaryPoints;
//    private int[][] gatePoints;
//    private int[][] itemPoints;
    public Gate crossedGate;
    public Item touchedItem;
    private Place place;
    // flags for dictating which directions are blocked off
    public boolean moveL = true;
    public boolean moveR = true;
    public boolean moveU = true;
    public boolean moveD = true;

    // ------------ Constructor ------------ \\
    
    // constructor for places with items
     public BoundaryLines(Place place, int offsetX, int offsetY) {
         this.place = place;
         this.createLines(place, offsetX, offsetY);
         this.r = new Color(255, 0, 0, 255);
         this.b = new Color(0, 0, 255, 255);
         this.g = new Color(0, 255, 0, 255);
         this.img = new Color(0, 0, 0, 255);
    }

    // ------------ Paint ------------ \\
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(this.r);
        // draw lines
        for (Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        // draw gates
        pen.setColor(this.b);
        for (Gate gate : this.place.sendGates()) {
            pen.draw(gate.line);
        }
        // draw items
        if (this.place.hasItems()) {
            pen.setColor(this.g);
            for (Item item : this.place.sendItems()) {
                pen.draw(item.box);
                pen.setColor(this.img);
                pen.drawImage(item.image, item.getX(), item.getY(), null); 
                System.out.printf("x = %d, y = %d\n", item.getX(), item.getY());
            }
        }
        // makes background transparent
        g.setColor(getBackground());
    }
 
    // ------------ Movement ------------ \\
    // move shapes across screen
    public void moveLines(int dx, int dy) {
        // move lines
        for (Line2D.Double line : this.lines) {
            line.setLine(line.getX1() + dx, 
                         line.getY1() + dy, 
                         line.getX2() + dx, 
                         line.getY2() + dy);
        }
        // move gates
        for (Gate gate : this.place.sendGates()) {
            gate.loadLine(gate.line.getX1() + dx, 
                          gate.line.getY1() + dy, 
                          gate.line.getX2() + dx, 
                          gate.line.getY2() + dy);
        }
        // move items
        if (this.place.hasItems()) {
            for (Item item : this.place.sendItems()) {
                item.loadBox(item.box.getX() + dx, 
                             item.box.getY() + dy);
            }
        }
    }
    
    // ------------ Collision Detection ------------ \\
    // tests if a Rectangle intersected any of the boundary lines
    public boolean boundaryCrossed(Rectangle2D.Double box) {
        for (Line2D.Double line : this.lines) {
            if (box.intersectsLine(line)) {
                return true;
            }
        }
        return false;
    }
   
    // tests if a Rectangle intersected any of the gates
    public boolean gateCrossed(Rectangle2D.Double box) {
        for (Gate gate : this.place.sendGates()) {
            if (box.intersectsLine(gate.line)) {
                this.crossedGate = gate;
                return true;
            }
        }
        return false;
    }
    
    // tests if a Rectangle intersected an item's box
    public boolean itemTouched(Rectangle2D.Double box) {
        for (int i = 0; i < this.place.sendItems().size(); i++) {
            Item item = this.place.sendItems().get(i);
            if (box.intersects(item.box.getX(),
                               item.box.getY(),
                               item.box.getWidth(),
                               item.box.getHeight())) {
                this.touchedItem = item;
                this.place.removeItem(item);
                return true;
            }
        }    
        return false;
    }
    
    public Gate getCrossedGate() {
        return this.crossedGate;
    }
    
    public Item getTouchedItem() {
        return this.touchedItem;
    }
    
    // ------------ Remove Shapes ------------ \\
    public void removeLines() {
        
    }

    // ------------ Build Shapes ------------ \\    
    // method for place as parameter
    public void createLines(Place place, int offsetX, int offsetY) {
        // sets the background and lines invisible
        setOpaque(false);    
        
        this.place = place;
        
        // create boundary lines
        this.lines = new ArrayList<Line2D.Double>();
        for (int i = 0; i < this.place.sendBoundaryPoints().length; i++) {
            for (int j = 3; j < this.place.sendBoundaryPoints()[i].length; j+=3) {
                Line2D.Double newLine = new Line2D.Double(
                                                          this.place.sendBoundaryPoints()[i][j-3] - offsetX,
                                                          this.place.sendBoundaryPoints()[i][j-2] - offsetY,
                                                          this.place.sendBoundaryPoints()[i][j-1] - offsetX,
                                                          this.place.sendBoundaryPoints()[i][j] - offsetY
                                                         );
                this.lines.add(newLine);
            }
        }
    }
    
  
}


// PRINT STATEMENTS
//            x1 -= dx;
//            y1 -= dy;
//            System.out.printf("fake x1 = %d, fake y1 = %d\n", x1, y1);
//            System.out.println(gate);
//                System.out.printf("boundary hit: %f, %f, %f, %f\n",
//                                  this.lines.get(0).getX1(),
//                                  this.lines.get(0).getY1(),
//                                  this.lines.get(0).getX2(),
//                                  this.lines.get(0).getY2());
//                System.out.printf("gate hit: %f, %f, %f, %f\n",
//                                  this.gates.get(0).line.getX1(),
//                                  this.gates.get(0).line.getY1(),
//                                  this.gates.get(0).line.getX2(),
//                                  this.gates.get(0).line.getY2());
//                System.out.printf("%d = %s\n", gateIterator, this.gates.get(gateIterator));
//                System.out.printf("Points without offset:\nx1 = %d, y1 = %d, x2 = %d, y2 = %d\n",
//                                  this.gatePoints[i][j-3],
//                                  this.gatePoints[i][j-2],
//                                  this.gatePoints[i][j-1],
//                                  this.gatePoints[i][j]);
//                System.out.printf("Points with offset:\nx1 = %d, y1 = %d, x2 = %d, y2 = %d\n",
//                                  this.gatePoints[i][j-3] - offsetX,
//                                  this.gatePoints[i][j-2] - offsetY,
//                                  this.gatePoints[i][j-1] - offsetX,
//                                  this.gatePoints[i][j] - offsetY);
//                System.out.printf("%d = %s\n", gateIterator, this.gates.get(gateIterator));
//