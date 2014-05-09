/**
 * Super class for: Item, NPC, Enemy
 */

import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.*;
import java.awt.Image;

public class Interactable implements LoadImage {
    
    // instance variables
    protected String name;
    protected String imageFile;
    public Image image;
    protected double width, height;
    public Rectangle2D.Double box;
    protected int[] coords;
    
    // constructor
    public Interactable(String name, String imageFile) {
        this.name = name;
        this.imageFile = imageFile; 
        this.box = new Rectangle2D.Double();
    }
    
    // ------------ Image ------------ \\
    public void loadImage() throws IOException {
        this.image = ImageIO.read(new File(this.imageFile));
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);
    }
    
    
    // ------------ Box ------------ \\
    public void loadBox(double x, double y) {
        this.box.setRect(x,  y, this.width, this.height);
        this.coords = new int[] {
            (int) x, (int) y
        };
    }
    
    // ------------ Width & Height & Coords ------------ \\
    public double getWidth() {
        return this.width;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public int getX() {
        return this.coords[0];
    }
    
    public int getY() {
        return this.coords[1];
    }
    
    // ------------ Name ------------ \\
    public String getName() {
        return this.name;
    }
    
    // ------------ toString ------------ \\
    public String toString() {
        return String.format("NAME: %s, WIDTH: %f, HEIGHT: %f\n", this.name, this.width, this.height);
    }
}