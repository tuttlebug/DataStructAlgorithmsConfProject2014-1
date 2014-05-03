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

public class TestGamePlay {
    
    private static WorldMap wMap;
    private static MainWindow mw;
    
    public static void main(String[] args) throws IOException {
        wMap = CreatePlaces.createWorldMap();
        mw = new MainWindow();
        Place current = wMap.getPlace("ForestPath_Cave");
        mw.shiftWorld(current.sendImage());
        mw.addBoundaries(current.sendBoundaryPoints(), current.sendSpawnPoint());
    }
    
}