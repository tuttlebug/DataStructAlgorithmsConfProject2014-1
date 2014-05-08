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
 * []
 */

// version 1
/*
public class ItemPanel extends JPanel {
    
    private ArrayList<Item> items;
    private Place currentPlace;
    
    // only used in the MainWindow constructor
    public ItemPanel() {
        setOpaque(false);
    }
    
    public ItemPanel(Place place, ArrayList<Item> items) {
//        this.currentPlace = place;
//        this.items = place.sendItems();
//        this.items = items;
        this.drawItems(place, items);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.GREEN);
        if (this.items.size() > 0) {
            for (Item item : this.items) {
                pen.drawImage(item.image, item.getX(), item.getY(), null);
            }
        }
        // makes background transparent
        g.setColor(getBackground());
    }
    
    // move boxes
//    public void moveLines(int dx, int dy) {
//        for (Item item : this.items) {
//        }
//    }
    
    public void drawItems(Place place, ArrayList<Item> items) {
        this.currentPlace = place;
        this.items = items;
    }
}
*/

public class ItemPanel extends JPanel {
    
    private Place place;
    
    public ItemPanel(Place place) {
        setOpaque(false);
        this.place = place;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setColor(Color.GRAY);
        if (this.place.hasItems()) {
            for (Item item : this.place.sendItems()) {
//                pen.draw(item.box);
                System.out.println("DRAWING");
                pen.drawImage(item.image, item.getX(), item.getY(), null);
                System.out.printf("image x1 = %d, box y1 = %d\n", item.getX(), item.getY());
            }
//            this.drawImages(pen);
        }
        // makes background transparent
        g.setColor(getBackground());
    }
    
    public void changePlace(Place place) {
        this.place = place;
    }
    
//    public void drawImages(Graphics2D pen) {
////        pen.setColor(Color.GREEN);
//        for (Item item : this.items) {
//            pen.drawImage(item.image, item.getX(), item.getY(), null);
//        }
//    }
    
    // move boxes
//    public void moveLines(int dx, int dy) {
//        for (Item item : this.items) {
//        }
//    }
}