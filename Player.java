import java.util.*;
import javax.swing.*;
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D

public class Player extends JPanel {
    
    /**
     */
    
    /**
     * moves the character around
     */
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
    int x, y;
    int radius; // Temporary
    
    public Player() {
        this.x = 200;
        this.y = 200;
        this.radius = 5;
        this.addKeyListener(new ListenerForKeys());
        this.setFocusable(true);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        int x1 = this.x - this.radius;
        int y1 = this.y - this.radius;
        int diameter = this.radius * 2;
        Ellipse2D.Double player = new Ellipse2D.Double(x1, y1, diameter, diameter);
        pen.fill(player);
    }
}