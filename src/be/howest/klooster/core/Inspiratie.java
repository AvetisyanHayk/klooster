package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
public class Inspiratie {

    private static Inspiratie uniqueInstance;

    private int concept = 1;

    private Inspiratie() {
    }

    public static synchronized Inspiratie getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Inspiratie();
        }
        return uniqueInstance;
    }

    public int inspireerMij() {
        int result = concept++;
        if (concept > 9) {
            concept = 0;
        }
        return result;
    }
}
