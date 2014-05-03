import java.util.* ;
import java.io.*;
import javax.swing.*;    // JPanel
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent

/**
 * TO ADD/IMPLEMENT:
 * [] change into a sublclass
 */
public class PlayerBox extends JPanel {
    
    private Rectangle2D.Double box;
    
    public PlayerBox(int x1, int y1, int x2, int y2) {
        this.box = new Rectangle2D.Double(x1, y1, x2, y2);
        // makes background and lines invisible
        setOpaque(false);
//        setVisible(false);
    }
    
    // draws a red circle at the current x, y coordinate
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.BLUE);
        pen.draw(this.box);
        // makes background transparent
        g.setColor(getBackground());
    }
    
    public Rectangle2D.Double getBox() {
        return this.box;
    }
}