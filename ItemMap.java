/**
 * The Item map:
 * consists of a Map that keys the items names to their objects
 * 
 * Check List:
 * [Ã] Map of Items
 * [] 
 * 
 * To Add/Implement:
 * [] Might need to extend a class (HashMap i.e)
 * [] remove method
 * [] Being a fully static class
 * [] Becoming a global variable
 * 
 * Temporary:
 * [] Instance variables
 */
import java.util.*;

public class ItemMap {
    
    // instance variables
    private Map<String, Item> items; 
    private ArrayList<String> itemsNames;   // ONLY HERE FOR SAKE OF TOSTRING 
    private ArrayList<String> itemsObjects; // ONLY HERE FOR SAKE OF TOSTRING 
    public int size; 
    
    // constructor
    public ItemMap () {
        this.items = new HashMap<String, Item>();  
        this.itemsNames = new ArrayList<String>();
        this.itemsObjects = new ArrayList<String>();
        this.size = 0;
    }
    
    // methods
    public void add(String name, Item item) {
        this.items.put(name, item);
        this.itemsNames.add(name);
        this.itemsObjects.add(item.toString());
        this.size++;
    }
    
    public Item getItem(String name) {
        return this.items.get(name);
    }
    
    public Collection getValues() {
        return this.items.values();
    }
    
    public Set keys() {
        return this.items.keySet();
    }
    
    public String toString() {
        return String.format("ITEMS: %s\n", this.itemsNames);  
    }
    
}