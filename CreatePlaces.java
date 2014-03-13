import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Testing class
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
        
        // create village and cave
        int[] villSpawnPoint = {0, 0};
        Place village = new Place("Village", villSpawnPoint, player);
        int[] caveSpawnPoint = {0, 0};
        Place cave = new Place("Cave", caveSpawnPoint, player);
        
        // create backgrounds
        village.constructPlace(Color.GREEN);
//        cave.constructPlace(Color.GRAY);
        // add accessible areas
        village.addPlace(cave);
        cave.addPlace(village);
        
        System.out.println(village);
        System.out.println(cave);
    }
}
