import java.util.*;
import javax.swing.*;
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * The Player Character;
 * 
 * Check List:
 * [Ã] Character sprite 
 * [] Pack 
 * 
 * To Add/Implement:
 * [Ã] Movement
 * [Ã] method that loads character sprite
 *   - [] method that changes Tome's sprites when e moves
 * [] method that allows Tome to go through eir's pack and use items
 * [] method that allows Tome to interact with people and objects
 *   - [] method that allos Tome to pick up items and choose whether to add to eir's pack
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
    private BufferedImage player;
    private String currentImage;
//  private Pack pack; 
    
    public Player() {
        this.x = 200;
        this.y = 200;
        this.radius = 5;
        this.currentArea = null;
        this.addKeyListener(new ListenerForKeys());
        this.setFocusable(true);
        this.currentImage = "Test Tome left 1.png";
        
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
        pen.drawImage(player, x1, y1, null);
        this.loadImage();
    }
    
    public void loadImage() {
        try {
           player = ImageIO.read(new File(this.currentImage));
       } catch (IOException e) {
       }
    }
}
    /*
<<<<<<< HEAD
    /*
    public Pack openPack() {
      return this.Pack();
    }
    
    
    /*
    public class Pack() {
    
      //instance variables?
      private int size;
      Map<String, Carriable> items;
      
      //constructor
      public Pack(Player) {
        //what data type should Pack be? Or what data types should it contain?
        this.items = new HashMap<String, Carriable>();
      }
      
      public HashMap getMap() { //What is the correct return type?
        return this.items; 
      }
      
      public void addItem(Carriable item) { //or have it return a boolean?
        //add to ...whatever data type the Pack is going to be. Map?
        this.size++;
      }
      
      public void removeItem(Carriable item) { //or return a boolean? 
        //remove from...whatever data type the Pack is going to be. Map?
        this.size--; 
      }
      
    }
    */
    /*
}
=======
}
>>>>>>> FETCH_HEAD
*/