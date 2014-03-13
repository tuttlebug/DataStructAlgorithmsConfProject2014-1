import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * TESTING CLASS
 * 
 * Worlds:
 *      Cave [Village]
 *      Village [Cave, Bridge]
 *      Foreign town [Bridge, Wilds]
 *      Wilds [Foreign Town, Shadow Path]
 *      Shadow Path [Wilds, Temple]
 */
public class CreatePlaces {
    
    public static void main (String[] args) {
        // create Player
        Player player = new Player();
        
        // create places
        int[] villSpawnPoint = {0, 0};
        Place village = new Place("Village", villSpawnPoint, player);
        int[] bridgeSpawnPoint = {0, 0};
        Place bridge = new Place("Bridge", bridgeSpawnPoint, player);
        int[] caveSpawnPoint = {0, 0};
        Place cave = new Place("Cave", caveSpawnPoint, player);
        int[] ftownSpawnPoint = {0, 0};
        Place foreignTown = new Place("Foreign town", ftownSpawnPoint, player);
        int[] wildsSpawnPoint = {0, 0};
        Place wilds = new Place("Wilds", wildsSpawnPoint, player);
        int[] shadowSpawnPoint = {0, 0};
        Place shadowPath = new Place("Shadow Path", shadowSpawnPoint, player);
        int[] templeSpawnPoint = {0, 0};
        Place temple = new Place("Temple", templeSpawnPoint, player);
        
        // create backgrounds
//        village.constructPlace(Color.GREEN);
//        cave.constructPlace(Color.GRAY);
        
        // add accessible areas
        village.addPlace(bridge);
        bridge.addPlace(village);
        bridge.addPlace(cave);
        cave.addPlace(bridge);
        foreignTown.addPlace(bridge);
        foreignTown.addPlace(wilds);
        wilds.addPlace(foreignTown);
        wilds.addPlace(shadowPath);
        shadowPath.addPlace(wilds);
        shadowPath.addPlace(temple);
        temple.addPlace(shadowPath);
        
        
        System.out.println(village);
        System.out.println(cave);
        System.out.println(bridge);
        System.out.println(foreignTown);
        System.out.println(wilds);
        System.out.println(shadowPath);
        System.out.println(temple);
        
        
        WorldMap worldMap = new WorldMap();
        worldMap.add(village.name, village);
        worldMap.add(cave.name
    }
}
