/**
 * The Main window for the game;
 * 
 * Check-List:
 * [�] set size
 * [�] set name
 * [�] set closing
 * [�] set location
 * [] Jpanel for the menu
 * [] Moveable Image
 * [] Player
 * [] Switch over to Layered Panels
 * 
 * To Add/Implement:
 * [] method that accepts new data and update the window
 * [] move map around with arrow keys
 * [�] load an image
 *   - [�] switch between images
 * [�] load a Place
 * [] JLayeredPanes
 * 
 * Temporary:
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainWindow extends JFrame {
    
    private static final int W_WIDTH = 700;
    private static final int W_HEIGHT = 600;
    private static final int XLCOORD = 370; //sets the spawn point of the game window on the computer screen
    private static final int YLCOORD = 100;
    
    // variables
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
        
        //--------add more code after this line -------//
//        add(currentArea);  
        add(window);
        window.setBounds(0, 0, W_WIDTH, W_HEIGHT); 
        currentArea.setBounds(0, 0, W_WIDTH, W_HEIGHT); 
        window.add(currentArea, new Integer(0), 0);
        
//        add(player);
        //To add: JLayeredPanes on top, and pretty much everything else

        
        //--------add more code before this line -------//
        
        setLocation(XLCOORD, YLCOORD);
        setVisible(true);
    }
    
    public void shiftWorld(ImageIcon image) {
        currentArea.setIcon(image);
    }
    
    public void addPlayer(ImageIcon player) {
        window.add(playerLevel, new Integer(1), 0);
        playerLevel.setIcon(player);
    }
    
}