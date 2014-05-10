import java.util.* ;
import java.io.*;
import javax.swing.*;    // JPanel
import java.awt.*;       // Graphics, Graphics2D, Color
import java.awt.geom.*;  // Ellipse2D
import java.awt.event.*; // MouseListener, MouseMotionListener, MouseEvent, KeyListener, KeyEvent

/**
 * CHECK-LIST:
 * []
 * To Add/Implement:
 * [] Move as a subclass to / put in Place
 */

public class BoundaryLines extends JPanel {
    
    private ArrayList<Line2D.Double> lines;
    private Color r;
    private Color b;
    private Color g;
    private Color p;
    private Color o;
    private Color img;
    private Gate crossedGate;
    private Item touchedItem;
    private Enemy touchedEnemy;
    private NPC speakingNPC;
    private Place place; 
    // flags for dictating which directions are blocked off
    public boolean moveL = true;
    public boolean moveR = true;
    public boolean moveU = true;
    public boolean moveD = true;
//    // flag for dictating whether Tome can attack
//    public boolean canAttack = true;

    // ------------ Constructor ------------ \\
    
    // constructor for places with items
     public BoundaryLines(Place place, int offsetX, int offsetY) {
         this.place = place;
         this.createLines(place, offsetX, offsetY);
         this.r = new Color(255, 0, 0, 255);
         this.b = new Color(0, 0, 255, 255);
         this.g = new Color(0, 255, 0, 255);
         this.p = new Color(100, 0, 100, 255);
         this.o = new Color(250, 115, 47, 255);
         this.img = new Color(0, 0, 0, 255);
    }

    // ------------ Paint ------------ \\
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(this.r);
        // draw lines
        for (Line2D.Double line : this.lines) {
            pen.draw(line);
        }
        // draw gates
        pen.setColor(this.b);
        for (Gate gate : this.place.sendGates()) {
            pen.draw(gate.line);
        }
        // draw items
        if (this.place.hasItems()) {
            pen.setColor(this.g);
            for (Item item : this.place.sendItems()) {
                pen.draw(item.box);
                pen.setColor(this.img);
                pen.drawImage(item.image, item.getX(), item.getY(), null); 
            }
        }
        // draw enemies
        if (this.place.hasEnemies()) {
            pen.setColor(this.p);
            for (Enemy enemy : this.place.sendEnemies()) {
                pen.draw(enemy.box);
                pen.setColor(this.img);
                pen.drawImage(enemy.image, enemy.getX(), enemy.getY(), null); 
            }
        }
        // draw npcs
        if (this.place.hasNPCs()) {
            pen.setColor(this.o);
            for (NPC npc : this.place.sendNPCs()) {
                pen.draw(npc.box);
                pen.setColor(this.img);
                pen.drawImage(npc.image, npc.getX() + 10 , npc.getY() + 10, null); 
                System.out.printf("npc x = %d, npc y = %d\n", npc.getX(), npc.getY()); 
            }
        }
        // makes background transparent
        g.setColor(getBackground());
    }
 
    // ------------ Movement ------------ \\
    // move shapes across screen
    public void moveLines(int dx, int dy) {
        // move lines
        for (Line2D.Double line : this.lines) {
            line.setLine(line.getX1() + dx, 
                         line.getY1() + dy, 
                         line.getX2() + dx, 
                         line.getY2() + dy);
        }
        // move gates
        for (Gate gate : this.place.sendGates()) {
            gate.loadLine(gate.line.getX1() + dx, 
                          gate.line.getY1() + dy, 
                          gate.line.getX2() + dx, 
                          gate.line.getY2() + dy);
        }
        // move items
        if (this.place.hasItems()) {
            for (Item item : this.place.sendItems()) {
                item.loadBox(item.box.getX() + dx, 
                             item.box.getY() + dy);
            }
        }
        // move enemies
        if (this.place.hasEnemies()) {
            for (Enemy enemy : this.place.sendEnemies()) {
                enemy.loadBox(enemy.box.getX() + dx, 
                             enemy.box.getY() + dy);
            }
        }
        // move npcs
        if (this.place.hasNPCs()) {
            for (NPC npc : this.place.sendNPCs()) {
                npc.loadBox(npc.box.getX() + dx, 
                             npc.box.getY() + dy);
            }
        }
    }
    
    // ------------ Collision Detection ------------ \\
    // tests if a Rectangle intersected any of the boundary lines
    public boolean boundaryCrossed(Rectangle2D.Double box) {
        for (Line2D.Double line : this.lines) {
            if (box.intersectsLine(line)) {
                return true;
            }
        }
        return false;
    }
   
    // tests if a Rectangle intersected any of the gates
    public boolean gateCrossed(Rectangle2D.Double box) {
        for (Gate gate : this.place.sendGates()) {
            if (box.intersectsLine(gate.line)) {
                this.crossedGate = gate;
                return true;
            }
        }
        return false;
    }
    
    // tests if a Rectangle intersected an item's box
    public boolean itemTouched(Rectangle2D.Double box) {
        for (int i = 0; i < this.place.sendItems().size(); i++) {
            Item item = this.place.sendItems().get(i);
            if (box.intersects(item.box.getX(),
                               item.box.getY(),
                               item.box.getWidth(),
                               item.box.getHeight())) {
                this.touchedItem = item;
                this.place.removeItem(item);
                return true;
            }
        }    
        return false;
    }
    
    // tests if a Rectangle intersected an enemy's box
    public boolean enemyTouched(Rectangle2D.Double box) {
        for (int i = 0; i < this.place.sendEnemies().size(); i++) {
            Enemy enemy = this.place.sendEnemies().get(i);
            if (box.intersects(enemy.box.getX(),
                               enemy.box.getY(),
                               enemy.box.getWidth(),
                               enemy.box.getHeight())) {
                this.touchedEnemy = enemy;
                return true;
            }
        }    
        return false;
    }
    
    // tests if a Rectangle intersected an npc's box
    public boolean npcSpeaking(Rectangle2D.Double box) {
        for (int i = 0; i < this.place.sendNPCs().size(); i++) {
            NPC npc = this.place.sendNPCs().get(i);
            if (box.intersects(npc.box.getX(),
                               npc.box.getY(),
                               npc.box.getWidth(),
                               npc.box.getHeight())) {
                this.speakingNPC = npc;
                return true;
            }
        }    
        return false;
    }
    
    public Gate getCrossedGate() {
        return this.crossedGate;
    }
    
    public Item getTouchedItem() {
        return this.touchedItem;
    }
    
    public Enemy getTouchedEnemy() {
        return this.touchedEnemy;
    }
    
    public NPC getSpeakingNPC() {
        return this.speakingNPC;
    }
    
    // ------------ Remove Shapes ------------ \\
    public void removeLines() {
        
    }

    // ------------ Build Shapes ------------ \\    
    // method for place as parameter
    public void createLines(Place place, int offsetX, int offsetY) {
        // sets the background and lines invisible
        setOpaque(false);    
        
        this.place = place;
        
        // create boundary lines
        this.lines = new ArrayList<Line2D.Double>();
        for (int i = 0; i < this.place.sendBoundaryPoints().length; i++) {
            for (int j = 3; j < this.place.sendBoundaryPoints()[i].length; j+=3) {
                Line2D.Double newLine = new Line2D.Double(
                                                          this.place.sendBoundaryPoints()[i][j-3] - offsetX,
                                                          this.place.sendBoundaryPoints()[i][j-2] - offsetY,
                                                          this.place.sendBoundaryPoints()[i][j-1] - offsetX,
                                                          this.place.sendBoundaryPoints()[i][j] - offsetY
                                                         );
                this.lines.add(newLine);
            }
        }
    }
    
  
}


// PRINT STATEMENTS
//            x1 -= dx;
//            y1 -= dy;
//            System.out.printf("fake x1 = %d, fake y1 = %d\n", x1, y1);
//            System.out.println(gate);
//                System.out.printf("boundary hit: %f, %f, %f, %f\n",
//                                  this.lines.get(0).getX1(),
//                                  this.lines.get(0).getY1(),
//                                  this.lines.get(0).getX2(),
//                                  this.lines.get(0).getY2());
//                System.out.printf("gate hit: %f, %f, %f, %f\n",
//                                  this.gates.get(0).line.getX1(),
//                                  this.gates.get(0).line.getY1(),
//                                  this.gates.get(0).line.getX2(),
//                                  this.gates.get(0).line.getY2());
//                System.out.printf("%d = %s\n", gateIterator, this.gates.get(gateIterator));
//                System.out.printf("Points without offset:\nx1 = %d, y1 = %d, x2 = %d, y2 = %d\n",
//                                  this.gatePoints[i][j-3],
//                                  this.gatePoints[i][j-2],
//                                  this.gatePoints[i][j-1],
//                                  this.gatePoints[i][j]);
//                System.out.printf("Points with offset:\nx1 = %d, y1 = %d, x2 = %d, y2 = %d\n",
//                                  this.gatePoints[i][j-3] - offsetX,
//                                  this.gatePoints[i][j-2] - offsetY,
//                                  this.gatePoints[i][j-1] - offsetX,
//                                  this.gatePoints[i][j] - offsetY);
//                System.out.printf("%d = %s\n", gateIterator, this.gates.get(gateIterator));
//