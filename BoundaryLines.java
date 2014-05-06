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
//    private int x1 = 51142; ///
//    private int y1 = 40028; ///
    
    // only used in the MainWindow constructor
    public BoundaryLines() {
        setOpaque(false);
        this.lines = new ArrayList<Line2D.Double>();
        this.gates = new ArrayList<Gate>();
    }
    
    public BoundaryLines(int[][] boundaryPoints, int[][] gatePoints, ArrayList<Gate> gates, int offsetX, int offsetY) {
        this.createLines(boundaryPoints, gatePoints, gates, offsetX, offsetY);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.RED);
        for (Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        pen.setColor(Color.BLUE);
        for (Gate gate : this.gates) {
            pen.draw(gate.line);
        }
        // makes background transparent
        g.setColor(getBackground());
    }
 
    public void moveLines(int dx, int dy) {
        for (Line2D.Double line : this.lines) {
            line.setLine(line.getX1() + dx, 
                         line.getY1() + dy, 
                         line.getX2() + dx, 
                         line.getY2() + dy);
            repaint();
        }
        for (Gate gate : this.gates) {
            gate.loadLine(gate.line.getX1() + dx, 
                          gate.line.getY1() + dy, 
                          gate.line.getX2() + dx, 
                          gate.line.getY2() + dy);
            repaint();
//            x1 -= dx;
//            y1 -= dy;
//            System.out.printf("fake x1 = %d, fake y1 = %d\n", x1, y1);
//            System.out.println(gate);
        }
    }
    
    // tests if a Rectangle intersected any of the boundary lines
    public boolean boundaryCrossed(Rectangle2D.Double box) {
        for (Line2D.Double line : this.lines) {
            if (box.intersectsLine(line)) {
//                System.out.printf("boundary hit: %f, %f, %f, %f\n",
//                                  this.lines.get(0).getX1(),
//                                  this.lines.get(0).getY1(),
//                                  this.lines.get(0).getX2(),
//                                  this.lines.get(0).getY2());
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
//                System.out.printf("gate hit: %f, %f, %f, %f\n",
//                                  this.gates.get(0).line.getX1(),
//                                  this.gates.get(0).line.getY1(),
//                                  this.gates.get(0).line.getX2(),
//                                  this.gates.get(0).line.getY2());
                return true;
            }
        }
        return false;
    }
    
    public Gate getCrossedGate() {
        return this.crossedGate;
    }
    
    public void createLines(int[][] boundaryPoints, int[][] gatePoints, ArrayList<Gate> gates, int offsetX, int offsetY) {
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
//                System.out.printf("%d = %s\n", gateIterator, this.gates.get(gateIterator));
                this.gates.get(gateIterator).loadLine(
                                                  this.gatePoints[i][j-3] - offsetX,
                                                  this.gatePoints[i][j-2] - offsetY,
                                                  this.gatePoints[i][j-1] - offsetX,
                                                  this.gatePoints[i][j] - offsetY
                                                 );
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
            }
            gateIterator++;
        }
    }
    
    
}