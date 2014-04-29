import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Line2D;
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
 * 
 * Parameters:
 * [Ã] Name of Place
 * [] Spawn point for character
 * [Ã] Name of background image
 * 
 * Temporary:
 * [] int[] spawnPoint;
 */
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
//    private int[][] gatePoints;
    private ArrayList<Line2D.Double> boundaryLines;
    private JPanel gui; // Menu gui
    
    // constructor
    public Place(String name, String imageFile) {
        this.name = name;
        this.neighbors = new HashMap<String, Place>();
        this.items = new HashMap<String, Item>();
        this.neighborList = new ArrayList<String>();
        this.itemList = new ArrayList<String>();
        this.boundaryLines = new ArrayList<Line2D.Double>();
        this.gateList = new ArrayList<Gate>();
        this.imageFile = imageFile;
        this.gui = new JPanel();
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
//    public void loadGatePoints(int[][] gatePoints) {
//        this.gatePoints = gatePoints;
//    }
    public void loadGates(String name, int[] start, int[] end) {
        this.gateList.add(new Gate(name, start, end));
    }
    
    // ------------ Spawn points ------------ \\
    // load spawn point
    public void loadSpawnPoint(int x, int y) {
        this.spawnPoint[0] = x * 32;
        this.spawnPoint[1] = y * 32;
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
