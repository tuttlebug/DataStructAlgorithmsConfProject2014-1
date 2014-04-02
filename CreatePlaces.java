/**
 * TESTING CLASS
 * 
 * Worlds:
 * TO DO:
 * add doors to places;
 * add something that affects which places other places have access to depending
 *    on where in the game the character is; 
 *    - chapter check points (?)
 *      - if (chapter = 1) :
 *            firstowns.neighboring areas == (Wilds);
 *    - break spawn points (?)
 *      - make 
 */
import java.awt.*;
import java.util.*;

public class CreatePlaces {
    
    public static Player player = new Player();
    public static WorldMap worldMap = new WorldMap();
    
    public static Place createVillage() {
        int[] villStartPoint = {0, 0};
        Place village = new Place("Village", villStartPoint, player, Color.GREEN);
        int[] startPoint = {5, 10};
        int[] endPoint = {10, 0}; 
        Door homeDoor = new Door("Village Home door", startPoint, endPoint);
        Place home = new Place("Home", homeDoor.endPoint, player, Color.GRAY);
        village.addPlace(home);
        home.addPlace(village);
        worldMap.add(home.getName(), home);
        
        return village;
    }
    
    public static Place createBridge() {
        int[] bridgeStartPoint = {0, 0};
        Place bridge = new Place("Bridge", bridgeStartPoint, player, Color.GREEN);
        
        return bridge;
    }
    
    public static Place createCave() {
        int[] caveStartPoint = {0, 0};
        Place cave = new Place("Cave", caveStartPoint, player, Color.GREEN);
        
        return cave;
    }
    
    public static Place createFirstTown() {
        int[] ftownStartPoint = {0, 0};
        Place firstTown = new Place("First town", ftownStartPoint, player, Color.GREEN);
        int[] marketStartPoint = {8, 10};
        int[] marketEndPoint = {4, 10};
        Door marketDoor = new Door("First Town Market door", marketStartPoint, marketEndPoint); 
        Place market = new Place("Market Place", marketDoor.endPoint, player, Color.GREEN);
        firstTown.addPlace(market);
        worldMap.add(market.getName(), market);
        
        return firstTown;
    }
    
    public static Place createWilds() {
        int[] wildsStartPoint = {0, 0};
        Place wilds = new Place("Wilds", wildsStartPoint, player, Color.GREEN);
        return wilds;
        
        // create places
//        int[] villSpawnPoint = {0, 0};
//        Place village = new Place("Village", villSpawnPoint, player);
//        int[] bridgeSpawnPoint = {0, 0};
//        Place bridge = new Place("Bridge", bridgeSpawnPoint, player);
//        int[] caveSpawnPoint = {0, 0};
//        Place cave = new Place("Cave", caveSpawnPoint, player);
//        int[] ftownSpawnPoint = {0, 0};
//        Place foreignTown = new Place("Foreign town", ftownSpawnPoint, player);
//        int[] wildsSpawnPoint = {0, 0};
//        Place wilds = new Place("Wilds", wildsSpawnPoint, player);
//        int[] shadowSpawnPoint = {0, 0};
//        Place shadowPath = new Place("Shadow Path", shadowSpawnPoint, player);
//        int[] templeSpawnPoint = {0, 0};
//        Place temple = new Place("Temple", templeSpawnPoint, player);
    }
    
    public static Place createShadowPath() {
        int[] shadowStartPoint = {0, 0};
        Place shadowPath = new Place("Shadow Path", shadowStartPoint, player, Color.GREEN);
        
        // create backgrounds
//        village.constructPlace(Color.GREEN);
//        cave.constructPlace(Color.GRAY);
        return shadowPath;
    }
    
    public static Place createTemple() {
        int[] templeStartPoint = {0, 0};
        Place temple = new Place("Temple", templeStartPoint, player, Color.GREEN);
        
        return temple;
    }
    
    public static void createWorlds() {
        // create areas
        Place village = createVillage();
        Place bridge = createBridge();
        Place cave = createCave();
        Place firstTown = createFirstTown();
        Place wilds = createWilds();
        Place shadowPath = createShadowPath();
        Place temple = createTemple();
        
        // add accessible areas
        village.addPlace(bridge);
        bridge.addPlace(village);
        bridge.addPlace(cave);
        cave.addPlace(bridge);
        firstTown.addPlace(bridge);
        firstTown.addPlace(wilds);
        wilds.addPlace(firstTown);
        firstTown.addPlace(bridge);
        firstTown.addPlace(wilds);
        wilds.addPlace(firstTown);
        wilds.addPlace(shadowPath);
        shadowPath.addPlace(wilds);
        shadowPath.addPlace(temple);
        temple.addPlace(shadowPath);
        
        // Add all to World map 
        worldMap.add(village.getName(), village);
        worldMap.add(cave.getName(), cave);
        worldMap.add(bridge.getName(), bridge);
        worldMap.add(firstTown.getName(), firstTown);
        worldMap.add(wilds.getName(), wilds);
        worldMap.add(shadowPath.getName(), shadowPath);
        worldMap.add(temple.getName(), temple);
        
        System.out.println(village);
        System.out.println(cave);
        System.out.println(bridge);
        System.out.println(firstTown);
        System.out.println(wilds);
        System.out.println(shadowPath);
        System.out.println(temple);
        
//        return worldMap;
    }
    
    /**
     * background will become an int array of sprite locations
     */
    public static void buildWorld(String name) {
        worldMap.getPlace(name).constructPlace();
    }
    
    public static void main (String[] args) {
        Scanner prompt = new Scanner(System.in);
        createWorlds();
//        System.out.println(worldMap);
        player.currentlyIn(worldMap.getPlace("Village"));
        buildWorld(player.currentArea.getName());
        
        WorldMap worldMap = new WorldMap();
        worldMap.add(village.name, village);
//            worldMap.add(cave.name
        // crappy dialogue
        System.out.println(player);
        System.out.println("You are in the village, where do you want to go?");
        System.out.println(worldMap.getPlace("Village"));
        String question = prompt.nextLine(); 
        player.currentlyIn(worldMap.getPlace("Village").getPlace(question));
        buildWorld(player.currentArea.getName());
        System.out.println(player);
        System.out.println(worldMap.getPlace("Village").getPlace(question));
    }
}
