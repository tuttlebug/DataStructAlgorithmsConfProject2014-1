/**
 * Carriable Interface:
 * for any carriable items in the place;
 * store the location inside the Map;
 * 
 * Map:
 *   1. Object name
 *   2  int array
 * 
 * 
 * Instance variables:
 * Place currentPlace();
 * 
 * Do we need a constructor for this?
 * 
 * 
 * 
 * Methods:
 * public addToPlace(Place place, Player player) {
 *   //fetches player's location and orientation, and adds the item
 *   //algorithm for correct location:
 *     //if player is facing up or down
 *      //add or subtract 1(2? 3?) from y location to place item in front of character
 *    //else if player is facing left or right
 *      //add or subtract 1(2? 3?) from x location to place item in front of character
 * }
 * 
 * public addToPack(Place place, Player player) {
 *   //way to retrieve location of player? 
 *   player.pack.addItem(this); //does just "this" actually work in this scenario? 
 * }
 * 
 * 
 * 
 * 
 * 
 */

public interface Carriable {
    
}
