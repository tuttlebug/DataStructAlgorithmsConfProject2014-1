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
        Place current = wMap.getPlace("Village");  
        mw = new MainWindow(current);
//        mw = new MainWindow();
        mw.swapWorlds();
    }
    
}

/**
 * 353 - 222 = 131
 * 310 - 931 = -621
 * 353 - 222 = 131
 * 374 - 995 = -621
 * 
NAME: Village
NEIGHBORING AREAS: [ForestPath_Cave, Bridge]
AVAILABLE ITEMS: []
BACKGROUND: village.png, SPAWNPOINTS: -2399,-736 
GATES: [next area is ForestPath_Cave
x1 = 222.000000, y1 = 931.000000, x2 =  222.000000, y2 = 995.000000
endpoint = [-2620, -965]
]
GATEPOINTS BEFORE: i = [21, 38, 21, 40], j =  21 38 21 40 
GATEPOINTS: 672 1216 672 1280 672 1216 672 1280  



--------2
NAME: Village
NEIGHBORING AREAS: [ForestPath_Cave, Bridge]
AVAILABLE ITEMS: []
BACKGROUND: village.png, SPAWNPOINTS: -1719,-911 
GATES: [next area is ForestPath_Cave
x1 = 353.000000, y1 = 310.000000, x2 =  353.000000, y2 = 374.000000
endpoint = [-2620, -965]
]
GATEPOINTS BEFORE: i = [21, 38, 21, 40], j =  21 38 21 40 
GATEPOINTS: 672 1216 672 1280 672 1216 672 1280 672 1216 672 1280 

 */    