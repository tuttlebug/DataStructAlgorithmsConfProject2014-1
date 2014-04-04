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
 * 
 * To Add/Implement:
 * [] method that accepts new data and update the window
 * [] move map around with arrow keys
 * [�] load an image
 *   - [�] switch between images
 * [�] load a Place
 * 
 * Temporary:
 * [�] shiftWorld with color
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;

public class MainWindow extends JFrame {
    
    private static final int W_WIDTH = 700;
    private static final int W_HEIGHT = 600;
    private static final int XLCOORD = 370;
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
        
        //--------add more code before this line -------//
        
        setLocation(XLCOORD, YLCOORD);
        setVisible(true);
    }
    
    /**
     * Loads the background image into the frame
     * @param: An ImageIcon of the background image
     */
//    public void loadBackground(ImageIcon image) {
//        currentArea.setIcon(image);
//    }
    
    /**
     * Creates the ImageIcon of the background
     * @param: name of the file
     * @return: ImageIcon
     */
//    public ImageIcon createBackground(String name) throws IOException {
//        BufferedImage image = ImageIO.read(new File(name));
//        return new ImageIcon(image);
//    }

    /**
     * VERSION 1
     * Changes the screen image to a new area
     * @param: name of the file
     */
//    public void shiftWorld(String name) throws IOException {
//        ImageIcon bg = createBackground(name);
//        loadBackground(bg);
//    }
    
    /**
     * VERSION 2
     */
    public void shiftWorld(ImageIcon image) {
        currentArea.setIcon(image);
    }
    
}