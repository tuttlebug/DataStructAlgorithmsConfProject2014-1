import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Place object:
 * contains:
 *   an Array List of the places it's conected to;
 *   a Name;
 *   an Array List of removable items;
 * 
 * Notes:
 * String objects will change to Place Objects later;
 * 
 * @ Parameters:
 *      1. Name of Place
 *      2. Spawn point for character
 * 
 * 
 * ?????????????????????????
 * Things to add:
 *  1. movement in a place
 *  2. array of in-bounds and out-of-bounds spaces  
 * ???????????
 * 
 * Worlds:
 *      Cave [Village]
 *      Village [Cave, Bridge]
 *      Foreign town [Bridge, Wilds]
 *      Wilds [Foreign Town, Shadow Path]
 *      Shadow Path [Wilds, Temple]
 */
public class Place {
    
    // instance variables
    private String name; 
    private ArrayList<Place> neighbors;
    private ArrayList<String> items;
    private ArrayList<String> neighborList;  // ONLY HERE FOR SAKE OF TOSTRING 
    private int[] spawnPoint;
    private Player player;
    
    // Temporary JFrame and JPanel
    private JFrame world;
    private JPanel elements;
    
    // constructor
    public Place(String name, int[] spawnPoint, Player player) {
        this.name = name;
        this.neighbors =  new ArrayList<Place>();
        this.items = new ArrayList<String>();
        this.neighborList = new ArrayList<String>();
        this.spawnPoint = spawnPoint; 
        // Temporary JFrame and JPanel
        this.world = new JFrame();
        this.elements = new JPanel();
        this.player = new Player();
    }
    
    // methods
    public void addPlace(Place place) {
        this.neighbors.add(place);
        this.neighborList.add(place.getName());
    }
    
    /**
     * Construct the elements and visual of place;
     * @param: int[] of sprite locations 
     *   Will likely need to more parameters
     */
    public void constructPlace(Color background) {
        // Temporary JFrame and JPanel
        // initialize Panel / setup
        this.world.setSize(700, 600);
        this.world.setTitle(this.name);
        this.world.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // adding to the panel
        // --------------------
        
        // background
        // - will be a real background
        this.elements.setBackground(background);
        
        // collideables
        /**
         * take in a map of collideable objects
         * 
         */
        
        this.world.add(this.elements);
        this.world.add(this.player);
        /*
         * ERROR:
         * this.player panel covers up background
         */
        this.world.setVisible(true);
    }
    
    /**
     * item should have reference to place that it is in;
     */
    public void addItem(String item) {
        this.items.add(item);
    }
    
    public void removePlace(int pos) {
        this.neighbors.remove(pos);
    }
    
    public void removeItem(int pos) {
        this.items.remove(pos);
    }
    
    
    public String getName() {
        return this.name;
    }
    
    public ArrayList getPlaces() {
        return this.neighbors;
    }
    
    
    public String toString() {
        return String.format("NAME: %s\nNEIGHBORING AREAS: %s\nAVAILABLE ITEMS: %s\n\n",
                             this.name, this.neighborList, this.items);
    }
    
}
