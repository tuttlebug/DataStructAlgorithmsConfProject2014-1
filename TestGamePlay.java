/**
 * TESTING CLASS
 */

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

/**
 * VERSION 1
 * for switching images
 */
public class TestGamePlay {
 
    public static MainWindow mw = new MainWindow();
    
    public static Place village = new Place("Village", "village.png");
    public static Place inn = new Place("Inn", "inn.png");
    public static Place home = new Place("Home", "home.png");
    public static Place outside = new Place("Outside", "overworld.png");
    
    public static Player tome = new Player();
    
    public static void main (String[] args) throws IOException {
        Scanner prompt = new Scanner(System.in);
        
        // give places their images
        village.loadImage();
        inn.loadImage();
        home.loadImage();
        outside.loadImage();
        
        // window and player
        tome.loadImage();
        mw.shiftWorld(village.sendImage());
        mw.addPlayer(tome.sendImage());
        
        System.out.println("You are in the village, where do you want to go?");
        for (int i = 0; i < 3; i++) {
            String firstQuestion = prompt.nextLine(); 
            prompt(firstQuestion);
        }
    }
    
    public static void prompt(String prompt) throws IOException {
        if (prompt.equals("home")) {
            mw.shiftWorld(home.sendImage());
        } 
        else if (prompt.equals("inn")) {
            mw.shiftWorld(inn.sendImage());
        } 
        else if (prompt.equals("outside")) {
            mw.shiftWorld(outside.sendImage());
        }
        else {
            System.out.println("Doesn't exist");
        }
    }
    
}



/**
 * VERSION 2
 */
/*
public class TestGamePlay {
    
    private static WorldMap wMap;
    private static MainWindow mw;
    
    public static void main(String[] args) throws IOException {
        wMap = CreatePlaces.createWorldMap();
        mw = new MainWindow();
        
        System.out.println(wMap);
        Place current = wMap.getPlace("Village");
        mw.shiftWorld(current.sendImage());
    }
    
}
*/