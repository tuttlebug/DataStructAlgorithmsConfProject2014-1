import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.*;
import java.awt.Image;

public class Enemy extends Interactable {
    
    private int atkPower;
    
    public Enemy(String name, String imageFile, int atkPower) {
        super(name, imageFile);
        this.name = name;
        this.imageFile = imageFile;
        this.box = new Rectangle2D.Double();
        this.atkPower = atkPower;
    }
    
    // ------------ Attack ------------ \\
    public int attack() {
        return this.atkPower;
    }
}