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
        int[] caveSpawnPoint = {0, 0};
        int[] caveSize = {600, 300};
        
        // Village 
        Place village = new Place("Village", villSpawnPoint);
        village.constructPlace(villSize, Color.GREEN);
        
        // Cave
        Place cave = new Place("Cave", caveSpawnPoint);
        cave.constructPlace(caveSize, Color.GRAY);
    }
}