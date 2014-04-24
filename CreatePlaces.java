/**
 * TESTING CLASS
 * 
 * Check List:
 * [Ã] creates all Places
 * [Ã] loads all Place backgrounds
 * [] loads all Place boundaries
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
        temple.addPlace(secretPaths);
        
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
        
        // Load boundaries
        int[][] villageBoundaries = new int[][]{
          { 24, 10, 27, 10 },
          { 27, 10, 27, 11 },
          { 27, 11, 28, 11 },
          { 28, 11, 31, 14 },
          { 31, 14, 31, 15 },
          { 31, 15, 34, 15 },
          { 34, 15, 34, 16 },
          { 34, 16, 32, 16 },
          { 32, 16, 32, 18 },
          { 32, 18, 35, 18 },
          { 35, 18, 35, 17 },
          { 35, 17, 36, 17 },
          { 36, 17, 39, 20 },
          { 39, 20, 39, 27 },
          { 39, 27, 34, 32 },
          { 34, 32, 31, 32 },  
          { 31, 32, 29, 30 },
          { 29, 30, 21, 30 }, 
          { 21, 30, 19, 32 }, 
          { 19, 32, 14, 32 }, 
          { 14, 32, 11, 29 },
          { 11, 29, 11, 28 }, 
          { 11, 28, 13, 28 },
          { 13, 28, 13, 26 }, 
          { 13, 26, 12, 26 }, 
          { 12, 26, 12, 18 }, 
          { 12, 18, 15, 16 },
          { 15, 16, 17, 16 }, 
          { 17, 16, 20, 13 },
          { 20, 13, 20, 15 }, 
          { 20, 15, 23, 15 }, 
          { 23, 15, 23, 13 }, 
          { 23, 13, 20, 13 },
          { 20, 13, 20, 12 }, 
          { 20, 12, 22, 12 },
          { 22, 12, 22, 11 }, 
          { 22, 11, 24, 11 }, 
          { 24, 11, 24, 10 }, 
            
            
          { 32, 20, 35, 20 },
          { 35, 20, 35, 22 }, 
          { 35, 22, 32, 22 },  
          { 32, 22, 32, 20 },
          
          { 34, 25, 37, 25 }, 
          { 37, 25, 37, 27 }, 
          { 37, 27, 34, 27 },
          { 34, 27, 34, 25 }, 
            
          { 15, 24, 18, 24 },  
          { 18, 24, 18, 26 }, 
          { 18, 26, 15, 26 }, 
          { 15, 26, 15, 24 },
            
          { 13, 20, 16, 20 },
          { 16, 20, 16, 22 }, 
          { 16, 22, 13, 22 },  
          { 13, 22, 13, 20 }, 
            
          { 18, 16, 21, 16 }, 
          { 21, 16, 21, 18 }, 
          { 21, 18, 22, 18 },
          { 22, 18, 22, 22 }, 
          { 22, 22, 17, 22 },
          { 17, 22, 17, 18 }, 
          { 17, 18, 18, 18 }, 
          { 18, 18, 18, 16 }
        };
        village.loadBoundaryPoints(villageBoundaries);
        village.loadBoundaryLines();
        
        int[][] villageGates = new int[][]{
          { 11, 28, 11, 30 }, 
          { 24, 10, 27, 10 },
        };
        village.loadGatePoints(villageGates);
        
        //for future reference:
//        { 0, 0, 0, 0 },
//          { 0, 0, 0, 0 },
//        { 320, 352, 320, 224 }, 
//          { 320, 352, 320, 224 }, 
//          { 320, 352, 320, 224 }, 
//          { 320, 352, 320, 224 },
//          { 320, 352, 320, 224 }, 
//          { 320, 352, 320, 224 },
        
        // items
        dummy.loadImage();
        
        // add items to Places
        village.addItem(dummy);
        
        // Add all to World map 
        WorldMap worldMap = new WorldMap();
        worldMap.add(village.getName(), village);
        worldMap.add(forestPath_Cave.getName(), forestPath_Cave);
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
