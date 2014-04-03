import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Place object:
 * Check-List:
 * [] A name
 * [] A map of the places it's connected to
 * [] A Map of removable items;
 * [] Image depicting full world
 * [] Array of "blocked off" areas
 * [] Array of locations for people and items
 * 
 * To Add/Implement:
 * [Ã] movement in a place
 * [] Have constructPlace() create neccessary data to add to the main Frame
 * []
 * 
 * Parameters:
 * [] Name of Place
 * [] Spawn point for character
 * 
 * Temporary:
 * [] Color background;
 * [] ArrayList<String> items;
 *   - [] Will be turned into a map
 */
public class Place {
    
    //_____
    // main frame
    //_____
    
    // instance variables
    private String name; 
    private Map<String, Place> neighbors;
    private ArrayList<String> items;
    private ArrayList<String> neighborList;  // ONLY HERE FOR SAKE OF TOSTRING 
    private int[] startPoint;
    private Player player;
    private Color background;  // Temporary (Will be replaced with an Background Image) 
    private JPanel gui; // Menu gui
    
    // constructor
//    public Place(String name, int[] startPoint, Player player, Color background) {
    public Place(String name) {
        this.name = name;
        this.neighbors = new HashMap<String, Place>();
        this.items = new ArrayList<String>();
        this.neighborList = new ArrayList<String>();
        this.startPoint = startPoint; 
        this.background = background;
        this.gui = new JPanel();
        this.player = new Player();
    }
    
    // methods
    public void addPlace(Place place) {
        this.neighbors.put(place.getName(), place);
        this.neighborList.add(place.getName());
    }
    
    public Place getPlace(String name) {
        return this.neighbors.get(name);
    }
    
    /**
     * VERSION 1
     * Construct the elements and visual of place
     * Currently only takes in a color
     */
    public void constructPlace(Color bg) {
        this.background = bg;
    }
    
    /**
     * VERSION 1
     * Sends construction data to mainWindow
     */
    public Color sendDetail() {
        return this.background;
    }
    
    
    /**
     * item should have reference to place that it is in;
     */
    public void addItem(String item) {
        this.items.add(item);
    }
    
    public void removePlace(String name) {
        this.neighbors.remove(name);
    }
    
    public void removeItem(int pos) {
        this.items.remove(pos);
    }
    
    
    public String getName() {
        return this.name;
    }
    
    public String toString() {
        return String.format("NAME: %s\nNEIGHBORING AREAS: %s\nAVAILABLE ITEMS: %s\n\n",
                             this.name, this.neighborList, this.items);
    }
    
}
