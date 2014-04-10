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
    
    private static Place village = new Place("Village", "village.png");
    private static Place home = new Place("Home", "home.png");
    private static Place bridge = new Place("Bridge", "bridge.png");
    private static Place cave = new Place("Cave", "cave.png");
    private static Place market = new Place("Market", "inn.png");
    private static Place firstTown = new Place("First Town", "firstTown.png");
    private static Place wilds = new Place("Wilds", "wilds.png");
    private static Place shadowPath = new Place("ShadowPath", "shadowPath.png");
    private static Place temple = new Place("Temple", "temple.png");
    
    // create a map of all Places
    public static WorldMap createWorldMap() throws IOException {
        // add accessible areas
        village.addPlace(bridge);
        village.addPlace(home);
        bridge.addPlace(village);
        bridge.addPlace(cave);
        cave.addPlace(bridge);
        firstTown.addPlace(bridge);
        firstTown.addPlace(wilds);
        firstTown.addPlace(market);
        wilds.addPlace(firstTown);
        shadowPath.addPlace(wilds);
        shadowPath.addPlace(temple);
        temple.addPlace(shadowPath);
        
        // Load images
        village.loadImage();
        home.loadImage();
        bridge.loadImage();
        cave.loadImage();
        firstTown.loadImage();
        wilds.loadImage();
        shadowPath.loadImage();
        temple.loadImage();
        
        // Add all to World map 
        WorldMap worldMap = new WorldMap();
        worldMap.add(village.getName(), village);
        worldMap.add(cave.getName(), cave);
        worldMap.add(bridge.getName(), bridge);
        worldMap.add(firstTown.getName(), firstTown);
        worldMap.add(wilds.getName(), wilds);
        worldMap.add(shadowPath.getName(), shadowPath);
        worldMap.add(temple.getName(), temple);
        
        return worldMap;
    }
    
    // create a map of all Items
    public static ItemMap createItemMap() throws IOException {
        
    }
}
