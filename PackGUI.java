//placeholders from Place.java; look out for new libraries! 
import java.util.*;
import javax.swing.*;
import java.awt.*; 

public class PackGUI extends JPanel {
  
  //instance variables
  //the pack, maybe? Or else the map of things in the pack?
  private PackListener listener;
  private Map<String, Carriable> items;
  
  //constructor
  public PackGUI(Player player) {
    //retrieve the item map from the Player object's Pack
      //that'd be something like Map itemsAvailablePlayer.Pack.Map
    Pack pack = player.openPack(); //?
    this.items = pack.getMap();
    
    //Process:
      //program accesses map for the sake of an instance variable
      //so create a new instance of the map of items in the map?

  }
  
  private class PackListener implements ActionListener { //original Java library! 
    //check out the MousePanel.java program 
    }
}