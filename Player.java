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
 * [] Array of character sprites
 * [] Pack 
 * [Ã] Dimensions
 * [Ã] Should NOT extend JPanel anymore
 * 
 * To Add/Implement:
 * [Ã] Movement
 * [Ã] method that loads character sprite
 *   - [] method that changes Tome's sprites when e moves
 * [] method that allows Tome to go through eir's pack and use items
 * [] method that allows Tome to interact with people and objects
 *   - [] method that allos Tome to pick up items and choose whether to add to eir's pack
 */

/*    
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
*/

// VERSION 1
//public class Player implements LoadImage {
//    
//    // variables
//    private ImageIcon sprite;
//    private String imageFile;
//    private int width, height;
//    
//    public Player() {
//        this.imageFile = "Test Tome left 1.png";
//    }
//    // ------------ Image ------------ \\
//    public void loadImage() throws IOException {
//        BufferedImage image = ImageIO.read(new File(this.imageFile));
//        this.sprite = new ImageIcon(image);
//        this.width = this.sprite.getIconWidth();
//        this.height = this.sprite.getIconHeight();
//    }
//    
//    public ImageIcon sendImage() {
//        return this.sprite;
//    }
//        
//    // ------------ Height&Width ------------ \\
//    public int getHeight() {
//        return this.height;
//    }
//    
//    public int getWidth() {
//        return this.width;
//    }
//    
//    
//    // ------------ Pack ------------ \\
//    /*
//    public Pack openPack() {
//      return this.Pack();
//    }
//    
//    public class Pack() {
//    
//      //instance variables?
//      private int size;
//      Map<String, Carriable> items;
//      
//      //constructor
//      public Pack(Player) {
//        //what data type should Pack be? Or what data types should it contain?
//        this.items = new HashMap<String, Carriable>();
//      }
//      
//      public HashMap getMap() { //What is the correct return type?
//        return this.items; 
//      }
//      
//      public void addItem(Carriable item) { //or have it return a boolean?
//        //add to ...whatever data type the Pack is going to be. Map?
//        this.size++;
//      }
//      
//      public void removeItem(Carriable item) { //or return a boolean? 
//        //remove from...whatever data type the Pack is going to be. Map?
//        this.size--; 
//      }
//      
//    }
//    */        
//}

public class Player {
    
    // variables
    public ImageIcon sprite;
    public ImageIcon[] sprites;
    private String[] filenames;
    private int width, height;
    private int health;
    
    public Player() throws IOException {
        // ------------ Sprites ------------ \\
        this.sprites = new ImageIcon[] {
            // left
            new ImageIcon(ImageIO.read(new File("Tome left stand.png"))), // 0
            new ImageIcon(ImageIO.read(new File("Tome left 1.png"))),     // 1
            new ImageIcon(ImageIO.read(new File("Tome left 2.png"))),     // 2
            // right    
            new ImageIcon(ImageIO.read(new File("Tome right stand.png"))),// 3   
            new ImageIcon(ImageIO.read(new File("Tome right 1.png"))),    // 4
            new ImageIcon(ImageIO.read(new File("Tome right 2.png"))),    // 5
            // down
            new ImageIcon(ImageIO.read(new File("Tome down stand.png"))), // 6
            new ImageIcon(ImageIO.read(new File("Tome down 1.png"))),     // 7
            new ImageIcon(ImageIO.read(new File("Tome down 2.png"))),     // 8
            // up
            new ImageIcon(ImageIO.read(new File("Tome up stand.png"))),   // 9
            new ImageIcon(ImageIO.read(new File("Tome up 1.png"))),       // 10
            new ImageIcon(ImageIO.read(new File("Tome up 2.png"))),       // 11
            // attack
            new ImageIcon(ImageIO.read(new File("Tome atk down.png"))),   // 12
            new ImageIcon(ImageIO.read(new File("Tome atk left.png"))),   // 13
            new ImageIcon(ImageIO.read(new File("Tome atk right.png"))),  // 14
            new ImageIcon(ImageIO.read(new File("Tome atk up.png")))      // 15
        };
        
        this.sprite = sprites[6];
        this.width = this.sprite.getIconWidth();
        this.height = this.sprite.getIconHeight();
        this.health = 20;
    }
    // ------------ Image ------------ \\
    public ImageIcon sendImage() {
        return this.sprite;
    }
    
    // ------------ Height&Width ------------ \\
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    // ------------ Health ------------ \\
    public void loseHealth(int damage) {
        this.health -= damage;
    }
    
    public void regainHealth(int restore) {
        this.health += restore;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    // ------------ Pack ------------ \\
    /*
    public Pack openPack() {
      return this.Pack();
    }
    
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
}

