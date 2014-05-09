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
    
    public Image[] sprites;
    private boolean speaking;
    
    public NPC(String name, String imageFile) {
        super(name, imageFile);
        this.name = name;
        this.imageFile = imageFile;
        this.box = new Rectangle2D.Double();
        this.sprites = new Image[12];
        this.speaking = false;
    }
    // ------------ Sprites ------------ \\
    public void loadImage() throws IOException {
        super.loadImage();
        this.image = ImageIO.read(new File(this.imageFile));
        this.width = this.image.getWidth(null) + 10;
        this.height = this.image.getHeight(null) + 10;
    }
    
    public void turn(Image image) {
        this.image = image;
    }
    
    public void loadSprites(String[] filenames) throws IOException {
            // left
            this.sprites[0] = (ImageIO.read(new File(filenames[0])));
            this.sprites[1] = (ImageIO.read(new File(filenames[1])));   
            this.sprites[2] = (ImageIO.read(new File(filenames[2])));   
            // right    
            this.sprites[3] = (ImageIO.read(new File(filenames[3])));  
            this.sprites[4] = (ImageIO.read(new File(filenames[4]))); 
            this.sprites[5] = (ImageIO.read(new File(filenames[5])));
            // down
            this.sprites[6] = (ImageIO.read(new File(filenames[6]))); 
            this.sprites[7] = (ImageIO.read(new File(filenames[7])));  
            this.sprites[8] = (ImageIO.read(new File(filenames[8])));  
            // up
            this.sprites[9] = (ImageIO.read(new File(filenames[9]))); 
            this.sprites[10] = (ImageIO.read(new File(filenames[10]))); 
            this.sprites[11] = (ImageIO.read(new File(filenames[11])));       
    }
    
    // ------------ Speaking ------------ \\
    public boolean isSpeaking() {
        return this.speaking;
    }
    
    public void noTurn(boolean bool) {
        this.speaking = bool;
    }
        
    /*
    public String speak() {
        
    }
    */
}