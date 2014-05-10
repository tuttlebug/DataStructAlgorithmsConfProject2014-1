import javax.swing.*;
import java.awt.*;
import java.awt.event.*;    // ActionListener, ActionEvent
import javax.swing.event.*; // ChangeListener, ChangeEvent

public class StartScreen extends JPanel {
    // instance variables
    private JComboBox fontMenu;       // User selects desired font from the pull down menu
    private JCheckBox italicCheckbox; // makes text italicized
    private JCheckBox boldCheckbox;   // makes text bold
    private JSlider sizeSlider;       // adjusts the size of the text
    private JLabel sizeLabel;         // shows the size
    private JLabel textLabel;         // "Here is some sample text"
    private int textSize;

    // listener class
    private class FontViewerListener implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent event) {
//            updateFont(); // menu, checkboxes
        }
        
        public void stateChanged(ChangeEvent event) {
//            updateSizeLabel(); // slider bar
        }
    }

    // constructor
    public StartScreen() {
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
//        updateSizeLabel();
        
        // add panels
        this.setLayout(new BorderLayout());  
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(textPanel, BorderLayout.CENTER);
        
        // configure frame parameters
//        this.setSize(750, 170);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true); 
    }
}