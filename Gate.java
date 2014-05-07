/*
 * A set of points in areas that mark where a character can enter into a different screen;
 * 
 * Check List:
 * [] A Map of areas that the gate connects to
 * [] XY coordinates in the area
 * [] Line object
 * 
 * To Add/Implement:
 * [Ã] method that sends the character to a new screen
 * [] Only holds one destinaion point; not start point
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
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Gate extends Line2D.Double {    
    // instance variables
    public int[] endPoint;
    private Place nextArea;
    public Line2D.Double line;
    
    public Gate(Place nextArea, int[] endSpawnPoint) throws IOException {
        this.nextArea = nextArea;
        this.endPoint = endSpawnPoint;
        this.line = new Line2D.Double();
    }
    
    // ------------ Line ------------ \\
    public void loadLine(double x1, double y1, double x2, double y2) {
        this.line.setLine(x1, y1, x2, y2);
    }
    
    // ------------ Swapping areas ------------ \\
    public Place toNextWorld() {
        this.nextArea.loadSpawnPoint(this.endPoint[0] + 5, this.endPoint[1]);
        return this.nextArea;
        
    }
    
    // ------------ toString ------------ \\
    public String toString() {
        return String.format("next area is %s\nx1 = %f, y1 = %f, x2 =  %f, y2 = %f\nendpoint = %s\n", 
                             this.nextArea.getName(), 
                             this.line.getX1(),
                             this.line.getY1(),
                             this.line.getX2(),
                             this.line.getY2(),
                             Arrays.toString(this.endPoint));
    }
}


