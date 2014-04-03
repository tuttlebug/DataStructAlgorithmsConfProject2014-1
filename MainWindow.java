/**
 * The Main window for the game;
 * 
 * Check-List:
 * [Ã] set size
 * [Ã] set name
 * [Ã] set closing
 * [Ã] set location
 * [Ã] JPanel for the main world
 * [] Jpanel for the menu
 * 
 * To Add/Implement:
 * [] method that accepts new data and update the window
 * [] move map around with arrow keys
 * [] load an image
 *   - [] make mainWorld extend JPanel; deal with loading image
 * [] load a Place
 * 
 * Temporary:
 * [] shiftWorld with color
 * [] loadBackground
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
    private static JPanel worldPanel = new JPanel();
    private static JPanel menuPanel = new JPanel();
    
    public MainWindow() {
        // standard setup
        setTitle("Game name");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //--------add more code after this line -------//
//        worldPanel.setBackground(Color.BLUE);
        add(worldPanel);
        
        //--------add more code before this line -------//
        
        setLocation(XLCOORD, YLCOORD);
        setVisible(true);
    }
    
    /**
     * VERSION 1
     * changes the screen
     * @param: data to change the screen
     */
    public void shiftWorld(Color bg) {
        worldPanel.setBackground(bg);
    }
    
    /**
     * VERSION 2
     * ""
     * @param: Place
     */
    public void shiftWorld(Place place) {
        worldPanel.setBackground(place.sendDetail());
    }
    
    /**
     * VERSION 1
     * loads the background image
     * @params: String name to the file;
     */
    public void loadBackground(String name) throws IOException {
        BufferedImage image = ImageIO.read(new File(name));
        JLabel label = new JLabel(new ImageIcon(image));
        worldPanel.add(label);
        setVisible(true);
    }
    
    /**
     * VERSION 2
     */    
    public void loadBackground2(String name) throws IOException {
        BackgroundImage bg = new BackgroundImage(name);
        worldPanel.add(bg);
    }
    
    /**
     * VERSION 3
     */
    public void loadBackground3(JLabel label) {
        worldPanel.add(label);
        setVisible(true);
    }
    
    public JLabel createLabel(String name) throws IOException {
        BufferedImage image = ImageIO.read(new File(name));
        JLabel label = new JLabel(new ImageIcon(image));
        return label;
    }
    
    // testing
    public static void main(String[] args) throws IOException {
        // window
        MainWindow mw = new MainWindow();
//        mw.loadBackground("utena2.jpg");
        mw.loadBackground3(mw.createLabel("mtown.png"));
        
        // Place
        Place village = new Place("Village");
        village.constructPlace(Color.GREEN);
//        mw.shiftWorld(village);
        
        
    }
}