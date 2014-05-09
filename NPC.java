/**
 * To add/Implement:
 * [] Need to have collision detection
 * [] array of sprites
 */

import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.*;
import java.awt.Image;

public class NPC extends Interactable {
    
    Image[] sprites;
    
    public NPC(String name, String imageFile) {
        super(name, imageFile);
        this.name = name;
        this.imageFile = imageFile;
        this.box = new Rectangle2D.Double();
        this.sprites = new Image[11];
    }
    
    /*
    public String speak() {
        
    }
    */
}