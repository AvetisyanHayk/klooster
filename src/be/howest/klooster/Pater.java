package be.howest.klooster;

import be.howest.klooster.toestand.BasisToestand;
import be.howest.klooster.toestand.HoofdZitVolMetGedachtenToestand;
import be.howest.klooster.toestand.NormaleToestand;
import be.howest.klooster.toestand.Toestand;
import be.howest.util.Tools;
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
            naam = getClass().getSimpleName();
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
        triggerChange();
    }
    
    public String getNaam() {
        return naam;
    }
    
    public Persoonlijkheid getPersoonlijkheid() {
        return persoonlijkheid;
    }
    
    public String getVolledigeNaam() {
        String classNaam = getClass().getSimpleName();
        if (naam.equals(classNaam)) {
            return naam;
        }
        return Tools.toZin(classNaam, naam);
    }
    
    public int getAantalGedachten() {
        int aantalGedachten = 0;
        for (Gedachte gedachte : gedachten) {
            if (gedachte != null) {
                aantalGedachten++;
            }
        }
        return aantalGedachten;
    }
    
    public void bid() {
        toestand.bid();
    }
    
    public void spreek() {
        toestand.spreek();
    }
    
    public void luister(Woord woord) {
        toestand.luister();
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
    
    public void triggerChange() {
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
        return toestand.toString();
    }
}
