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
 * [] Player
 * [] Switch over to Layered Panels
 * 
 * To Add/Implement:
 * [] method that accepts new data and update the window
 * [] move map around with arrow keys
 * [Ã] load an image
 *   - [Ã] switch between images
 * [Ã] load a Place
 * 
 * Temporary:
 * [Ã] shiftWorld with color
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
    private static JPanel menuPanel = new JPanel();
    private static JLabel currentArea = new JLabel();
    
    // constructor
    public MainWindow() {
        // standard setup
        setTitle("Game name");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //--------add more code after this line -------//
        add(currentArea);
<<<<<<< HEAD
//        add(player);
        //To add: JLayeredPanes on top, and pretty much everything else
=======
>>>>>>> FETCH_HEAD
        
        //--------add more code before this line -------//
        
        setLocation(XLCOORD, YLCOORD);
        setVisible(true);
    }
    
    public void shiftWorld(ImageIcon image) {
        currentArea.setIcon(image);
    }
    
}