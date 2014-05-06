/*
 * A set of points in areas that mark where a character can enter into a different screen;
 * 
 * Check List:
 * [] A Map of areas that the gate connects to
 * [] XY coordinates in the area
 * [] Line object
 * 
 * To Add/Implement:
 * [] method that sends the character to a new screen
 * 
 * Temp:
 * [] String name
 * 
 * Spawn calculations:
 * **** Village ****
 * 1. Start (-2399, -736);
 * 2. Exit ([-2160, -350], [-1665, -965]);
 * **** Forest Path ****
 * 1. Gate 1: ([-1665, -965], [-2160, -350]);
 * 2. Gate 2: ([-2625, -965], [-2025, -845]);
 * **** Clearing ****
 * 1. Gate 1: ([-2025, -845], [-2625, -965]);
 * 2. Gate 2: ([-2385, -680], [2190, -1070]);
 * **** Cave ****
 * 1. Gate 1: ([2190, -1070], [-2385, -680]);
 */
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// VERSION 1
/*
public class Gate {
    
    // instance variables
//    private String name;
    public int[] startPoint;
    public int[] endPoint;
    private Line2D.Double gate;
    private ImageIcon[] backgrounds;
    
    public Gate(String start, String end, int[] startSpawnPoint, int[] endSpawnPoint, int[] gatePoints) throws IOException {
//        this.name = name;
        this.startPoint = startSpawnPoint;
        this.endPoint = endSpawnPoint;
        this.backgrounds = new ImageIcon[2];
        this.gate = new Line2D.Double(gatePoints[0] * 32, gatePoints[1] * 32, 
                                      gatePoints[2] * 32, gatePoints[3] * 32); 
        BufferedImage startImage = ImageIO.read(new File(start));
        this.backgrounds[0]= new ImageIcon(startImage);
        BufferedImage endImage = ImageIO.read(new File(end));
        this.backgrounds[1] = new ImageIcon(endImage);
    }
    
    // ------------ Background Image ------------ \\
//    public void loadImages(String start, String end) throws IOException {
//        BufferedImage startImage = ImageIO.read(new File(start));
//        this.backgrounds[0]= new ImageIcon(startImage);
//        BufferedImage endImage = ImageIO.read(new File(end));
//        this.backgrounds[1] = new ImageIcon(endImage);
//    }
    
    public ImageIcon[] sendImages() {
        return this.backgrounds;
    }
    
    public Line2D.Double sendLine() {
        return this.gate;
    }
    
    public int[] sendStart() {
        return this.startPoint;
    }
    
    public int[] sendEnd() {
        return this.endPoint;
    }
    
}
*/

// VERSION 2
/*
public class Gate extends Line2D.Double {
    
    private static final int OFFSET_X = 450;
    private static final int OFFSET_Y = 285;
    
    // instance variables
    public int[] startPoint;
    public int[] endPoint;
    private Place area1;
    private Place area2;
//    private int x1, x2, y1, y2;
    private Line2D.Double gate;
    
    public Gate(Place area1, Place area2, int[] startSpawnPoint, int[] endSpawnPoint, int[] gatePoints) throws IOException {
        this.area1 = area1;
        this.area2 = area2;
        this.startPoint = startSpawnPoint;
        this.endPoint = endSpawnPoint;
        this.gate = new Line2D.Double(gatePoints[0] * 32 - OFFSET_X, 
                                      gatePoints[1] * 32 - OFFSET_Y, 
                                      gatePoints[2] * 32 - OFFSET_X,
                                      gatePoints[3] * 32 - OFFSET_Y);
//        this.x1 = gatePoints[0] * 32;
//        this.y1 = gatePoints[1] * 32;
//        this.x2 = gatePoints[2] * 32;
//        this.y2 = gatePoints[3] * 32;
    }
    
    // ------------ Background Image ------------ \\
//    public void loadImages(String start, String end) throws IOException {
//        BufferedImage startImage = ImageIO.read(new File(start));
//        this.backgrounds[0]= new ImageIcon(startImage);
//        BufferedImage endImage = ImageIO.read(new File(end));
//        this.backgrounds[1] = new ImageIcon(endImage);
//    }
    
    public Line2D.Double sendLine() {
        return this.gate;
    }
//    
//    public int[] sendStart() {
//        return this.startPoint;
//    }
//    
//    public int[] sendEnd() {
//        return this.endPoint;
//    }
    
    // ------------ Swapping areas ------------ \\
    public void toArea1() {
        
    }
    
    public void toArea2() {
        
    }
}
*/

public class Gate extends Line2D.Double {    
    // instance variables
    public int[] startPoint;
    public int[] endPoint;
    private Place area1;
    private Place area2;
    private Place currentArea, nextArea;
    public Line2D.Double line;
    
    public Gate(Place area1, Place area2, int[] startSpawnPoint, int[] endSpawnPoint) throws IOException {
        this.area1 = area1;
        this.area2 = area2;
        this.currentArea = area1;
        this.nextArea = area2;
        this.startPoint = startSpawnPoint;
        this.endPoint = endSpawnPoint;
        this.line = new Line2D.Double();
    }
    
    // ------------ Line ------------ \\
    public void loadLine(int x1, int y1, int x2, int y2) {
        this.line = new Line2D.Double(x1, y1, x2, y2);
    }
//    
//    public int[] sendStart() {
//        return this.startPoint;
//    }
//    
//    public int[] sendEnd() {
//        return this.endPoint;
//    }
    
    // ------------ Swapping areas ------------ \\
    public Place toNextWorld() {
        if (this.currentArea.equals(area1)) {
            this.nextArea = this.area1;
            this.currentArea = this.area2;
        }
        else {
            this.nextArea = this.area2;
            this.currentArea = this.area1;
        }
        this.currentArea.loadSpawnPoint(this.endPoint[0], this.endPoint[1]);
        return this.currentArea;
        
    }
    
    // ------------ toString ------------ \\
    public String toString() {
        return String.format("current area is %s, next area is %s\nx1 = %f, y1 = %f, x2 =  %f, y2 = %f\n", 
                             this.currentArea.getName(), 
                             this.nextArea.getName(), 
                             this.line.getX1(),
                             this.line.getY1(),
                             this.line.getX2(),
                             this.line.getY2());
    }
}


