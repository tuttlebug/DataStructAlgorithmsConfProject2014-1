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
 * [] make tome abstract
 * 
 * Temporary:
 * 
 * METHODS TO THINK ABOUT:
 * [] JLayeredPane.paint(Graphics g)
 * [] player as variable
 * 
 * THINGS TO CONSIDER:
 * Graphs
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
import java.util.*;

/*
public class MainWindow extends JFrame implements KeyListener {
    
    // Moves currentArea with arrow keys
    public void keyPressed(KeyEvent event) {
        // ------------ Movement ------------ \\
        // going left
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (boundaryPanel.moveR == true) {
                x -= MOVE;
                boundaryPanel.moveLines(-MOVE, 0);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[4])) shiftPlayerSprite(tome.sprites[4]);
                else shiftPlayerSprite(tome.sprites[5]);  
                // ------------ Boundary Collision ------------ \\
                // if this movement caused a collision:
                // the movement flag becomes false.
                // tome can no longer go in this direction 
                // and will not be able to until the movement flag turns true again.
                // i.e. tome moves away from the boundary
                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
                    boundaryPanel.moveR = false;
                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // going right
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            if (boundaryPanel.moveL == true) {
                x += MOVE;
                boundaryPanel.moveLines(MOVE, 0);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[1])) shiftPlayerSprite(tome.sprites[1]);
                else shiftPlayerSprite(tome.sprites[2]);
                // ------------ Boundary Collision ------------ \\
                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
                    boundaryPanel.moveL = false;
                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // going down
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            if (boundaryPanel.moveU == true) {
                y += MOVE;
                boundaryPanel.moveLines(0, MOVE);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[10])) shiftPlayerSprite(tome.sprites[10]);
                else shiftPlayerSprite(tome.sprites[11]);
                // ------------ Boundary Collision ------------ \\
                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
                    boundaryPanel.moveU = false;
                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // going up
        if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
            if (boundaryPanel.moveD == true) {
                y -= MOVE;
                boundaryPanel.moveLines(0, -MOVE);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[7])) shiftPlayerSprite(tome.sprites[7]);
                else shiftPlayerSprite(tome.sprites[8]);
                // ------------ Boundary Collision ------------ \\
                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
                    boundaryPanel.moveD = false;
                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // ------------ Attacking ------------ \\
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            // attack left
            if (tome.sprite.equals(tome.sprites[4]) || tome.sprite.equals(tome.sprites[5]) || tome.sprite.equals(tome.sprites[3])) {
                shiftPlayerSprite(tome.sprites[14]);
            }
            // attack right
            if (tome.sprite.equals(tome.sprites[1]) || tome.sprite.equals(tome.sprites[2]) || tome.sprite.equals(tome.sprites[0])) {
                shiftPlayerSprite(tome.sprites[13]);
            }
            // attack up
            if (tome.sprite.equals(tome.sprites[10]) || tome.sprite.equals(tome.sprites[11]) || tome.sprite.equals(tome.sprites[9])) {
                shiftPlayerSprite(tome.sprites[15]);
            }
            // attack down
            if (tome.sprite.equals(tome.sprites[7]) || tome.sprite.equals(tome.sprites[8]) || tome.sprite.equals(tome.sprites[6])) {
                shiftPlayerSprite(tome.sprites[12]);
            }
                
        }
        // this allows tome to move away from the boundary
        if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == false) {
            boundaryPanel.moveL = true;
            boundaryPanel.moveR = true; 
            boundaryPanel.moveU = true; 
            boundaryPanel.moveD = true;
        }
//        System.out.printf("x = %d, y = %d\n", x, y);
    }
    
    public void keyReleased(KeyEvent event) {
        // left
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) { 
            shiftPlayerSprite(tome.sprites[3]);
        }
        // right
        if (event.getKeyCode() == KeyEvent.VK_LEFT) { 
            shiftPlayerSprite(tome.sprites[0]);
        }
        // down
        if (event.getKeyCode() == KeyEvent.VK_UP) { 
            shiftPlayerSprite(tome.sprites[9]);
        }
        // up
        if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
            shiftPlayerSprite(tome.sprites[6]);
        }
    }
    
    public void keyTyped(KeyEvent event) {
    }
    
    // ------------------------------------------------------------------------------------------ \\
    // ------------------------------------------------------------------------------------------ \\
    // ---------------------------------------- Main Program ------------------------------------ \\
    // ------------------------------------------------------------------------------------------ \\
    // ------------------------------------------------------------------------------------------ \\
    // constants
    private static final int W_WIDTH = 700;             // width of frame
    private static final int W_HEIGHT = 600;            // height of window
    private static final int CA_WIDTH = 5000;            
    private static final int CA_HEIGHT = 5000;
    private static final int XLCOORD = 370;             // sets the spawn point of the game window on the computer screen
    private static final int YLCOORD = 100;             // sets the spawn point of the game window on the computer screen
    private static final int PLAYER_X = W_WIDTH / 2;
    private static final int PLAYER_Y = W_HEIGHT / 2;
    private static final int OFFSET_X = 450;
    private static final int OFFSET_Y = 285;
    private static final int START_X = -1830;
    private static final int START_Y = -290;
    private static final int MOVE = 10;                 // movement speed
    
    // variables
    private static int x = -1830;
    private static int y = -290;
    private static JLayeredPane window = new JLayeredPane();
    private static JPanel caPanel = new JPanel();
    private static JLabel currentArea = new JLabel();
    private static JLabel playerLevel = new JLabel();
    private static BoundaryLines boundaryPanel;
    private static ItemPanel itemPanel;
    private static CollisionBox playerBox;
    private static Player tome; //
    
    // ------------ Constructor ------------ \\
    public MainWindow() throws IOException {
        // standard setup
        setTitle("Game name");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //--------add more code after this line -------//
        
        // ------------ Window ------------ \\
        add(window);
        window.setBounds(x, y, W_WIDTH, W_HEIGHT); 
        // ------------ Player ------------ \\
        tome = new Player();
        playerLevel.setIcon(tome.sprite);
        playerLevel.setBounds(PLAYER_X, PLAYER_Y, tome.getWidth(), tome.getHeight());
        playerBox = new CollisionBox(PLAYER_X, PLAYER_Y + 5, tome.getWidth() - 5, tome.getHeight() - 13);
        playerBox.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        // ------------ Panels ------------ \\
        boundaryPanel = new BoundaryLines();
        boundaryPanel.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        currentArea.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        caPanel.add(currentArea);
        itemPanel = new ItemPanel();
        itemPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
        // add to window
        window.add(caPanel, new Integer(0), 0);
        window.add(boundaryPanel, new Integer(1), 0);
        window.add(playerBox, new Integer(1), 0);
        window.add(itemPanel, new Integer(2), 0);
        window.add(playerLevel, new Integer(3), 0);
        
        //--------add more code before this line -------//
        
        this.addKeyListener(this);
        this.setFocusable(true);
        setLocation(XLCOORD, YLCOORD);
        setVisible(true);
    }
    
    // ------------ Sprites ------------ \\
    private void shiftWorld(ImageIcon image) {
        currentArea.setIcon(image);
    }
    
    private void shiftPlayerSprite(ImageIcon image) {
        tome.sprite = image;
        playerLevel.setIcon(image);
    }
    
    private void addItems(ArrayList<Item> items) {
        itemPanel = new ItemPanel(items);
        itemPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
        window.add(itemPanel, new Integer(2), 0);
    }
    
    // ------------ Boundaries ------------ \\
    // Creates the boundary, gate, and item lines for the level
    private void addBoundaries(Place place) {
        boundaryPanel.createLines(place, OFFSET_X, OFFSET_Y);
        int[] spawnPoint = place.sendSpawnPoint();
        x = spawnPoint[0];
        y = spawnPoint[1];
        caPanel.setLocation(x, y);
        boundaryPanel.moveLines(x - START_X, y - START_Y);
    }
    
    // ------------ Changing Worlds ------------ \\
    // Handles all changes    
    // used when tome crosses a gate
    public void swapWorlds(Gate g) {
        Place current = g.toNextWorld();
        shiftWorld(current.sendImage());
        addBoundaries(current);
        if (current.hasItems == true) addItems(boundaryPanel.sendItems());
//        if (current.hasItems == true) { 
//            boundaryPanel.itemPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
//            window.add(boundaryPanel.itemPanel, new Integer(2), 0);
//        }
    }
    
    // With Place parameter
    public void swapWorlds(Place place) {
        shiftWorld(place.sendImage());
        addBoundaries(place);
        if (place.hasItems == true) addItems(boundaryPanel.sendItems());
//        if (place.hasItems == true) { 
//            boundaryPanel.itemPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT);
//            window.add(boundaryPanel.itemPanel, new Integer(2), 0);
//        }
    }
}
*/

///*
public class MainWindow extends JFrame implements KeyListener {
    
    // Moves currentArea with arrow keys
    public void keyPressed(KeyEvent event) {
        // ------------ Movement ------------ \\
        // going left
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (boundaryPanel.moveR == true) {
                x -= MOVE;
                boundaryPanel.moveLines(-MOVE, 0);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[4])) shiftPlayerSprite(tome.sprites[4]);
                else shiftPlayerSprite(tome.sprites[5]);  
                // ------------ Boundary Collision ------------ \\
                // if this movement caused a collision:
                // the movement flag becomes false.
                // tome can no longer go in this direction 
                // and will not be able to until the movement flag turns true again.
                // i.e. tome moves away from the boundary
//                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
//                    boundaryPanel.moveR = false;
//                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // going right
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            if (boundaryPanel.moveL == true) {
                x += MOVE;
                boundaryPanel.moveLines(MOVE, 0);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[1])) shiftPlayerSprite(tome.sprites[1]);
                else shiftPlayerSprite(tome.sprites[2]);
                // ------------ Boundary Collision ------------ \\
//                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
//                    boundaryPanel.moveL = false;
//                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // going down
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            if (boundaryPanel.moveU == true) {
                y += MOVE;
                boundaryPanel.moveLines(0, MOVE);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[10])) shiftPlayerSprite(tome.sprites[10]);
                else shiftPlayerSprite(tome.sprites[11]);
                // ------------ Boundary Collision ------------ \\
//                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
//                    boundaryPanel.moveU = false;
//                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // going up
        if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
            if (boundaryPanel.moveD == true) {
                y -= MOVE;
                boundaryPanel.moveLines(0, -MOVE);
                caPanel.setLocation(x, y);
                // ------------ Changing sprites ------------ \\
                if (!tome.sprite.equals(tome.sprites[7])) shiftPlayerSprite(tome.sprites[7]);
                else shiftPlayerSprite(tome.sprites[8]);
                // ------------ Boundary Collision ------------ \\
//                if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == true) {
//                    boundaryPanel.moveD = false;
//                }
                // ------------ Gate Collision ------------ \\
                if (boundaryPanel.gateCrossed(playerBox.getBox()) == true) {
                    System.out.println("gate crossed");
                    Gate g = boundaryPanel.getCrossedGate();
                    swapWorlds(g);
                }
                // ------------ Item Collision ------------ \\
                if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                    System.out.println("item touched");
                }
            }
        }
        // ------------ Attacking ------------ \\
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            // attack left
            if (tome.sprite.equals(tome.sprites[4]) || tome.sprite.equals(tome.sprites[5]) || tome.sprite.equals(tome.sprites[3])) {
                shiftPlayerSprite(tome.sprites[14]);
            }
            // attack right
            if (tome.sprite.equals(tome.sprites[1]) || tome.sprite.equals(tome.sprites[2]) || tome.sprite.equals(tome.sprites[0])) {
                shiftPlayerSprite(tome.sprites[13]);
            }
            // attack up
            if (tome.sprite.equals(tome.sprites[10]) || tome.sprite.equals(tome.sprites[11]) || tome.sprite.equals(tome.sprites[9])) {
                shiftPlayerSprite(tome.sprites[15]);
            }
            // attack down
            if (tome.sprite.equals(tome.sprites[7]) || tome.sprite.equals(tome.sprites[8]) || tome.sprite.equals(tome.sprites[6])) {
                shiftPlayerSprite(tome.sprites[12]);
            }
                
        }
        // this allows tome to move away from the boundary
        if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == false) {
            boundaryPanel.moveL = true;
            boundaryPanel.moveR = true; 
            boundaryPanel.moveU = true; 
            boundaryPanel.moveD = true;
        }
        System.out.printf("x = %d, y = %d\n", x, y);
    }
    
    public void keyReleased(KeyEvent event) {
        // left
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) { 
            shiftPlayerSprite(tome.sprites[3]);
        }
        // right
        if (event.getKeyCode() == KeyEvent.VK_LEFT) { 
            shiftPlayerSprite(tome.sprites[0]);
        }
        // down
        if (event.getKeyCode() == KeyEvent.VK_UP) { 
            shiftPlayerSprite(tome.sprites[9]);
        }
        // up
        if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
            shiftPlayerSprite(tome.sprites[6]);
        }
    }
    
    public void keyTyped(KeyEvent event) {
    }
    
    // ------------------------------------------------------------------------------------------ \\
    // ------------------------------------------------------------------------------------------ \\
    // ---------------------------------------- Main Program ------------------------------------ \\
    // ------------------------------------------------------------------------------------------ \\
    // ------------------------------------------------------------------------------------------ \\
    // constants
    private static final int W_WIDTH = 700;             // width of frame
    private static final int W_HEIGHT = 600;            // height of window
    private static final int CA_WIDTH = 5000;            
    private static final int CA_HEIGHT = 5000;
    private static final int XLCOORD = 370;             // sets the spawn point of the game window on the computer screen
    private static final int YLCOORD = 100;             // sets the spawn point of the game window on the computer screen
    private static final int PLAYER_X = W_WIDTH / 2;
    private static final int PLAYER_Y = W_HEIGHT / 2;
    private static final int OFFSET_X = 450;
    private static final int OFFSET_Y = 285;
    private static final int START_X = -1830;
    private static final int START_Y = -290;
    private static final int MOVE = 25;                 // movement speed 10
    
    // variables
    private static int x = -1830;
    private static int y = -290;
    private static JLayeredPane window = new JLayeredPane();
    private static JPanel caPanel = new JPanel();
    private static JLabel currentArea = new JLabel();
    private static JLabel playerLevel = new JLabel();
    private static BoundaryLines boundaryPanel;
    private static CollisionBox playerBox;
    private static Player tome; //
    private static Place place;
    
    // ------------ Constructor ------------ \\
    public MainWindow(Place place) throws IOException {
        // standard setup
        setTitle("Game name");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //--------add more code after this line -------//
        // ------------ Place------------ \\
        this.place = place;
        // ------------ Window ------------ \\
        add(window);
        window.setBounds(x, y, W_WIDTH, W_HEIGHT); 
        // ------------ Player ------------ \\
        tome = new Player();
        playerLevel.setIcon(tome.sprite);
        playerLevel.setBounds(PLAYER_X, PLAYER_Y, tome.getWidth(), tome.getHeight());
        playerBox = new CollisionBox(PLAYER_X, PLAYER_Y, tome.getWidth() + 5, tome.getHeight() + 5);
        playerBox.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        // ------------ Panels ------------ \\
        boundaryPanel = new BoundaryLines(this.place, OFFSET_X, OFFSET_Y);
        boundaryPanel.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        currentArea.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        caPanel.add(currentArea);
        // add to window
        window.add(caPanel, new Integer(0), 0);
        window.add(boundaryPanel, new Integer(1), 0);
        window.add(playerBox, new Integer(1), 0);
        window.add(playerLevel, new Integer(2), 0);
        
        //--------add more code before this line -------//
        
        this.addKeyListener(this);
        this.setFocusable(true);
        setLocation(XLCOORD, YLCOORD);
        setVisible(true);
    }
    
    // ------------ Sprites ------------ \\
    private void shiftWorld(ImageIcon image) {
        currentArea.setIcon(image);
    }
    
    private void shiftPlayerSprite(ImageIcon image) {
        tome.sprite = image;
        playerLevel.setIcon(image);
    }  
    
    // ------------ Boundaries ------------ \\
    // Creates the boundary, gate, and item lines for the level
    private void addBoundaries() {
        this.place.buildGates();
        boundaryPanel.createLines(this.place, OFFSET_X, OFFSET_Y);
        int[] spawnPoint = place.sendSpawnPoint();
        x = spawnPoint[0];
        y = spawnPoint[1];
        caPanel.setLocation(x, y);
        boundaryPanel.moveLines(x - START_X, y - START_Y);
    }
    
    // ------------ Changing Worlds ------------ \\
    // Handles all changes 
    
    // used when tome crosses a gate
    public void swapWorlds(Gate g) {
        this.place = g.toNextWorld();
        shiftWorld(this.place.sendImage());
        if (this.place.hasItems()) { 
            this.place.buildItems();
        }
        addBoundaries();
    }
    
    // With Place parameter
    public void swapWorlds() {
        shiftWorld(place.sendImage());
        if (this.place.hasItems()) { 
            this.place.buildItems();
        }
        addBoundaries();
    }
}
//*/



