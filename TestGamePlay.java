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
 - 672 - (-347) = 1019
 - 1216 - 485 = 731
 - 672 - (-347) = 1019
 - 1280 - 549 = 731
 
 WITHOUT OFFSET
 NAME: Village
NEIGHBORING AREAS: [ForestPath_Cave, Bridge]
AVAILABLE ITEMS: [Dummy]
BACKGROUND: village.png, SPAWNPOINTS: -2399,-736 
GATES: [current area is Village, next area is ForestPath_Cave
x1 = 103.000000, y1 = 770.000000, x2 =  103.000000, y2 = 834.000000
start point = [-1724, -911], endpoint = [-2620, -965]
]

--------------3
0 = current area is Village, next area is ForestPath_Cave
x1 = 0.000000, y1 = 0.000000, x2 =  0.000000, y2 = 0.000000
start point = [-1724, -911], endpoint = [-2620, -965]

Points without offset:
x1 = 672, y1 = 1216, x2 = 672, y2 = 1280
Points with offset:
x1 = 222, y1 = 931, x2 = 222, y2 = 995
0 = current area is Village, next area is ForestPath_Cave
x1 = 222.000000, y1 = 931.000000, x2 =  222.000000, y2 = 995.000000
start point = [-1724, -911], endpoint = [-2620, -965]


NAME: Village
NEIGHBORING AREAS: [ForestPath_Cave, Bridge]
AVAILABLE ITEMS: [Dummy]
BACKGROUND: village.png, SPAWNPOINTS: -2399,-736 
GATES: [current area is Village, next area is ForestPath_Cave
x1 = -347.000000, y1 = 485.000000, x2 =  -347.000000, y2 = 549.000000
start point = [-1724, -911], endpoint = [-2620, -965]
]
GATEPOINTS BEFORE: i = [21, 38, 21, 40], j =  21 38 21 40 
GATEPOINTS = 672 1216 672 1280 672 1216 672 1280 
------------->>>>>>>>>>>
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
 - 1600 - 49955 = -48355
 - 1248 - 38976 = -37728
 - 1600 - 49955 = -48355
 - 1312 - 41024 = -39712
 
 WITHOUT OFFSET
 NAME: ForestPath_Cave
NEIGHBORING AREAS: [Village, Clearing_Cave]
AVAILABLE ITEMS: []
BACKGROUND: forestPath_Cave.png, SPAWNPOINTS: -2620,-965 
GATES: [current area is ForestPath_Cave, next area is Village
x1 = 50410.000000, y1 = 39261.000000, x2 =  50410.000000, y2 = 41309.000000
start point = [-2620, -965], endpoint = [-1724, -911]
]

x = -2620, y = -965
x = -2645, y = -965

--------------3

0 = current area is ForestPath_Cave, next area is Village
x1 = 0.000000, y1 = 0.000000, x2 =  0.000000, y2 = 0.000000
start point = [-2620, -965], endpoint = [-1724, -911]

Points without offset:
x1 = 51200, y1 = 39936, x2 = 51200, y2 = 41984
Points with offset:
x1 = 50750, y1 = 39651, x2 = 50750, y2 = 41699
0 = current area is ForestPath_Cave, next area is Village
x1 = 50750.000000, y1 = 39651.000000, x2 =  50750.000000, y2 = 41699.000000
start point = [-2620, -965], endpoint = [-1724, -911]


NAME: ForestPath_Cave
NEIGHBORING AREAS: [Village, Clearing_Cave]
AVAILABLE ITEMS: []
BACKGROUND: forestPath_Cave.png, SPAWNPOINTS: -2620,-965 
GATES: [current area is ForestPath_Cave, next area is Village
x1 = 49960.000000, y1 = 38976.000000, x2 =  49960.000000, y2 = 41024.000000
start point = [-2620, -965], endpoint = [-1724, -911]
]
GATEPOINTS BEFORE: i = [50, 39, 50, 41], j =  50 39 50 41 i = [1600, 1248, 1600, 1312], j =  1600 1248 1600 1312 
GATEPOINTS = 51200 39936 51200 41984 51200 39936 51200 41984 

 */