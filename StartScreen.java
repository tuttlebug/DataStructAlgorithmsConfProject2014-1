import javax.swing.*;
import java.awt.*;
import java.awt.event.*;    // ActionListener, ActionEvent
import javax.swing.event.*; // ChangeListener, ChangeEvent
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.*;

public class StartScreen extends JPanel implements MouseListener {
    
    private static final int W_WIDTH = 700;             // width of frame
    private static final int W_HEIGHT = 600;            // height of window

    // Mouse Listener
    public void mousePressed(MouseEvent event) {
    }
    public void mouseReleased(MouseEvent event) {
    }
    public void mouseClicked(MouseEvent event) {
        x = event.getX();
        y = event.getY();
        repaint();
        System.out.printf("x = %d, y = %d\ncursex = %f, cursey = %f\n", x, y, cursor.getX(), cursor.getY());
    }
    public void mouseEntered(MouseEvent event) {
    }
    public void mouseExited(MouseEvent event) {
    }
    
    // instance variables
    private Image bg;
    private boolean gameStarted;
    private Rectangle2D.Double cursor;
//    private int x, y, tRectX, tRectY;
    public int x, y;
    private int tRectX, tRectY;
    private Rectangle2D.Double titleRect, play;
    
    // constructor
    public StartScreen() throws IOException {
        this.bg = ImageIO.read(new File("StartScreen.png")); // filler picture
        this.gameStarted = false;
        this.x = W_WIDTH / 2;
        this.y = W_HEIGHT / 2;
        this.addMouseListener(this);
        this.tRectX = 100;
        this.tRectY = 50;
        this.titleRect = new Rectangle2D.Double(tRectX, tRectY, W_WIDTH - 200, W_HEIGHT - 400); 
        this.play = new Rectangle2D.Double(tRectX + titleRect.getWidth() / 3, 
                                                                  tRectY + titleRect.getHeight() + 20,
                                                                  200,
                                                                  100);
        System.out.printf("play x = %f, play y = %f, play width = %f, play height = %f\n", 
                          play.getX(), play.getY(), play.getWidth(), play.getHeight());
    }
    
    public void paintComponent(Graphics g) { 
        this.cursor = new Rectangle2D.Double(x, y, 10, 10); 
        // ------------ Pen ------------ \\
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        // ------------ Draw ------------ \\
        pen.drawImage(this.bg, 0, 0, this);
        pen.setColor(Color.RED);
        pen.draw(this.titleRect);
        pen.draw(this.play);
        pen.draw(this.cursor);
        pen.fill(this.cursor);
        pen.setColor(Color.BLUE);
        g.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 100));
        pen.drawString("TITLE", this.tRectX, this.tRectY + 100);
        g.setFont(new Font("Adobe Caslon Pro", Font.PLAIN, 30));
        pen.drawString("Press \"M\" to start", 
                       (int) (this.tRectX + this.titleRect.getWidth() / 3),
                       (int) (this.tRectY + this.titleRect.getHeight() + 20 + 30));
        if (this.playPressed()) {
            this.gameStarted = true;
            System.out.println("clicked");
        }
    }
    
//    public boolean gameStarted() {
//        return this.gameStarted;
//    }
    
    public boolean playPressed() {
        return (this.x > this.play.getX() && 
            (this.x + this.cursor.getWidth() < this.play.getX() + this.play.getWidth()) &&
            (this.y > this.play.getY()) && 
            (this.y + this.cursor.getHeight() < this.play.getY() + this.play.getHeight()));
    }
    

}


