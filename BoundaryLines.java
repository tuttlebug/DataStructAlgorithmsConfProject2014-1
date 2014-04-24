import java.util.* ;
import java.io.*;
import javax.swing.*;    // JPanel
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent

public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    
    public BoundaryLines(ArrayList<Line2D.Double> lines) {
        this.lines = lines;
        setOpaque(false);
    }
    
    // draws a red circle at the current x, y coordinate
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.RED);
        
        for ( Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        // makes background transparent
        g.setColor(getBackground());
    }
}

/*
 public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    private BufferedImage image;
    
    public BoundaryLines(ArrayList<Line2D.Double> lines, BufferedImage image) {
        this.lines = lines;
        this.image = image;
    }
    
    // draws a red circle at the current x, y coordinate
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D pen = (Graphics2D) g;
        Graphics2D pen = this.image.createGraphics();
        pen.setColor(Color.RED);
        
        for ( Line2D.Double line : this.lines) {
            pen.draw(line);
        }
    }
}
*/