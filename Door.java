/**
 * contains instance variable spawnpoint;
 */
// Might extend place object
public class Door {
    
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