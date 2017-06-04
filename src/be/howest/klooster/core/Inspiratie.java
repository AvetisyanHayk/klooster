package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
public class Inspiratie {

    public static final int MIN = 1;
    public static final int MAX = 9;

    private static Inspiratie uniqueInstance;

    private int concept = MIN;

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
        if (concept > MAX) {
            reset();
        }
        return result;
    }

    Inspiratie reset() {
        concept = MIN;
        return this;
    }

    public static boolean isValidConcept(int concept) {
        return concept >= MIN && concept <= MAX;
    }
}
