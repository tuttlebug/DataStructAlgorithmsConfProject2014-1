import java.util.*;
import javax.swing.*;
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D

/**
 * TO DO:
 * Work on loading image;
 */

public class Player extends JPanel {
   
    // moves the character around
    private class ListenerForKeys implements KeyListener {
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                x += 10;
            }
            if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                x -= 10;
            }
            if (event.getKeyCode() == KeyEvent.VK_UP) {
                y -= 10;
            }
            if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                y += 10;
            }
            repaint();
        }
        public void keyReleased(KeyEvent event) {
        }
        public void keyTyped(KeyEvent event) {
        }
    }
    
    // instance variables
    private int x, y;
    private int radius; // Temporary
    public Place currentArea;
//  private Pack pack; 
    
    public Player() {
        this.x = 200;
        this.y = 200;
        this.radius = 5;
        this.currentArea = null;
        this.addKeyListener(new ListenerForKeys());
        this.setFocusable(true);
    }
    
    public String toString() {
        return String.format("CURRENTLY IN: %s\nITEMS ON HAND: (Nothing yet)\n", this.currentArea.getName());
    }
    
    public void currentlyIn (Place place) {
        this.currentArea = place;
    }
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        int x1 = this.x - this.radius;
        int y1 = this.y - this.radius;
        int diameter = this.radius * 2;
        
//        ImageIcon test = new ImageIcon("Test Tome left 1.png");
//        test.paintIcon(this, g, 100, 100);
        Ellipse2D.Double player = new Ellipse2D.Double(x1, y1, diameter, diameter);
        pen.fill(player);
    }
}
