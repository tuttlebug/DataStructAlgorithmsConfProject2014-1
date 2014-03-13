import java.util.*;

/**
 * The World map:
 * consists of a Map that keys the world names
 * 
 * Worlds:
 *      Cave [Village]
 *      Village [Cave, Bridge]
 *      Foreign town [Bridge, Wilds]
 *      Wilds [Foreign Town, Shadow Path]
 *      Shadow Path [Wilds, Temple]
 */
public class WorldMap {
    
    // instance variables
    private Map<String, Place> places; 
    
    // constructor
    public WorldMap () {
        this.places = new HashMap<String, Place>();    
    }
    
    // methods
    public void add(String name, Place place) {
        this.places.put(name, place);
    }
    
    public Place getPlace(String name) {
        return this.places.get(name);
    }
    
    public Collection getValues() {
        return this.places.values();
    }
    
    public Set keys() {
        return this.places.keySet();
    }
    
}
