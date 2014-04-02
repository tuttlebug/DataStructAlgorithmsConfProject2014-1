import java.util.*;
import javax.swing.*;
import java.awt.*;

public class TestGamePlay {
    
    public static void main (String[] args) {
        CreatePlaces.createWorlds();
        Scanner prompt = new Scanner(System.in);
//        WorldMap worldMap = createWorlds();
//        System.out.println(worldMap);
        player.currentlyIn(worldMap.getPlace("Village"));
        worldMap.getPlace("Village").constructPlace(Color.GREEN);
        worldMap.getPlace("Cave").constructPlace(Color.GRAY);
        
        // crappy dialogue
        System.out.println(player);
        System.out.println("You are in the village, where do you want to go?");
        System.out.println(worldMap.getPlace("Village"));
        String question = prompt.nextLine(); 
        player.currentlyIn(worldMap.getPlace("Village").getPlace(question));
        System.out.println(player);
        System.out.println(worldMap.getPlace("Village").getPlace(question));
    }
    
}