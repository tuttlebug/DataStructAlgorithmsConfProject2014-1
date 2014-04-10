/**
 * Interacterable Items
 * 
 * Check List:
 * [Ã] Image
 * [Ã] Dimensions
 * [] Boundaries
 * [] Reference to place that it is in
 * 
 * To Add/Implement:
 * [] Performs action when interacted with
 * [] Carriable interface
 */

import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Item implements LoadImage {
    
    // instance variables
    private String name;
    private String imageFile;
    private ImageIcon sprite;
    private int width, height;
    
    // constructor
    public Item(String name, String imageFile) {
        this.name = name;
        this.imageFile = imageFile; 
    }
    
    // methods
    public void loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(this.imageFile));
        this.sprite = new ImageIcon(image);
        this.width = this.sprite.getIconWidth();
        this.height = this.sprite.getIconHeight();
    }
    
    public ImageIcon sendImage() {
        return this.sprite;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String toString() {
        return this.name;
    }
}