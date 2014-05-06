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
        Place current = wMap.getPlace("Village");     
        mw.swapWorlds(current.sendImage(), current.sendBoundaryPoints(), current.sendGatePoints(), 
                      current.sendGates(), current.sendSpawnPoint());
        System.out.println(current);
    }
    
}

/*
 * xy coords:
 *   x = -1724, y = -911
 * start coords:
 *   x1 = -347.000000, y1 = 485.000000, x2 =  -347.000000, y2 = 549.000000
 * gate coords:
 *   { 21, 38, 21, 40 }, // (672, 1216, 672, 1280);
 */