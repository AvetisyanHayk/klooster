package be.howest.klooster;

import be.howest.klooster.ex.HoofdZitVolMetGedachtenException;
import be.howest.klooster.ex.GeenGedachtenException;
import be.howest.klooster.toestand.BasisToestand;
import be.howest.klooster.toestand.HoofdZitVolMetGedachtenToestand;
import be.howest.klooster.toestand.NormaleToestand;
import be.howest.klooster.toestand.Toestand;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Hayk
 */
public class Pater extends Observable {
    private static final int MAX_GEDACHTEN = 20;
    
    private final String naam;
    private Persoonlijkheid persoonlijkheid;
    private final Gedachte[] gedachten;
    
    private Toestand basisToestand;
    private Toestand normaleToestand;
    private Toestand hoofdZitVolMetGedachtenToestand;
    
    private Toestand toestand;
    
    
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
        initToestanden();
    }
    
    private void initToestanden() {
        basisToestand = new BasisToestand(this);
        normaleToestand = new NormaleToestand(this);
        hoofdZitVolMetGedachtenToestand = new HoofdZitVolMetGedachtenToestand(this);
        toestand = basisToestand;
    }
    
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        setChangedAndNotify();
    }
    
    public String getNaam() {
        return naam;
    }
    
    public void bid() {
        int inspiratie = Inspiratie.getInstance().inspireerMij();
        // TODO Not finished yet
        setChangedAndNotify();
        throw new UnsupportedOperationException();
    }
    
    public void spreek() {
        if (!hasGedachten()) {
            throw new GeenGedachtenException();
        }
        int inspiratie = Inspiratie.getInstance().inspireerMij();
        // TODO Not finished yet
        setChangedAndNotify();
        throw new UnsupportedOperationException();
    }
    
    public void luister(Woord woord) {
        if (hoofdZitVol()) {
            denkNa();
        }
        // TODO Not finished yet
        setChangedAndNotify();
        throw new UnsupportedOperationException();
    }
    
    private void denkNa() {
        // TODO denkt eerst nog na en daarna:
        if (hoofdZitVol()) {
            throw new HoofdZitVolMetGedachtenException();
        }
        // TODO Not finished yet
        setChangedAndNotify();
        throw new UnsupportedOperationException();
    }
    
    private boolean hasGedachten() {
        for (Gedachte gedachte : gedachten) {
            if (gedachte != null) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hoofdZitVol() {
        for (Gedachte gedachte : gedachten) {
            if (gedachte == null) {
                return false;
            }
        }
        return true;
    }
    
    private void setChangedAndNotify() {
        setChanged();
        notifyObservers();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.naam);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pater other = (Pater) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return naam;
    }
}
