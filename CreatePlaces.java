/**
 * TESTING CLASS
 * 
 * Check List:
 * [] creates all Places
 * [] loads all backgrounds
 * [] creates all Items
 * [] loads all Item images
 */
import java.awt.*;
import java.util.*;
import java.io.*;

public class CreatePlaces {
    
    public static Player player = new Player();
    
    /* COMPREHENSIVE LIST OF WORLDS (in order of first appearance):
     * - "Village"
     * - "ForestPath_Cave"
     * - "Clearing_Cave"
     * - "Cave"
     * - "Bridge"
     * - "EndBridge"
     * - "WildForest_ForeignVillage"
     * - "ForeignVillage"
     * - "Wasteland"
     * - "SecretPaths"
     * - "Temple"
     */ 
    

    private static Place village = new Place("Village", "village.png");
    private static Place forestPath_Cave = new Place("ForestPath_Cave", "forestPath_Cave.png");
    private static Place clearing_Cave = new Place("Clearing_Cave", "clearing_Cave.png");
    private static Place cave = new Place("Cave", "cave.png");
    private static Place bridge = new Place("Bridge", "bridge.png");
    private static Place endBridge = new Place("EndBridge", "endBridge.png");
    private static Place wildForest_ForeignVillage = new Place("WildForest_ForeignVillage", "wildForest_ForeignVillage.png");
    private static Place foreignVillage = new Place("ForeignVillage", "foreignVillage.png");
    private static Place wasteland = new Place("Wasteland", "wasteland.png");
    private static Place secretPaths = new Place("SecretPaths", "secretPaths.png");
    private static Place temple = new Place("Temple", "temple.png");
    
    // create a map of all Places
    public static WorldMap createWorldMap() throws IOException {
        // add accessible areas
        village.addPlace(forestPath_Cave);
        village.addPlace(bridge);
        forestPath_Cave.addPlace(village);
        forestPath_Cave.addPlace(clearing_Cave);
        clearing_Cave.addPlace(forestPath_Cave);
        clearing_Cave.addPlace(cave);
        cave.addPlace(clearing_Cave);
        bridge.addPlace(village);
        bridge.addPlace(endBridge);
        endBridge.addPlace(bridge);
        endBridge.addPlace(wildForest_ForeignVillage);
        wildForest_ForeignVillage.addPlace(endBridge);
        wildForest_ForeignVillage.addPlace(foreignVillage);
        foreignVillage.addPlace(wildForest_ForeignVillage);
        foreignVillage.addPlace(wasteland);
        wasteland.addPlace(foreignVillage);
        wasteland.addPlace(secretPaths);
        secretPaths.addPlace(wasteland);
        secretPaths.addPlace(temple);
        temple.add(secretPaths);
        
        // create items
        Item dummy = new Item("Dummy", "item.png");
        
        // Load images
        // places
        village.loadImage();
        forestPath_Cave.loadImage();
        clearing_Cave.loadImage();
        cave.loadImage();
        bridge.loadImage();
        endBridge.loadImage();
        wildForest_ForeignVillage.loadImage();
        foreignVillage.loadImage();
        wasteland.loadImage();
        secretPaths.loadImage();
        temple.loadImage();
        
        // items
        dummy.loadImage();
        
        // add items to Places
        village.addItem(dummy);
        
        // Add all to World map 
        WorldMap worldMap = new WorldMap();
        worldMap.add(village.getName(), village);
        worldMap.add(forestPath_Cave.getName(), forestPath);
        worldMap.add(clearing_Cave.getName(), clearing_Cave);
        worldMap.add(cave.getName(), cave);
        worldMap.add(bridge.getName(), bridge);
        worldMap.add(endBridge.getName(), endBridge);
        worldMap.add(wildForest_ForeignVillage.getName(), wildForest_ForeignVillage);
        worldMap.add(foreignVillage.getName(), foreignVillage);
        worldMap.add(wasteland.getName(), wasteland);
        worldMap.add(secretPaths.getName(), secretPaths);
        worldMap.add(temple.getName(), temple);
        
        return worldMap;
    }
}
