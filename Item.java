/**
 * Interacterable Items
 * 
 * Check List:
 * [] Image
 * [] Dimensions
 * [] Boundaries
 * 
 * To Add/Implement:
 * [] Performs action when interacted with
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
    
    // constructor
    public Item(String name, String imageFile) {
        this.name = name;
        this.imageFile = imageFile; 
    }
    
    // methods
    public void loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(this.imageFile));
        this.sprite = new ImageIcon(image);
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