import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Testing class
 */
public class createPlaces {
    
    public static void main (String[] args) {
        // create village and cave
        int[] villSpawnPoint = {0, 0};
        int[] villSize = {700, 600};
        Place village = new Place("Village", villSpawnPoint);
        int[] caveSpawnPoint = {0, 0};
        int[] caveSize = {600, 300};
        Place cave = new Place("Cave", caveSpawnPoint);
        
        // create backgrounds
//        village.constructPlace(villSize, Color.GREEN);
//        cav e.constructPlace(caveSize, Color.GRAY);
        // add accessible areas
        village.addPlace(cave);
        cave.addPlace(village);
        
        System.out.println(village);
        System.out.println(cave);
    }
}