import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Place object:
 * Check-List:
 * [Ã] A name
 * [Ã] A map of the places it's connected to
 * [] A Map of removable items;
 * [Ã] Image depicting full world
 * [] Array of "blocked off" areas
 * [] Array of locations for people and items
 * 
 * To Add/Implement:
 * [Ã] movement in a place
 * [] Have constructPlace() create neccessary data to add to the main window
 * [] remove Items and Places from ArrayList when they're removed from the map 
 * 
 * Parameters:
 * [Ã] Name of Place
 * [] Spawn point for character
 * [Ã] Name of background image
 * 
 * Temporary:
 * [Ã] Color background;
 * [Ã] ArrayList<String> items;
 *   - [Ã] Will be turned into a map
 */
public class Place implements LoadImage {
    
    // instance variables
    private String name; 
    private Map<String, Place> neighbors;
    private Map<String, Item> items;
    private ArrayList<String> neighborList;  // ONLY HERE FOR SAKE OF TOSTRING 
    private ArrayList<String> itemList;  // ONLY HERE FOR SAKE OF TOSTRING 
//    private int[] startPoint;
    private String imageFile;
    private ImageIcon background;
    private int[][] boundaryPoints;
    private ArrayList<Line2D.Double> lines;
    private JPanel gui; // Menu gui
    
    // constructor
    public Place(String name, String imageFile) {
        this.name = name;
        this.neighbors = new HashMap<String, Place>();
        this.items = new HashMap<String, Item>();
        this.neighborList = new ArrayList<String>();
        this.itemList = new ArrayList<String>();
//        this.startPoint = startPoint; 
        this.imageFile = imageFile;
        this.gui = new JPanel();
    }
    
    // loads the background image
    public void loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(this.imageFile));
        this.background = new ImageIcon(image);
    }

    // sends the background over
    public ImageIcon sendImage() {
        return this.background;
    }
    
    // assign boundaries for the player
    public void loadBoundaryPoints(int[][] boundaryPoints) {
        this.boundaryPoints = boundaryPoints;
    }
    
    // create lines from points
    public void loadBoundaryLines() {
        
    }
    
    // send boundaries to MainWindow
    public int[][] sendBoundaries() {
        return this.boundaryPoints;
    }
    
    /**
     * item should have reference to place that it is in;
     */
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
