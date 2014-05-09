/**
 * Interacterable Items
 * 
 * Check List:
 * [Ã] Image
 * [Ã] Dimensions (size of sprite)
 * [Ã] Boundaries (i.e., array of lines)
 * 
 * To Add/Implement:
 * [] Performs action when interacted with
 * [] Carriable interface
 * [] become subclass of Interactable
 */

import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.*;
import java.awt.Image;

public class Item extends Interactable {
    
    public Item(String name, String imageFile) {
        super(name, imageFile);
        this.name = name; 
        this.imageFile = imageFile;
        this.box = new Rectangle2D.Double();
    }
    
}