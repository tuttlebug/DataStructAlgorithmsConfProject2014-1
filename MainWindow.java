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
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainWindow extends JFrame implements KeyListener {
    
    // Moves currentArea with arrow keys
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
        System.out.printf("x = %d, y = %d\n", x, y);
        currentArea.setBounds(x, y, W_WIDTH, W_HEIGHT); 
        currentArea.repaint();
    }
    
    public void keyReleased(KeyEvent event) {
    }
    
    public void keyTyped(KeyEvent event) {
    }
    
    // constants
    private static final int W_WIDTH = 700;
    private static final int W_HEIGHT = 600;
    private static final int XLCOORD = 370; //sets the spawn point of the game window on the computer screen
    private static final int YLCOORD = 100;
    
    // variables
    private static int x = 0;
    private static int y = 0;
//    private static JPanel menuPanel = new JPanel();
    private static JLayeredPane window = new JLayeredPane();
    private static JLabel currentArea = new JLabel();
    private static JLabel playerLevel = new JLabel();
    
    // constructor
    public MainWindow() {
        // standard setup
        setTitle("Game name");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setFocusable(true);
        
        //--------add more code after this line -------//
        add(window);
        window.setBounds(0, 0, W_WIDTH, W_HEIGHT); 
        currentArea.setBounds(x, y, W_WIDTH, W_HEIGHT); 
        window.add(currentArea, new Integer(0), 0);
        window.add(playerLevel, new Integer(1), 0);
        
        //To add: JLayeredPanes on top, and pretty much everything else

        
        //--------add more code before this line -------//
        
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
    
}