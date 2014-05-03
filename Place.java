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
 * [] Have constructPlace() create neccessary data to add to the main window
 * [] remove Items and Places from ArrayList when they're removed from the map 
 * [] Gate Arrays
 * [] becomes a JPanel
 *   [] that loads an imge (paint)
 * 
 * Parameters:
 * [Ã] Name of Place
 * [] Spawn point for character
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



// VERSION 1

public class Place implements LoadImage {
    
    // instance variables
    private String name; 
    private Map<String, Place> neighbors;
    private Map<String, Item> items;
    private ArrayList<String> neighborList;  // ONLY HERE FOR SAKE OF TOSTRING 
    private ArrayList<String> itemList;  // ONLY HERE FOR SAKE OF TOSTRING 
    private ArrayList<Gate> gateList;
    private int[] spawnPoint;
    private String imageFile;
    private ImageIcon background;
    private int[][] boundaryPoints;
    private int[][] gatePoints;
//    private JPanel gui; // Menu gui
    
    // constructor
    public Place(String name, String imageFile) {
        this.name = name;
        this.neighbors = new HashMap<String, Place>();
        this.items = new HashMap<String, Item>();
        this.neighborList = new ArrayList<String>();
        this.itemList = new ArrayList<String>();
        this.gateList = new ArrayList<Gate>();
        this.imageFile = imageFile;
//        this.gui = new JPanel();
        this.spawnPoint = new int[2];
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
            for (int j = 0; j < gatePoints[i].length; j++) {
                gatePoints[i][j] *= 32;
            }
        }
        this.gatePoints = gatePoints;
    }
    
//    public void loadGates(String name, int[] start, int[] end, int[][] gatePoints) {
//        this.gateList.add(new Gate(name, start, end, gatePoints));
//    }
    public int[][] sendGatePoints() {
        return this.gatePoints;
    }
    
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
        this.items.put(item.getName(), item);
        this.itemList.add(item.getName());
    }
    
    public void removeItem(String name) {
        this.items.remove(name);
    }   
    
    public String getName() {
        return this.name;
    }
    
    public String toString() {
        return String.format("\nNAME: %s\nNEIGHBORING AREAS: %s\nAVAILABLE ITEMS: %s\nBACKGROUND: %s\n",
                             this.name, this.neighborList, this.itemList, this.imageFile);
    }
    
}