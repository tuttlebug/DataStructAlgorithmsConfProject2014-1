import javax.swing.*;
import java.awt.*;
import java.awt.event.*;    // ActionListener, ActionEvent
import javax.swing.event.*; // ChangeListener, ChangeEvent
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.*;

public class StartScreen extends JPanel {
    
    private static final int W_WIDTH = 700;             // width of frame
    private static final int W_HEIGHT = 600;            // height of window
    
    // instance variables
//    private JLayeredPane window;
    private JComboBox fontMenu;       // User selects desired font from the pull down menu
    private JCheckBox italicCheckbox; // makes text italicized
    private JCheckBox boldCheckbox;   // makes text bold
    private JSlider sizeSlider;       // adjusts the size of the text
    private JLabel sizeLabel;         // shows the size
    private JLabel textLabel;         // "Here is some sample text"
    private int textSize;
    private Image bg;
    

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
        
        this.fontMenu = new JComboBox(fontNames);
        this.italicCheckbox = new JCheckBox("italic");
        this.boldCheckbox = new JCheckBox("bold");
        this.textSize = 40;
        this.sizeSlider = new JSlider(10, 72, this.textSize);
        this.sizeLabel = new JLabel();
        this.textLabel = new JLabel("Here's some sample text");
        JPanel controlPanel = new JPanel();
        JPanel textPanel = new JPanel();
        
        // set background
        this.bg = ImageIO.read(new File("home.png")); // filler picture
        
        // add listener to slider, checkboxes, menu
        FontViewerListener listener = new FontViewerListener();
        this.fontMenu.addActionListener(listener);
        this.italicCheckbox.addActionListener(listener);
        this.boldCheckbox.addActionListener(listener);
        this.sizeSlider.addChangeListener(listener);
        
        // add to panels
        controlPanel.add(this.fontMenu);
        controlPanel.add(this.boldCheckbox);
        controlPanel.add(this.italicCheckbox);
        controlPanel.add(this.sizeSlider);
        controlPanel.add(this.sizeLabel); 
        textPanel.add(this.textLabel);
        
//        controlPanel.setBounds(0, 0, W_WIDTH / 4, W_HEIGHT / 4);
//        controlPanel.setOpaque(false);
//        textPanel.setBounds(0, 0, W_WIDTH / 4, W_HEIGHT / 4);
//        textPanel.setOpaque(false);
        
        // add panels
//        window.setLayout(new BorderLayout());  
//        window.add(this.background, new Integer(0), 0);
//        window.add(controlPanel, BorderLayout.NORTH, new Integer(1));
//        window.add(textPanel, BorderLayout.WEST, new Integer(1));
        
        // configure frame parameters
        this.setSize(W_WIDTH, W_HEIGHT);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); 
    }
    
    public void paintComponent(Graphics g) { 
        // ------------ Pen ------------ \\
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        // ------------ Rectangle ------------ \\
        Rectangle2D.Double rect = new Rectangle2D.Double(W_WIDTH / 2, W_HEIGHT / 2, 50, 50); 
        // ------------ Draw ------------ \\
        pen.drawImage(this.bg, 0, 0, this);
        pen.setColor(Color.RED);
        pen.draw(rect);
        pen.setColor(Color.BLUE);
        pen.drawString("Press M to play", W_WIDTH / 2, W_HEIGHT / 2);
    }

}