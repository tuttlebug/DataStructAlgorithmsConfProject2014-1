import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.*;
import java.awt.Image;

public class Enemy extends Interactable {
    
    public Enemy(String name, String imageFile) {
        super(name, imageFile);
        this.name = name;
        this.imageFile = imageFile;
        this.box = new Rectangle2D.Double();
    }
    
}