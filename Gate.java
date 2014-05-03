/*
 * A set of points in areas that mark where a character can enter into a different screen;
 * 
 * Check List:
 * [] A Map of areas that the gate connects to
 * [] XY coordinates in the area
 * [] Line object
 * 
 * To Add/Implement:
 * [] method that sends the character to a new screen
 * 
 * Spawn calculations:
 * **** Village ****
 * 1. Start (-2399, -736);
 * 2. Exit ([-2160, -350], [-1665, -965]);
 * **** Forest Path ****
 * 1. Gate 1: ([-1665, -965], [-2160, -350]);
 * 2. Gate 2: ([-2625, -965], [-2025, -845]);
 * **** Clearing ****
 * 1. Gate 1: ([-2025, -845], [-2625, -965]);
 * 2. Gate 2: ([-2385, -680], [?, ?]);
 * **** Cave ****
 * 1. Gate 1: ([?, ?], [-2385, -680]);
 */
public class Gate {
    
    // instance variables
    private String name;
    public int[] startPoint;
    public int[] endPoint;
    
    public Gate(String name, int[] startSpawnPoint, int[] endSpawnPoint) {
        this.name = name;
        this.startPoint = startSpawnPoint;
        this.endPoint = endSpawnPoint;
    }
}