import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;

public class TestGamePlay {
    public static MainWindow mw = new MainWindow();
    
    public static Place village = new Place("Village", "village.png");
    public static Place inn = new Place("Inn", "inn.png");
    public static Place home = new Place("Home", "home.png");
    public static Place outside = new Place("Outside", "overworld.png");
    
    public static void main (String[] args) throws IOException {
//        CreatePlaces.createWorlds();
        Scanner prompt = new Scanner(System.in);
//        player.currentlyIn(worldMap.getPlace("Village"));
//        worldMap.getPlace("Village").constructPlace(Color.GREEN);
//        worldMap.getPlace("Cave").constructPlace(Color.GRAY);
        
        // give places their images
        village.loadImage();
        inn.loadImage();
        home.loadImage();
        outside.loadImage();
        // old crappy dialogue
        /*
         System.out.println(player);
         System.out.println("You are in the village, where do you want to go?");
         System.out.println(worldMap.getPlace("Village"));
         String question = prompt.nextLine(); 
         player.currentlyIn(worldMap.getPlace("Village").getPlace(question));
         System.out.println(player);
         System.out.println(worldMap.getPlace("Village").getPlace(question));
         */
        // window
        mw.shiftWorld(village.sendBackground());
        System.out.println("You are in the village, where do you want to go?");
        for (int i = 0; i < 3; i++) {
            String firstQuestion = prompt.nextLine(); 
            prompt(firstQuestion);
        }
    }
    
    public static void prompt(String prompt) throws IOException {
        if (prompt.equals("home")) {
            mw.shiftWorld(home.sendBackground());
        } 
        else if (prompt.equals("inn")) {
            mw.shiftWorld(inn.sendBackground());
        } 
        else if (prompt.equals("outside")) {
            mw.shiftWorld(outside.sendBackground());
        }
        else {
            System.out.println("Doesn't exist");
        }
    }
    
}