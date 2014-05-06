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
/*
// VERSION 1
public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Line2D.Double> gates;
    private int[][] boundaryPoints;
    private int[][] gatePoints;
    // flags for dictating which directions are blocked off
    public boolean moveL = true;
    public boolean moveR = true;
    public boolean moveU = true;
    public boolean moveD = true;
    
    // only used in the MainWindow constructor
    public BoundaryLines() {
        
    }
    
    public BoundaryLines(int[][] boundaryPoints, int[][] gatePoints, int offsetX, int offsetY) {
        this.boundaryPoints = boundaryPoints;
        this.gatePoints = gatePoints;
        this.lines = new ArrayList<Line2D.Double>();
        this.gates = new ArrayList<Line2D.Double>();
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
        for (int i = 0; i < this.gatePoints.length; i++) {
            for (int j = 3; j < this.gatePoints[i].length; j+=3) {
                Line2D.Double newGate = new Line2D.Double(
                                                          this.gatePoints[i][j-3] - offsetX,
                                                          this.gatePoints[i][j-2] - offsetY,
                                                          this.gatePoints[i][j-1] - offsetX,
                                                          this.gatePoints[i][j] - offsetY
                                                         );
                this.gates.add(newGate);
            }
        }
        System.out.println(this.gates);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.RED);
        for (Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        for (Line2D.Double gate : this.gates) {
            pen.draw(gate);
        }
        // makes background transparent
        g.setColor(getBackground());
    }

    public void moveLines(int dx, int dy) {
        for (Line2D.Double line : this.lines) {
            line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
        }
        for (Line2D.Double gate : this.gates) {
            gate.setLine(gate.getX1() + dx, gate.getY1() + dy, gate.getX2() + dx, gate.getY2() + dy);
        }
    }
    
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
        for (Line2D.Double gate : this.gates) {
            if (box.intersectsLine(gate)) {
                return true;
            }
        }
        return false;
    }
}
*/

public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Gate> gates;
    private int[][] boundaryPoints;
    private int[][] gatePoints;
    public Gate crossedGate;
    // flags for dictating which directions are blocked off
    public boolean moveL = true;
    public boolean moveR = true;
    public boolean moveU = true;
    public boolean moveD = true;
    
    // only used in the MainWindow constructor
    public BoundaryLines() {
        
    }
    
    public BoundaryLines(int[][] boundaryPoints, int[][] gatePoints, ArrayList<Gate> gates, int offsetX, int offsetY) {
        this.boundaryPoints = boundaryPoints;
        this.gatePoints = gatePoints;
        this.gates = gates;
        this.lines = new ArrayList<Line2D.Double>();
        this.crossedGate = null;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.RED);
        for (Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        for (Gate gate : this.gates) {
            pen.draw(gate.line);
        }
        // makes background transparent
        g.setColor(getBackground());
    }

    public void moveLines(int dx, int dy) {
        for (Line2D.Double line : this.lines) {
            line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
        }
        for (Gate gate : this.gates) {
            System.out.println(gate);
            gate.line.setLine(gate.line.getX1() + dx, gate.line.getY1() + dy, 
                                    gate.line.getX2() + dx, gate.line.getY2() + dy);
            System.out.println(gate);
        }
        System.out.printf("boundary = %f, %f\n", 
                          this.lines.get(0).getX1(),
                          this.lines.get(0).getY1());
    }
    
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
    
    public Gate getCrossedGate() {
        return this.crossedGate;
    }
    
    
}