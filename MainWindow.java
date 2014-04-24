/**
 * The Main window for the game;
 * 
 * Check-List:
 * [Ã] set size
 * [Ã] set name
 * [Ã] set closing
 * [Ã] set location
 * [] Jpanel for the menu
 * [] Moveable Image
 * [Ã] Player
 * [Ã] Switch over to Layered Panels
 * 
 * To Add/Implement:
 * [] method that accepts new data and update the window
 * [] move map around with arrow keys
 * [Ã] load an image
 *   - [Ã] switch between images
 * [Ã] load a Place
 * [Ã] JLayeredPanes
 * 
 * Temporary:
 * currentArea.setBounds(x, y, W_WIDTH, W_HEIGHT) in keyPressed
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

public class MainWindow extends JFrame implements KeyListener {
    
    // Moves currentArea with arrow keys
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            x -= 10;
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            x += 10;
        }
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            y += 10;
        }
        if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            y -= 10;
        }
        System.out.printf("x = %d, y = %d\n", x, y);
        caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        testPanel.setBounds(x + RECONCILE_X, y + RECONCILE_Y, CA_WIDTH, CA_HEIGHT);
        currentArea.repaint();
    }
    
    public void keyReleased(KeyEvent event) {
    }
    
    public void keyTyped(KeyEvent event) {
    }
    
    // constants
    private static final int W_WIDTH = 700;
    private static final int W_HEIGHT = 600;
    private static final int CA_WIDTH = 2000;
    private static final int CA_HEIGHT =  2000;
    private static final int XLCOORD = 370; //sets the spawn point of the game window on the computer screen
    private static final int YLCOORD = 100;
    private static final int RECONCILE_X = 200;
    private static final int RECONCILE_Y = 5;
    
    // variables
    private static int x = -649; // -90
    private static int y = -290; // -860
//    private static JPanel menuPanel = new JPanel();
    private static JLayeredPane window = new JLayeredPane();
    private static JPanel caPanel = new JPanel();
    private static JLabel currentArea = new JLabel();
    private static JLabel playerLevel = new JLabel();
    private static BoundaryLines testPanel;
    
    // constructor
    public MainWindow() {
        // standard setup
        setTitle("Game name");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //--------add more code after this line -------//
        add(window);
        window.setBounds(0, 0, W_WIDTH, W_HEIGHT); 
        caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        currentArea.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        caPanel.add(currentArea);
        window.add(caPanel, new Integer(0), 0);
        window.add(playerLevel, new Integer(2), 0);
        
        //To add: JLayeredPanes on top, and pretty much everything else

        
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
        playerLevel.setBounds(W_WIDTH / 2, W_HEIGHT / 2, width, height);
        playerLevel.setIcon(image);
    }
    
    public void addBoundaries(ArrayList<Line2D.Double> lines) {
        testPanel = new BoundaryLines(lines);
        testPanel.setBounds(x + RECONCILE_X, y + RECONCILE_Y, CA_WIDTH, CA_HEIGHT); 
        window.add(testPanel, new Integer(1), 0);
    }
    
}