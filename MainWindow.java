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
 * [Ã] method that handles switching from place to another place
 *   - [] changing the image
 *   - [] 
 * [] move the images like we move the lines
 *   - using paint
 * 
 * Temporary:
 * 
 * METHODS TO THINK ABOUT:
 * [] JLayeredPane.paint(Graphics g)
 * [] player as variable
 * [] setPosition(Component c, int position)
 * 
 * THINGS TO CONSIDER:
 * Graphs
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

///*
public class MainWindow extends JFrame implements KeyListener {
    
    // Moves currentArea with arrow keys
    public void keyPressed(KeyEvent event) {
        // ------------ Movement ------------ \\
        // going left
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveLeft();
        }
        // going right
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            moveRight();
        }
        // going down
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            moveDown();
        }
        // going up
        if (event.getKeyCode() == KeyEvent.VK_DOWN) { 
            moveUp(); 
        }
        // ------------ Attacking ------------ \\
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            attack();
        }
        // ------------ Menu ------------ \\
//        if (event.getKeyCode() == KeyEvent.VK_M) {
//            boundaryPanel.moveL = false;
//            boundaryPanel.moveR = false; 
//            boundaryPanel.moveU = false; 
//            boundaryPanel.moveD = false;
//            System.out.println(window.highestLayer());
//            System.out.println(window.getIndexOf(stscreen));
//            window.remove(0);
//            repaint();
//        }
        // ------------ Refresh ------------ \\
        // this allows tome to move away from the boundary
        if (boundaryPanel.boundaryCrossed(playerBox.getBox()) == false && boundaryPanel.npcSpeaking(playerBox.getBox()) == false) {
            boundaryPanel.moveL = true;
            boundaryPanel.moveR = true; 
            boundaryPanel.moveU = true; 
            boundaryPanel.moveD = true;
        }
        if (boundaryPanel.npcSpeaking(playerBox.getBox()) == false) {
            for (NPC npc : this.place.sendNPCs()) {
                if (npc.isSpeaking()) {
                    npc.noTurn(false);
                }
            }
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
    private static final int MOVE = 15;                 // movement speed 10
    
    // variables
    private static int x = -1830;
    private static int y = -290;
    private static JLayeredPane window = new JLayeredPane();
    private static JPanel caPanel = new JPanel();
    private static JLabel currentArea = new JLabel();
    private static JLabel playerLevel = new JLabel();
    private static BoundaryLines boundaryPanel;
    private static CollisionBox playerBox;
//    private static StartScreen stscreen;
    private static Player tome;
    private static Place place;
    private static Timer timer;
    
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
        playerBox = new CollisionBox(PLAYER_X + 8, PLAYER_Y + 20, tome.getWidth() - 15, tome.getHeight() - 20);
        playerBox.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        // ------------ Panels ------------ \\
        boundaryPanel = new BoundaryLines(this.place, OFFSET_X, OFFSET_Y);
        boundaryPanel.setBounds(0, 0, CA_WIDTH, CA_HEIGHT);
        caPanel.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        currentArea.setBounds(x, y, CA_WIDTH, CA_HEIGHT); 
        caPanel.add(currentArea);
//        stscreen = new StartScreen();
//        stscreen.setBounds(0, 0, W_WIDTH, W_HEIGHT / 2); 
        // add to window
        window.add(caPanel, new Integer(0), 0);
        window.add(boundaryPanel, new Integer(1), 0);
        window.add(playerBox, new Integer(1), 0);
        window.add(playerLevel, new Integer(2), 0);
//        window.add(stscreen, new Integer(3), 0);
        
        //--------add more code before this line -------//
        
        this.addKeyListener(this);
//        this.timer = new Timer(500, this); 
//        this.timer.setDelay(50);
//        this.timer.start();
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

    public void swapWorlds() {
        shiftWorld(this.place.sendImage());
        if (this.place.hasItems()) { 
            this.place.buildItems();
        }
        if (this.place.hasEnemies()){
            this.place.buildEnemies();
        }
        if (this.place.hasNPCs()) {
            this.place.buildNPCs();
        }
        addBoundaries();
    }
    
    // ------------ Movement methods ------------ \\
    // ------------ Left ------------ \\
    public void moveLeft() {
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
                this.place = g.toNextWorld();
                swapWorlds();
            }
            // ------------ Item Collision ------------ \\
            if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                System.out.println("item touched");
                
            }
            // ------------ Enemy Collision ------------ \\
            if (boundaryPanel.enemyTouched(playerBox.getBox()) == true) {
                Enemy e = boundaryPanel.getTouchedEnemy();
                tome.loseHealth(e.attack());
                System.out.printf("Tome's health is %d\n", tome.getHealth());
            }
            // ------------ NPC Collision ------------ \\
            if (boundaryPanel.npcSpeaking(playerBox.getBox()) == true) {
                NPC npc = boundaryPanel.getSpeakingNPC();
                if (!npc.isSpeaking()) {
                    npc.turn(npc.sprites[0]);
                    npc.noTurn(true);
                    System.out.println("NPC speaking");
                }
                boundaryPanel.moveR = false;
            }
        }
    }
    // ------------ Right ------------ \\
    public void moveRight() {
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
                this.place = g.toNextWorld();
                swapWorlds();
            }
            // ------------ Item Collision ------------ \\
            if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                System.out.println("item touched");
            }
            // ------------ Enemy Collision ------------ \\
            if (boundaryPanel.enemyTouched(playerBox.getBox()) == true) {
                Enemy e = boundaryPanel.getTouchedEnemy();
                tome.loseHealth(e.attack());
                System.out.printf("Tome's health is %d\n", tome.getHealth());
            }
            // ------------ NPC Collision ------------ \\
            if (boundaryPanel.npcSpeaking(playerBox.getBox()) == true) {
                NPC npc = boundaryPanel.getSpeakingNPC();
                if (!npc.isSpeaking()) {
                    npc.turn(npc.sprites[3]);
                    npc.noTurn(true);
                    System.out.println("NPC speaking");
                }
                boundaryPanel.moveL = false;
            }
        }
    }
    // ------------ Down ------------ \\
    public void moveDown() {
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
                this.place = g.toNextWorld();
                swapWorlds();
            }
            // ------------ Item Collision ------------ \\
            if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                System.out.println("item touched");
            }
            // ------------ Enemy Collision ------------ \\
            if (boundaryPanel.enemyTouched(playerBox.getBox()) == true) {
                Enemy e = boundaryPanel.getTouchedEnemy();
                tome.loseHealth(e.attack());
                System.out.printf("Tome's health is %d\n", tome.getHealth());
            }
            // ------------ NPC Collision ------------ \\
            if (boundaryPanel.npcSpeaking(playerBox.getBox()) == true) {
                NPC npc = boundaryPanel.getSpeakingNPC();
                if (!npc.isSpeaking()) {
                    npc.turn(npc.sprites[6]);
                    npc.noTurn(true);
                    System.out.println("NPC speaking");
                }
                boundaryPanel.moveU = false;
            }
        }
    }
    // ------------ Up ------------ \\
    public void moveUp() {
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
                this.place = g.toNextWorld();
                swapWorlds();
            }
            // ------------ Item Collision ------------ \\
            if (boundaryPanel.itemTouched(playerBox.getBox()) == true) {
                System.out.println("item touched");
            }
            // ------------ Enemy Collision ------------ \\
            if (boundaryPanel.enemyTouched(playerBox.getBox()) == true) {
                Enemy e = boundaryPanel.getTouchedEnemy();
                tome.loseHealth(e.attack());
                System.out.printf("Tome's health is %d\n", tome.getHealth());
            }
            // ------------ NPC Collision ------------ \\
            if (boundaryPanel.npcSpeaking(playerBox.getBox()) == true) {
                NPC npc = boundaryPanel.getSpeakingNPC();
                if (!npc.isSpeaking()) {
                    npc.turn(npc.sprites[11]);
                    npc.noTurn(true);
                    System.out.println("NPC speaking");
                    boundaryPanel.moveD = false;
                }
            }
        }
    }    
    // ------------ Attacking ------------ \\
    public void attack() {
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
}
//*/



