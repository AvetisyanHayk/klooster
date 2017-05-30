package be.howest.klooster;

/**
 *
 * @author Hayk
 */
class Inspiratie {
    
    private static final Inspiratie INSTANCE = new Inspiratie();
    
    private int concept = 1;
    
    private Inspiratie() {
    }
    
    static Inspiratie getInstance() {
        return INSTANCE;
    }
   
    int inspireerMij() {
        int result = concept++;
        if (concept > 9) {
            concept = 0;
        }
        return result;
    }
}
