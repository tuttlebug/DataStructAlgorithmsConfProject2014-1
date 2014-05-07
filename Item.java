/**
 * Interacterable Items
 * 
 * Check List:
 * [Ã] Image
<<<<<<< HEAD
<<<<<<< HEAD
 * [] Dimensions (size of sprite)
 * [] Boundaries (i.e., array of lines)
=======
=======
>>>>>>> FETCH_HEAD
 * [Ã] Dimensions
 * [] Boundaries
>>>>>>> FETCH_HEAD
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
    private int[] coordinates;
    
    // constructor
    public Item(String name, String imageFile, int[] coords) {
        this.name = name;
        this.imageFile = imageFile; 
        this.coordinates = coords;
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