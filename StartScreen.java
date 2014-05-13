import javax.swing.*;
import java.awt.*;
import java.awt.event.*;    // ActionListener, ActionEvent
import javax.swing.event.*; // ChangeListener, ChangeEvent
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StartScreen extends JPanel implements LoadImage{
    
    private static final int W_WIDTH = 700;             // width of frame
    private static final int W_HEIGHT = 600;            // height of window
    
    // instance variables
    private JLayeredPane window;
    private JComboBox fontMenu;       // User selects desired font from the pull down menu
    private JCheckBox italicCheckbox; // makes text italicized
    private JCheckBox boldCheckbox;   // makes text bold
    private JSlider sizeSlider;       // adjusts the size of the text
    private JLabel sizeLabel;         // shows the size
    private JLabel textLabel;         // "Here is some sample text"
    private JLabel background;
    private int textSize;
    private ImageIcon bg;
    

    // listener class
    private class FontViewerListener implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent event) {
        }
        
        public void stateChanged(ChangeEvent event) {
        }
    }

    // constructor
    public StartScreen() throws IOException {
        // initialize values
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = env.getAvailableFontFamilyNames();
        // ------------ Window ------------ \\
        this.window = new JLayeredPane();
        add(window);
        window.setBounds(0, 0, W_WIDTH, W_HEIGHT);
        
        this.fontMenu = new JComboBox(fontNames);
        this.italicCheckbox = new JCheckBox("italic");
        this.boldCheckbox = new JCheckBox("bold");
        this.textSize = 40;
        this.sizeSlider = new JSlider(10, 72, this.textSize);
        this.sizeLabel = new JLabel();
        this.textLabel = new JLabel("Here's some sample text");
        this.background = new JLabel();
        JPanel controlPanel = new JPanel();
        JPanel textPanel = new JPanel();
        
        // set background
        this.loadImage();
        
        // add listener to slider, checkboxes, menu
        FontViewerListener listener = new FontViewerListener();
        this.fontMenu.addActionListener(listener);
        this.italicCheckbox.addActionListener(listener);
        this.boldCheckbox.addActionListener(listener);
        this.sizeSlider.addChangeListener(listener);
        
        // add to panels
//        controlPanel.add(this.background);
        controlPanel.add(this.fontMenu);
        controlPanel.add(this.boldCheckbox);
        controlPanel.add(this.italicCheckbox);
        controlPanel.add(this.sizeSlider);
        controlPanel.add(this.sizeLabel); 
        textPanel.add(this.textLabel);
//        updateSizeLabel();
        
        // add panels
        window.setLayout(new BorderLayout());  
        window.add(this.background, BorderLayout.CENTER, new Integer(0));
        window.add(controlPanel, BorderLayout.NORTH, new Integer(1));
        window.add(textPanel, BorderLayout.WEST, new Integer(1));
        
        // configure frame parameters
        this.setSize(W_WIDTH, W_HEIGHT);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); 
    }
    
    public void loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("home.png")); // filler picture
        this.bg = new ImageIcon(image);
        this.background.setIcon(this.bg);
    }
}