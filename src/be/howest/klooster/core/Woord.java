package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
public class Woord {

    private final Gedachte gedachte;
    private final Persoonlijkheid begeestering;
    

    public Woord(Gedachte gedachte, Persoonlijkheid begeestering) {
        if (gedachte == null || begeestering == null) {
            throw new IllegalArgumentException();
        }
        this.begeestering = begeestering;
        this.gedachte = gedachte;
    }
    
    public Persoonlijkheid getBegeestering() {
        return begeestering;
    }
    
    public Gedachte getGedachte() {
        return gedachte;
    }
    
    public int getConcept() {
        return gedachte.getConcept();
    }
}
