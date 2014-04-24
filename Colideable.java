/**
 * Collision Detection:
 * 
 * Check List:
 * [] center point
 * [] radius; width; height (bounding region)
 * [] 
 * 
 * Situation:
 * ¥ These may be actual objects, with sprites and everything, that lay on top of the background;
 * ¥ Or, they are an array of points and lines that surround images on the background, creating a 
 *   bounding region that way;
 */
import java.awt.geom.*;

public interface Colideable {
    
    public void collided(Rectangle2D.Double box, Line2D.Double line);
    
}