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
 * Village:
 * NAME: Village
 NEIGHBORING AREAS: [ForestPath_Cave, Bridge]
 AVAILABLE ITEMS: [Dummy]
 BACKGROUND: village.png, SPAWNPOINTS: -2399,-736 
 GATES: [current area is Village, next area is ForestPath_Cave
 x1 = -347.000000, y1 = 485.000000, x2 =  -347.000000, y2 = 549.000000
 ]
 Should be:
 353.000000, 310.000000, 353.000000, 374.000000
 
 Gate points: { 21, 38, 21, 40 }, // (672, 1216, 672, 1280);
 
 * Forest Path:
 * When going through a gate:
 * NAME: ForestPath_Cave
 NEIGHBORING AREAS: [Village, Clearing_Cave]
 AVAILABLE ITEMS: []
 BACKGROUND: forestPath_Cave.png, SPAWNPOINTS: -2625,-965 
 GATES: [current area is ForestPath_Cave, next area is Village
 x1 = 49955.000000, y1 = 38976.000000, x2 =  49955.000000, y2 = 41024.000000
]
What they should be:
360.000000, 288.000000, 360.000000, 352.000000
 
 x = -2620, y = -965
 
 Gate Points: { 50, 39, 50, 41 }, // (1600, 1248, 1600, 1312);
 */