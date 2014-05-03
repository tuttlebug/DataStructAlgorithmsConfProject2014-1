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
        
        // ------------- Load boundaries, gate points, and spawn points ------------- //
        // Village
        int[][] villageBoundaries = new int[][]{
          { 34, 20, 37, 20 },
          { 37, 20, 37, 21 },
          { 37, 21, 38, 21 },
          { 38, 21, 41, 24 },
          { 41, 24, 41, 25 },
          { 41, 25, 44, 25 },
          { 44, 25, 44, 26 },
          { 44, 26, 42, 26 },
          { 42, 26, 42, 28 },
          { 42, 28, 45, 28 },
          { 45, 28, 45, 27 },
          { 45, 27, 46, 27 },
          { 46, 27, 49, 20 },
          { 49, 30, 49, 37 },
          { 49, 37, 44, 42 },
          { 44, 42, 41, 42 },  
          { 41, 42, 39, 40 },
          { 39, 40, 31, 40 }, 
          { 31, 40, 29, 42 }, 
          { 29, 42, 24, 42 }, 
          { 24, 42, 21, 39 },
          { 21, 39, 21, 38 }, 
          { 21, 38, 23, 38 },
          { 23, 38, 23, 36 }, 
          { 23, 36, 22, 36 }, 
          { 22, 36, 22, 28 }, 
          { 22, 28, 25, 26 },
          { 25, 26, 27, 26 }, 
          { 27, 26, 30, 23 },
          { 30, 23, 30, 25 }, 
          { 30, 25, 33, 25 }, 
          { 33, 25, 33, 23 }, 
          { 33, 23, 30, 23 },
          { 30, 23, 30, 22 }, 
          { 30, 22, 32, 22 },
          { 32, 22, 32, 21 }, 
          { 32, 21, 34, 21 }, 
          { 34, 21, 34, 20 }, 
            
            
          { 42, 30, 45, 30 },
          { 45, 30, 45, 32 }, 
          { 45, 32, 42, 32 },  
          { 42, 32, 42, 30 },
          
          { 44, 35, 47, 35 }, 
          { 47, 35, 47, 37 }, 
          { 47, 37, 44, 37 },
          { 44, 37, 44, 35 }, 
            
          { 25, 34, 28, 34 },  
          { 28, 34, 28, 36 }, 
          { 28, 36, 25, 36 }, 
          { 25, 36, 25, 34 },
            
          { 23, 30, 26, 30 },
          { 26, 30, 26, 32 }, 
          { 26, 32, 23, 32 },  
          { 23, 32, 23, 30 }, 
            
          { 28, 26, 31, 26 }, 
          { 31, 26, 31, 28 }, 
          { 31, 28, 32, 28 },
          { 32, 28, 32, 32 }, 
          { 32, 32, 27, 32 },
          { 27, 32, 27, 28 }, 
          { 27, 28, 28, 28 }, 
          { 28, 28, 28, 26 }
        };
        village.loadBoundaryPoints(villageBoundaries);
        // gates
        int[][] villageGates = new int[][]{
          { 21, 38, 21, 40 }, 
          { 34, 20, 37, 20 },
        };
        village.loadGatePoints(villageGates);
        // spawnPoints 
        village.loadSpawnPoint(43, 33);
        
        // ForestPath_Cave
        int[][] forestPath_CaveBoundaries = new int[][]{
          { 51, 41, 41, 41 }, 
          { 41, 41, 41, 37 }, 
          { 41, 37, 38, 37 }, 
          { 38, 37, 38, 35 },
          { 38, 35, 37, 35 }, 
          { 37, 35, 37, 34 },
          { 37, 34, 32, 34 }, 
          { 32, 34, 32, 45 }, 
          { 32, 45, 25, 45 }, 
          { 25, 45, 25, 43 },
          { 25, 43, 23, 43 }, 
          { 23, 43, 23, 41 },
          { 23, 41, 19, 41 }, 
          { 19, 41, 19, 39 }, 
          { 19, 39, 25, 39 }, 
          { 25, 39, 25, 41 },
          { 25, 41, 27, 41 }, 
          { 27, 41, 27, 43 },  
          { 27, 43, 30, 43 }, 
          { 30, 43, 30, 32 }, 
          { 30, 32, 42, 32 }, 
          { 42, 32, 42, 30 },
          { 42, 30, 46, 30 }, 
          { 46, 30, 46, 33 },   
          { 46, 33, 43, 33 }, 
          { 43, 33, 43, 39 }, 
          { 43, 39, 51, 39 },
          { 41, 39, 51, 41 }
        };
        forestPath_Cave.loadBoundaryPoints(forestPath_CaveBoundaries);
        // gates
        int[][] forestPath_CaveGates = new int[][]{
          { 19, 39, 19, 41 }, 
          { 50, 39, 50, 41 },
        };
        forestPath_Cave.loadGatePoints(forestPath_CaveGates);
        // spawn Point
        forestPath_Cave.loadSpawnPoint(20, 33);
        
        //clearing_Cave
        int[][] clearing_CaveBoundaries = new int[][]{
          { 43, 32, 42, 32 }, 
          { 42, 32, 42, 40 }, 
          { 42, 40, 40, 42 }, 
          { 40, 42, 29, 42 },
          { 29, 42, 29, 40 }, 
          { 29, 40, 31, 38 },
          { 31, 38, 31, 37 }, 
          { 31, 35, 31, 34 }, 
          { 31, 34, 29, 32 },
          { 29, 32, 29, 31 }, 
          { 29, 31, 30, 31 },
          { 30, 31, 30, 30 }, 
          { 30, 30, 33, 27 }, 
          { 33, 27, 40, 27 },
          { 40, 27, 42, 29 }, 
          { 42, 29, 42, 30 },
          { 42, 30, 43, 30 }
        };
        clearing_Cave.loadBoundaryPoints(clearing_CaveBoundaries);
        // gates
        int[][] clearing_CaveGates = new int[][]{
          { 43, 30, 43, 32 }, 
          { 31, 35, 31, 37 },
        };
        clearing_Cave.loadGatePoints(clearing_CaveGates);
        // spawn Point
        clearing_Cave.loadSpawnPoint(41, 31);
        

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
