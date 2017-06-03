package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
public class Gedachte {

    private final int concept;
    private final Persoonlijkheid mening;

    public Gedachte(int concept, Persoonlijkheid mening) {
        this.concept = concept;
        this.mening = mening;
    }

    public Woord verwoord(Persoonlijkheid begeestering) {
        return new Woord(this, begeestering);
    }
}
