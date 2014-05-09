import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Arrays;

/**
 * Place object:
 * Check-List:
 * [Ã] A name
 * [Ã] A map of the places it's connected to
 * [] A Map of removable items;
 * [Ã] Image depicting full world
 * [Ã] Array of "blocked off" areas
 * [] Array of locations for people and items
 * 
 * To Add/Implement:
 * [Ã] movement in a place
 * [] remove Items and Places from ArrayList when they're removed from the map 
 * [Ã] Gate Arrays
 * [] becomes a JPanel
 *   [] that loads an imge (paint)
 * [] add BoundaryLines
 * 
 * Parameters:
 * [Ã] Name of Place
 * [Ã] Name of background image
 * 
 * Temporary:
 * [] int[] spawnPoint;
 * [] loadGatepoints
 * [] gatepoints
 * 
 * Possible methods:
 * [] updateGraphics
 * [] repaint
 * [] draw();
 *   - no need to extend JPanel etc.
 * [] translate
 */

public class Place implements LoadImage {
    
    private static final int OFFSET_X = 450;
    private static final int OFFSET_Y = 285;
    
    // ------------ instance variables ------------ \\
    private String name; 
    private Map<String, Place> neighbors;
//    private Map<String, Item> items;
    private ArrayList<Item> items;
    private ArrayList<String> neighborList;  // ONLY HERE FOR SAKE OF TOSTRING 
    private ArrayList<String> itemList;      // ONLY HERE FOR SAKE OF TOSTRING 
    private ArrayList<String> enemyList;      // ONLY HERE FOR SAKE OF TOSTRING 
    private ArrayList<Gate> gateList;
    private ArrayList<Enemy> enemies;
    private ArrayList<NPC> npcs;
    private int[] spawnPoint;
    private int[][] boundaryPoints;
    private int[][] gatePoints;
    private int[][] itemPoints;
    private int[][] enemyPoints;
    private int[][] npcPoints;
    private String imageFile;
    private String gatePointsString;         // ONLY HERE FOR SAKE OF TOSTRING
    private String gatePointsStringBefore;   // ONLY HERE FOR SAKE OF TOSTRING
    private ImageIcon background;
    private boolean hasItems;
    private boolean hasEnemies;
    private boolean hasNPCs;
//    private JPanel gui; // Menu gui
    
    // ------------ Constructor ------------ \\
    public Place(String name, String imageFile) {
        this.name = name;
        this.neighbors = new HashMap<String, Place>();
//        this.items = new HashMap<String, Item>();
        this.neighborList = new ArrayList<String>();
        this.itemList = new ArrayList<String>();
        this.enemyList = new ArrayList<String>();
        this.npcs = new ArrayList<NPC>();
        this.enemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Item>();
        this.gateList = new ArrayList<Gate>();
        this.imageFile = imageFile;
//        this.gui = new JPanel();
        this.spawnPoint = new int[2];
        this.gatePointsString = "";
        this.gatePointsStringBefore = "";
        this.hasItems = false;
        this.hasEnemies = false;
        this.hasNPCs = false;
    }
    // ------------ Background Image ------------ \\
    // loads the background image
    public void loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(this.imageFile));
        this.background = new ImageIcon(image);
    }
    
    // sends the background over
    public ImageIcon sendImage() {
        return this.background;
    }
    
    // ------------ Boundaries ------------ \\
    // assign boundaries for the player
    public void loadBoundaryPoints(int[][] boundaryPoints) {
        for (int i = 0; i < boundaryPoints.length; i++) {
            for (int j = 0; j < boundaryPoints[i].length; j++) {
                boundaryPoints[i][j] *= 32;
            }
        }
        this.boundaryPoints = boundaryPoints;
    }
    
    public int[][] sendBoundaryPoints() {
        return this.boundaryPoints;
    }
    
    // ------------ Gates ------------ \\
    // load gate points
    public void loadGatePoints(int[][] gatePoints) {
        for (int i = 0; i < gatePoints.length; i++) {
//            this.gatePointsStringBefore += String.format("i = %s, j =  ", Arrays.toString(gatePoints[i]));
            for (int j = 0; j < gatePoints[i].length; j++) {
//                this.gatePointsStringBefore += String.format("%d ", gatePoints[i][j]);
                gatePoints[i][j] *= 32;
            }
        }
        this.gatePoints = gatePoints;
    }
    
    public void loadGates(Place nextArea, int[] end) throws IOException {
        this.gateList.add(new Gate(nextArea, end));  
    }
   
    // build gate lines
    public void buildGates() {
        int gateIterator = 0;
        for (int i = 0; i < this.gatePoints.length; i++) {
            for (int j = 3; j < this.gatePoints[i].length; j+=3) {
                this.gateList.get(gateIterator).loadLine(
                                                      this.gatePoints[i][j-3] - OFFSET_X,
                                                      this.gatePoints[i][j-2] - OFFSET_Y,
                                                      this.gatePoints[i][j-1] - OFFSET_X,
                                                      this.gatePoints[i][j] - OFFSET_Y
                                                     );
            }
            gateIterator++;
        }
    }
    
    public ArrayList<Gate> sendGates() {
        return this.gateList;
    }
    
    
//    public String printGatePoints() {
//        if (this.gateList.size() > 0) {
//            for (int i = 0; i < this.gatePoints.length; i++) {
//                for (int j = 0; j < this.gatePoints[i].length; j++) {
//                    this.gatePointsString += String.format("%d ", this.gatePoints[i][j]);  
//                }
//            }
//        }
//        return this.gatePointsString;
//    }    
    
    // ------------ Spawn points ------------ \\
    // load spawn point
    public void loadSpawnPoint(int x, int y) {
        this.spawnPoint[0] = x;
        this.spawnPoint[1] = y;
    }
    
    public int[] sendSpawnPoint() {
        return this.spawnPoint;
    }    
    
    // ------------ Places ------------ \\
    public String getName() {
        return this.name;
    }
     
    public void addPlace(Place place) {
        this.neighbors.put(place.getName(), place);
        this.neighborList.add(place.getName());
    }
    
    
    public void removePlace(String name) {
        this.neighbors.remove(name);
    }
    
    public Place getPlace(String name) {
        return this.neighbors.get(name);
    }
    
    // ------------ Items ------------ \\
    public void addItem(Item item) {
        this.items.add(item);
        this.itemList.add(item.getName());
        this.hasItems = true;
    }
    
    public void removeItem(Item item) {
        this.items.remove(item);
        if (this.items.size() <= 0) this.hasItems = false;
    }   
    
    public void loadItemPoints(int[][] itemPoints) {
        for (int i = 0; i < itemPoints.length; i++) {
            for (int j = 0; j < itemPoints[i].length; j++) {
                itemPoints[i][j] *= 32;
            }
        }
        this.itemPoints = itemPoints;
    }
    
    public void buildItems() {
        int itemIterator = 0;
        for (int i = 0; i < this.itemPoints.length; i++) {
            for (int j = 1; j < this.itemPoints[i].length; j+=1) {
                this.items.get(itemIterator).loadBox(
                                                        this.itemPoints[i][j-1] - OFFSET_X,
                                                        this.itemPoints[i][j] - OFFSET_Y
                                                       );
            }
            itemIterator++;
        }
    }
    
    public ArrayList<Item> sendItems() {
        return this.items;
    }
    
    public boolean hasItems() {
        return this.hasItems;
    }
    
    // ------------ Enemies ------------ \\
    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
        this.enemyList.add(enemy.getName());
        this.hasEnemies = true;
    }
    
    public void removeEnemy(Enemy enemy) {
        this.enemies.remove(enemy);
        if (this.enemies.size() <= 0) this.hasEnemies = false;
    } 
    
    public void loadEnemyPoints(int[][] enemyPoints) {
        for (int i = 0; i < enemyPoints.length; i++) {
            for (int j = 0; j < enemyPoints[i].length; j++) {
                enemyPoints[i][j] *= 32;
            }
        }
        this.enemyPoints = enemyPoints;
    }
    
    // build enemy boxes
    public void buildEnemies() {
        int enemyIterator = 0;
        for (int i = 0; i < this.enemyPoints.length; i++) {
            for (int j = 1; j < this.enemyPoints[i].length; j+=1) {
                this.enemies.get(enemyIterator).loadBox(
                                                      this.enemyPoints[i][j-1] - OFFSET_X,
                                                      this.enemyPoints[i][j] - OFFSET_Y
                                                     );
            }
            enemyIterator++;
        }
    }
    
    public ArrayList<Enemy> sendEnemies() {
        return this.enemies;
    }
    
    public boolean hasEnemies() {
        return this.hasEnemies;
    }
    
    // ------------ NPCs ------------ \\
    public void addNPC(NPC npc) {
        this.npcs.add(npc);
        this.hasNPCs = true;
    }
    
    
    public void loadNPCPoints(int[][] npcPoints) {
        for (int i = 0; i < npcPoints.length; i++) {
            for (int j = 0; j < npcPoints[i].length; j++) {
                npcPoints[i][j] *= 32;
            }
        }
        this.npcPoints = npcPoints;
    }
    
    // build enemy boxes
    public void buildNPCs() {
        int npcIterator = 0;
        for (int i = 0; i < this.npcPoints.length; i++) {
            for (int j = 1; j < this.npcPoints[i].length; j+=1) {
                this.npcs.get(npcIterator).loadBox(
                                                      this.npcPoints[i][j-1] - OFFSET_X,
                                                      this.npcPoints[i][j] - OFFSET_Y
                                                     );
            }
            npcIterator++;
        }
    }
    
    public ArrayList<NPC> sendNPCs() {
        return this.npcs;
    }
    
    public boolean hasNPCs() {
        return this.hasNPCs;
    }
    
    // ------------ toString() ------------ \\
    public String toString() {
        return String.format("\nNAME: %s\nNEIGHBORING AREAS: %s\nAVAILABLE ITEMS: %s\nBACKGROUND: %s, SPAWNPOINTS: %d,%d \nGATES: %s\n",
                             this.name, 
                             this.neighborList, 
                             this.items, 
                             this.imageFile, 
                             this.spawnPoint[0], this.spawnPoint[1], 
                             this.gateList);
//        return String.format("\nNAME: %s\nNEIGHBORING AREAS: %s\nAVAILABLE ITEMS: %s\nBACKGROUND: %s, SPAWNPOINTS: %d,%d \nGATES: %s\nGATEPOINTS BEFORE: %s\nGATEPOINTS: %s\nGATES NUM: %d\nGATEPOINTS NUM: %d\n",
//                             this.name, 
//                             this.neighborList, 
//                             this.items, 
//                             this.imageFile, 
//                             this.spawnPoint[0], this.spawnPoint[1], 
//                             this.gateList,
//                             this.gatePointsStringBefore,
//                             this.printGatePoints(),
//                             this.gateList.size(),
//                             this.gatePoints.length);
    }
    
}