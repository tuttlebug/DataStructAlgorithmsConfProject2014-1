import java.util.*;

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
 * 
 * ?????????????????????????
 * Things to add:
 *  1. movement in a place
 *  2. array of in-bounds and out-of-bounds spaces  
 */
public class Place {
    
    // instance variables
    private String name; 
    private ArrayList<Place> neighbors;
    private ArrayList<String> items;
    private ArrayList<String> neighborList;  // ONLY HERE FOR SAKE OF TOSTRING 
    
    // constructor
    public Place(String name) {
        this.name = name;
        this.neighbors =  new ArrayList<Place>();
        this.items = new ArrayList<String>();
        this.neighborList = new ArrayList<String>();
    }
    
    // methods
    public void addPlace(Place place) {
        this.neighbors.add(place);
        this.neighborList.add(place.getName());
    }
    
//    public void sharePlaces(Place place) {
//        place
//    }
    
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
