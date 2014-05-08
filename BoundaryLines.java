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

/*
public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Gate> gates;
    private ArrayList<Item> items;
    private int[][] boundaryPoints;
    private int[][] gatePoints;
    private int[][] itemPoints;
    public Gate crossedGate;
    public Item touchedItem;
    private Place currentPlace;
//    public ItemPanel itemPanel;
    // flags for dictating which directions are blocked off
    public boolean moveL = true;
    public boolean moveR = true;
    public boolean moveU = true;
    public boolean moveD = true;

    // ------------ Constructors ------------ \\
    // only used in the MainWindow constructor
    public BoundaryLines() {
        this.lines = new ArrayList<Line2D.Double>();
        this.gates = new ArrayList<Gate>();
        this.items = new ArrayList<Item>();
    }
    
    // constructor for places with items
     public BoundaryLines(Place place, int offsetX, int offsetY) {
        this.createLines(place, offsetX, offsetY);
    }

    // ------------ Paint ------------ \\
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.RED);
        // draw lines
        for (Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        // draw gates
        pen.setColor(Color.BLUE);
        for (Gate gate : this.gates) {
            pen.draw(gate.line);
        }
        // draw items
        if (this.items.size() > 0) {
            pen.setColor(Color.GREEN);
            for (Item item : this.items) {
                pen.draw(item.box);
//                pen.drawImage(item.image, item.getX(), item.getY(), null);
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
        for (Gate gate : this.gates) {
            gate.loadLine(gate.line.getX1() + dx, 
                          gate.line.getY1() + dy, 
                          gate.line.getX2() + dx, 
                          gate.line.getY2() + dy);
        }
        // move items
        if (this.items.size() > 0) {
            for (Item item : this.items) {
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
        for (Gate gate : this.gates) {
            if (box.intersectsLine(gate.line)) {
                this.crossedGate = gate;
                return true;
            }
        }
        return false;
    }
    
    // tests if a Rectangle intersected an item's box
    public boolean itemTouched(Rectangle2D.Double box) {
        for (int i = 0; i < this.items.size(); i++) {
            Item item = this.items.get(i);
            if (box.intersects(item.box.getX(),
                               item.box.getY(),
                               item.box.getWidth(),
                               item.box.getHeight())) {
                this.touchedItem = item;
                this.currentPlace.removeItem(item);
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
    
    // ------------ Send Items ------------ \\
    public ArrayList<Item> sendItems() {
        return this.items;
    }
    
    // ------------ Build Shapes ------------ \\    
    // method for place as parameter
    public void createLines(Place place, int offsetX, int offsetY) {
        // sets the background and lines invisible
        setOpaque(false);    
//        setVisible(false);
        
        this.currentPlace = place;
        
        // create boundary lines
        this.boundaryPoints = this.currentPlace.sendBoundaryPoints();
        this.lines = new ArrayList<Line2D.Double>();
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
        this.gatePoints = this.currentPlace.sendGatePoints();
        this.gates = this.currentPlace.sendGates();
        this.crossedGate = null;
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
        
        // create item boxes
        if (this.currentPlace.hasItems == true) {
            this.itemPoints = this.currentPlace.sendItemPoints();
            this.items = this.currentPlace.sendItems();
            this.touchedItem = null;
            int itemIterator = 0;
            for (int i = 0; i < this.itemPoints.length; i++) {
                for (int j = 1; j < this.itemPoints[i].length; j+=1) {
                    this.items.get(itemIterator).loadBox(
                                                         this.itemPoints[i][j-1] - offsetX,
                                                         this.itemPoints[i][j] - offsetY
                                                        );
                }
                itemIterator++;
            }
        }
        else this.items = new ArrayList<Item>();
    }
    
  
}
*/

///*
public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
//    private ArrayList<Gate> gates;
//    private ArrayList<Item> items;
//    private int[][] boundaryPoints;
//    private int[][] gatePoints;
//    private int[][] itemPoints;
    public Gate crossedGate;
    public Item touchedItem;
    private Place place;
//    public ItemPanel itemPanel;
    // flags for dictating which directions are blocked off
    public boolean moveL = true;
    public boolean moveR = true;
    public boolean moveU = true;
    public boolean moveD = true;

    // ------------ Constructors ------------ \\
    // only used in the MainWindow constructor
//    public BoundaryLines() {
//        this.crossedGate = null;
//        this.touchedItem = null;
//        this.currentPlace = null;
////        this.lines = new ArrayList<Line2D.Double>();
//        this.gates = new ArrayList<Gate>();
//        this.items = new ArrayList<Item>();
//    }
    
    // constructor for places with items
     public BoundaryLines(Place place, int offsetX, int offsetY) {
         this.place = place;
         this.createLines(place, offsetX, offsetY);
    }

    // ------------ Paint ------------ \\
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.RED);
        // draw lines
        for (Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        // draw gates
        pen.setColor(Color.BLUE);
        for (Gate gate : this.place.sendGates()) {
            pen.draw(gate.line);
        }
        // draw items
        if (this.place.hasItems == true) {
            pen.setColor(Color.GREEN);
            for (Item item : this.place.sendItems()) {
                pen.draw(item.box);
//                pen.drawImage(item.image, item.getX(), item.getY(), null);
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
        if (this.place.hasItems == true) {
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
    
//    // ------------ Send Items ------------ \\
//    public ArrayList<Item> sendItems() {
//        return this.items;
//    }
    
    // ------------ Build Shapes ------------ \\    
    // method for place as parameter
    public void createLines(Place place, int offsetX, int offsetY) {
        // sets the background and lines invisible
        setOpaque(false);    
//        setVisible(false);
        
        this.place = place;
        
        // create boundary lines
//        this.boundaryPoints = this.place.sendBoundaryPoints();
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
        // create gate lines
//        this.gatePoints = this.currentPlace.sendGatePoints();
//        this.gates = this.currentPlace.sendGates();
        this.crossedGate = null;
        int gateIterator = 0;
        for (int i = 0; i < this.place.sendGatePoints().length; i++) {
            for (int j = 3; j < this.place.sendGatePoints()[i].length; j+=3) {
                this.place.sendGates().get(gateIterator).loadLine(
                                                      this.place.sendGatePoints()[i][j-3] - offsetX,
                                                      this.place.sendGatePoints()[i][j-2] - offsetY,
                                                      this.place.sendGatePoints()[i][j-1] - offsetX,
                                                      this.place.sendGatePoints()[i][j] - offsetY
                                                     );
            }
            gateIterator++;
        }
        
        // create item boxes
        if (this.place.hasItems == true) {
//            this.itemPoints = this.place.sendItemPoints();
//            this.items = this.currentPlace.sendItems();
            this.touchedItem = null;
            int itemIterator = 0;
            for (int i = 0; i < this.place.sendItemPoints().length; i++) {
                for (int j = 1; j < this.place.sendItemPoints()[i].length; j+=1) {
                    this.place.sendItems().get(itemIterator).loadBox(
                                                         this.place.sendItemPoints()[i][j-1] - offsetX,
                                                         this.place.sendItemPoints()[i][j] - offsetY
                                                        );
                }
                itemIterator++;
            }
        }
//        else this.items = new ArrayList<Item>();
    }
    
  
}
//*/



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