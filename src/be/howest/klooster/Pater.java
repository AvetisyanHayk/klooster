package be.howest.klooster;

import be.howest.klooster.ex.HoofdZitVolMetGedachtenException;
import be.howest.klooster.ex.GeenGedachtenException;

/**
 *
 * @author Hayk
 */
public class Pater {
    private static final int MAX_GEDACHTEN = 20;
    
    private final String naam;
    private Persoonlijkheid persoonlijkheid;
    private final Gedachte[] gedachten;
    
    public Pater(String naam) {
        this(naam, Persoonlijkheid.createRandomPersoonlijkheid());
    }
    
    public Pater(String naam, Persoonlijkheid persoonlijkheid) {
        if (naam == null || "".equals(naam)) {
            naam = getClass().getName();
        }
        this.naam = naam;
        if (persoonlijkheid == null) {
            persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        }
        this.persoonlijkheid = persoonlijkheid;
        gedachten = new Gedachte[MAX_GEDACHTEN];
    }
    
    public void bid() {
        int inspiratie = Inspiratie.getInstance().inspireerMij();
        // TODO Not finished yet
        throw new UnsupportedOperationException();
    }
    
    public void spreek() {
        if (!hasGedachten()) {
            throw new GeenGedachtenException();
        }
        int inspiratie = Inspiratie.getInstance().inspireerMij();
        // TODO Not finished yet
        throw new UnsupportedOperationException();
    }
    
    public void luister(Woord woord) {
        if (hoofdZitVol()) {
            denkNa();
        }
        // TODO Not finished yet
        throw new UnsupportedOperationException();
    }
    
    private void denkNa() {
        // TODO denkt eerst nog na en daarna:
        if (hoofdZitVol()) {
            throw new HoofdZitVolMetGedachtenException();
        }
        // TODO Not finished yet
        throw new UnsupportedOperationException();
    }
    
    private boolean hasGedachten() {
        for (int i = 0; i < gedachten.length; i++) {
            if (gedachten[i] != null) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hoofdZitVol() {
        for (int i = 0; i < gedachten.length; i++) {
            if (gedachten[i] == null) {
                return false;
            }
        }
        return true;
    }
    
}
