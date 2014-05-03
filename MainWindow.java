/**
 * The Main window for the game;
 * 
 * Check-List:
 * [Ã] set size
 * [Ã] set name
 * [Ã] set closing
 * [Ã] set location
 * [] Jpanel for the menu
 * [Ã] Moveable Image
 * [Ã] Player
 * [Ã] Switch over to Layered Panels
 * 
 * To Add/Implement:
 * [] method that handles switching from place to another place
 *   - [] changing the image
 *   - [] 
 * [] move the images like we move the lines
 *   - using paint
 * 
 * Temporary:
 * 
 * METHODS TO THINK ABOUT:
 * [] JLayeredPane.paint(Graphics g)
 * 
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

// VERSION 1

public class MainWindow extends JFrame implements KeyListener {
    
    // Moves currentArea with arrow keys
    public void keyPressed(KeyEvent event) {
        // going left
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (boundaryPanel.moveR == true) {
                x -= MOVE;
                boundaryPanel.moveLines(-MOVE, 0);
                caPanel.setLocation(x, y);
                // if this movement caused a collision:
                // the movement flag becomes false.
                // tome can no longer go in this direction 
                // and will not be able to until the movement flag turns true again.
                // i.e. tome moves away from the boundary
                if (boundaryPanel.crossed(box.getBox()) == true) {
                    boundaryPanel.moveR = false;
                }
            }
        }
        // going right
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            if (boundaryPanel.moveL == true) {
                x += MOVE;
                boundaryPanel.moveLines(MOVE, 0);
                caPanel.setLocation(x, y);
                if (boundaryPanel.crossed(box.getBox()) == true) {
                    boundaryPanel.moveL = false;
                }
            }
        }
        // going down
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            if (boundaryPanel.moveU == true) {
                y += MOVE;
                boundaryPanel.moveLines(0, MOVE);
                caPanel.setLocation(x, y);
                if (boundaryPanel.crossed(box.getBox()) == true) {
                    boundaryPanel.moveU = false;
                }
            }
        }
        // going right
        if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
            if (boundaryPanel.moveD == true) {
                y -= MOVE;
                boundaryPanel.moveLines(0, -MOVE);
                caPanel.setLocation(x, y);
                if (boundaryPanel.crossed(box.getBox()) == true) {
                    boundaryPanel.moveD = false;
                }
            }
        }
        // this allows tome to move away from the boundary
        if (boundaryPanel.crossed(box.getBox()) == false) {
            boundaryPanel.moveL = true;
            boundaryPanel.moveR = true; 
            boundaryPanel.moveU = true; 
            boundaryPanel.moveD = true;
        }
        System.out.printf("x = %d, y = %d\n", x, y);
//        System.out.printf("BoundaryLabel Location: x = %d, y = %d, width = %d, height = %d, xalign = %f, y align = %f\nbounds = %s\n", 
//                          boundaryPanel.getX(),
//                          boundaryPanel.getY(),
//                          boundaryPanel.getWidth(),
//                          boundaryPanel.getHeight(),
//                          boundaryPanel.getAlignmentX(),
//                          boundaryPanel.getAlignmentY(),
//                          boundaryPanel.getBounds().toString());
//        System.out.printf("caPanel Location: x = %d, y = %d, width = %d, height = %d, xalign = %f, y align = %f\nbounds = %s\n", 
//                          caPanel.getX(),
//                          caPanel.getY(),
//                          caPanel.getWidth(),
//                          caPanel.getHeight(),
//                          caPanel.getAlignmentX(),
//                          caPanel.getAlignmentY(),
//                          caPanel.getBounds().toString());
//        System.out.printf("window Location: x = %d, y = %d, width = %d, height = %d, xalign = %f, y align = %f\nbounds = %s\n\n", 
//                          window.getX(),
//                          window.getY(),
//                          window.getWidth(),
//                          window.getHeight(),
//                          window.getAlignmentX(),
//                          window.getAlignmentY(),
//                          window.getBounds().toString());
    }
    
    public void keyReleased(KeyEvent event) {
    }
    
    public void keyTyped(KeyEvent event) {
    }
    
    // constants
    private static final int W_WIDTH = 700;             // width of frame
    private static final int W_HEIGHT = 600;            // height of window
    private static final int CA_WIDTH = 5000;           // 
    private static final int CA_HEIGHT = 5000;
    private static final int XLCOORD = 370;             // sets the spawn point of the game window on the computer screen
    private static final int YLCOORD = 100;             // sets the spawn point of the game window on the computer screen
    private static final int PLAYER_X = W_WIDTH / 2;
    private static final int PLAYER_Y = W_HEIGHT / 2;
    private static final int OFFSET_X = 450;
    private static final int OFFSET_Y = 285;
    private static final int MOVE = 15;                 // movement speed
    public static final int X = -2150;
    public static final int Y = -290;
    
    // variables
    private static int x = -1830; // -90, -649, -1830
    private static int y = -290; // -860, -290  -290
    private static JLayeredPane window = new JLayeredPane();
    private static JPanel caPanel = new JPanel();
    private static JLabel currentArea = new JLabel();
    private static JLabel playerLevel = new JLabel();
    private static BoundaryLines boundaryPanel;
    private static PlayerBox box;
    
    // constructor
    public MainWindow() {
        // standard setup
        setTitle("Game name");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //--------add more code after this line -------//
        add(window);
        window.setBounds(x, y, W_WIDTH, W_HEIGHT); 
        
        caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        currentArea.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        caPanel.add(currentArea);
        window.add(caPanel, new Integer(0), 0);
        window.add(playerLevel, new Integer(3), 0);
        
        //--------add more code before this line -------//
        
        this.addKeyListener(this);
        this.setFocusable(true);
        setLocation(XLCOORD, YLCOORD);
        setVisible(true);
    }
    
    public void shiftWorld(ImageIcon image) {
        currentArea.setIcon(image);
    }

    public void addPlayer(ImageIcon image, int width, int height) { 
        playerLevel.setBounds(PLAYER_X, PLAYER_Y, width, height);
        playerLevel.setIcon(image);
    }
    
    public void addPlayerBox(int width, int height) {
        box = new PlayerBox(PLAYER_X, PLAYER_Y, width, height);
        box.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        window.add(box, new Integer(1), 0);
    }
    
    public void addBoundaries(int[][] points, int[] spawnPoint) {
        boundaryPanel = new BoundaryLines(points, OFFSET_X, OFFSET_Y);
        System.out.printf("X = %d, Y = %d\n", X - spawnPoint[0], Y - spawnPoint[1]);
        boundaryPanel.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        int oldX = x; // -1830, -290
        int oldY = y;
        x = spawnPoint[0]; // 1376, 1056
        y = spawnPoint[1]; // These calculations may need to be passed through
        boundaryPanel.setLocation(x, y); // x = -2399 - (-1860) = -4229, y = -736 - (-290) = -446, 
        caPanel.setLocation(x, y);
//        boundaryPanel.setAlignmentX(0);
        boundaryPanel.moveLines(x - oldX, y - oldY);
        window.add(boundaryPanel, new Integer(1), 0);
//        System.out.printf("BoundaryLabel Location: x = %d, y = %d, width = %d, height = %d\n", 
//                          boundaryPanel.getX(),
//                          boundaryPanel.getY(),
//                          boundaryPanel.getWidth(),
//                          boundaryPanel.getHeight());
//        System.out.printf("caPanel Location: x = %d, y = %d, width = %d, height = %d\n", 
//                          caPanel.getX(),
//                          caPanel.getY(),
//                          caPanel.getWidth(),
//                          caPanel.getHeight());
    }
    
    // Handles all changes
     
    // changes the background image;
    // changes the boundaries;
    // changes tome's start positon;
    public void swapWorlds() {
        // shiftWorld(ImageIcon image)
        // addBoundaries(int[][] points)
    }  
}


//public class MainWindow extends JFrame implements KeyListener {
//    
//    // Moves currentArea with arrow keys
//    public void keyPressed(KeyEvent event) {
//        // going left
//        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
//            if (boundaryPanel.moveR == true) {
//                x -= MOVE;
//                boundaryPanel.moveLines(-MOVE, 0);
//                caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
//                // if this movement caused a collision:
//                // the movement flag becomes false.
//                // tome can no longer go in this direction 
//                // and will not be able to until the movement flag turns true again.
//                // i.e. tome moves away from the boundary
//                if (boundaryPanel.crossed(box.getBox()) == true) {
//                    boundaryPanel.moveR = false;
//                }
//            }
//        }
//        // going right
//        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
//            if (boundaryPanel.moveL == true) {
//                x += MOVE;
//                boundaryPanel.moveLines(MOVE, 0);
//                caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
//                if (boundaryPanel.crossed(box.getBox()) == true) {
//                    boundaryPanel.moveL = false;
//                }
//            }
//        }
//        // going down
//        if (event.getKeyCode() == KeyEvent.VK_UP) {
//            if (boundaryPanel.moveU == true) {
//                y += MOVE;
//                boundaryPanel.moveLines(0, MOVE);
//                caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
//                if (boundaryPanel.crossed(box.getBox()) == true) {
//                    boundaryPanel.moveU = false;
//                }
//            }
//        }
//        // going right
//        if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
//            if (boundaryPanel.moveD == true) {
//                y -= MOVE;
//                boundaryPanel.moveLines(0, -MOVE);
//                caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
//                if (boundaryPanel.crossed(box.getBox()) == true) {
//                    boundaryPanel.moveD = false;
//                }
//            }
//        }
//        // this allows tome to move away from the boundary
//        if (boundaryPanel.crossed(box.getBox()) == false) {
//            boundaryPanel.moveL = true;
//            boundaryPanel.moveR = true; 
//            boundaryPanel.moveU = true; 
//            boundaryPanel.moveD = true;
//        }
//        currentArea.repaint();
//        System.out.printf("x = %d, y = %d\n", x, y);
//    }
//    
//    public void keyReleased(KeyEvent event) {
//    }
//    
//    public void keyTyped(KeyEvent event) {
//    }
//    
//    // constants
//    private static final int W_WIDTH = 700;
//    private static final int W_HEIGHT = 600;
//    private static final int CA_WIDTH = 2000;
//    private static final int CA_HEIGHT = 2000;
//    private static final int XLCOORD = 370; //sets the spawn point of the game window on the computer screen
//    private static final int YLCOORD = 100;
//    private static final int RECONCILE_X = 200;
//    private static final int RECONCILE_Y = 5;
//    private static final int PLAYER_X = W_WIDTH / 2;
//    private static final int PLAYER_Y = W_HEIGHT / 2;
//    private static final int OFFSET_X = 450;
//    private static final int OFFSET_Y = 285;
//    private static final int MOVE = 15;
//    
//    // variables
//    private static int x = -649; // -90, -649
//    private static int y = -290; // -860, -290
////    private static JPanel menuPanel = new JPanel();
//    private static JLayeredPane window = new JLayeredPane();
//    private static Place caPanel = new Place();
//    private static JLabel currentArea = new JLabel();
//    private static JLabel playerLevel = new JLabel();
//    private static BoundaryLines boundaryPanel;
//    private static PlayerBox box;
//    
//    // constructor
//    public MainWindow() {
//        // standard setup
//        setTitle("Game name");
//        setSize(W_WIDTH, W_HEIGHT);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//        //--------add more code after this line -------//
//        add(window);
//        window.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
//        
//        caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
//        currentArea.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
//        caPanel.add(currentArea);
//        window.add(caPanel, new Integer(0), 0);
//        window.add(playerLevel, new Integer(3), 0);
//        
//        //--------add more code before this line -------//
//        
//        this.addKeyListener(this);
//        this.setFocusable(true);
//        setLocation(XLCOORD, YLCOORD);
//        setVisible(true);
//    }
//    
//    public void shiftWorld(ImageIcon image) {
//        currentArea.setIcon(image);
//    }
//
//    public void addPlayer(ImageIcon image, int width, int height, int[] spawnPoint) { 
//        System.out.printf("spawn X = %d, spawn Y = %d\n", spawnPoint[0], spawnPoint[1]);
//        playerLevel.setBounds(spawnPoint[0], spawnPoint[1], width, height);
////        playerLevel.setBounds(PLAYER_X, PLAYER_Y, width, height);
//        playerLevel.setIcon(image);
//        addPlayerBox(width, height, spawnPoint);
//    }
//    
//    public void addPlayerBox(int width, int height, int[] spawnPoint) {
//        box = new PlayerBox(spawnPoint[0], spawnPoint[1], width, height);
////        box = new PlayerBox(PLAYER_X, PLAYER_Y, width, height);
//        box.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
//        window.add(box, new Integer(1), 0);
//    }
//    
//    public void addBoundaries(int[][] points) {
//        boundaryPanel = new BoundaryLines(points, OFFSET_X, OFFSET_Y);
//        boundaryPanel.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
//        window.add(boundaryPanel, new Integer(1), 0);
//    }
//    
//    /*
//     * Handles all changes
//     * 
//     * changes the background image;
//     * changes the boundaries;
//     * changes tome's start positon;
//     */
//    public void swapWorlds() {
//        // shiftWorld(ImageIcon image)
//        // addBoundaries(int[][] points)
//    }
//    
//}