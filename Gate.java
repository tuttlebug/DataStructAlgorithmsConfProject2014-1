/*
 * A set of points in areas that mark where a character can enter into a different screen;
 * 
 * Check List:
 * [] A Map of areas that the gate connects to
 * [] XY coordinates in the area
 * 
 * To Add/Implement:
 * [] method that sends the character to a new screen
 */
public class Gate {
    
    // instance variables
    private String name;
    public int[] startPoint;
    public int[] endPoint;
    
    public Door(String name, int[] startSpawnPoint, int[] endSpawnPoint) {
        this.name = name;
        this.startPoint = startSpawnPoint;
        this.endPoint = endSpawnPoint;
    }
}