package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
public class Inspiratie {
    
    private static final Inspiratie INSTANCE = new Inspiratie();
    
    private int concept = 1;
    
    private Inspiratie() {
    }
    
    public static Inspiratie getInstance() {
        return INSTANCE;
    }
   
    public int inspireerMij() {
        int result = concept++;
        if (concept > 9) {
            concept = 0;
        }
        return result;
    }
}
