package be.howest.klooster.core;

import be.howest.klooster.toestand.BasisToestand;
import be.howest.klooster.toestand.HoofdZitVolMetGedachtenToestand;
import be.howest.klooster.toestand.NormaleToestand;
import be.howest.klooster.toestand.Toestand;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public class Pater extends Observable {

    public static final int MAX_GEDACHTEN = 20;

    private final String naam;
    private Persoonlijkheid persoonlijkheid;
    private Gedachte[] gedachten;

    private Toestand basisToestand;
    private Toestand normaleToestand;
    private Toestand hoofdZitVolMetGedachtenToestand;

    private Toestand toestand;

    private String info = null;

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

    public String getNaam() {
        return naam;
    }

    public Gedachte[] getGedachten() {
        return gedachten;
    }
    
    public Set<Gedachte> getSetVanGedachten() {
        return new LinkedHashSet<>(Arrays.asList(gedachten));
    }

    void setGedachten(Gedachte[] gedachten) {
        if (gedachten != null && gedachten.length == MAX_GEDACHTEN) {
            this.gedachten = gedachten;
        }
    }

    public Toestand getToestand() {
        return toestand;
    }

    public Toestand getBasisToestand() {
        return basisToestand;
    }

    public Toestand getNormaleToestand() {
        return normaleToestand;
    }

    public Toestand getHoofdZitVolMetGedachtenToestand() {
        return hoofdZitVolMetGedachtenToestand;
    }

    public void setToestand(Toestand toestand) {
        this.toestand = toestand;
    }

    public Persoonlijkheid getPersoonlijkheid() {
        return persoonlijkheid;
    }
    
    public void setPersoonlijkheid(Persoonlijkheid persoonlijkheid) {
        if (persoonlijkheid != null) {
            this.persoonlijkheid = persoonlijkheid;
        }
    }

    public int getGoedheid() {
        return persoonlijkheid.getGoedheid();
    }

    public int getCreativiteit() {
        return persoonlijkheid.getCreativiteit();
    }

    public void setInfo(String info) {
        this.info = info;
        triggerChange();
    }

    public boolean addGedachte(Gedachte gedachte) {
        if (!hoofdZitVol()) {
            for (int i = 0; i < gedachten.length; i++) {
                if (gedachten[i] == null) {
                    gedachten[i] = gedachte;
                    return true;
                }
            }
        }
        return false;
    }

    public String getVolledigeNaam() {
        String classNaam = getClass().getSimpleName();
        if (naam.equals(classNaam)) {
            return naam;
        }
        return classNaam + " " + naam;
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
        toestand.luister(woord);
    }

    public void denkNa() {
        toestand.denkNa();
    }

    public void denkNa(GedachtenOptimizer optimizer) {
        toestand.denkNa(optimizer);
    }

    public void info() {
        triggerChange();
    }

    public boolean hasGedachten() {
        for (Gedachte gedachte : gedachten) {
            if (gedachte != null) {
                return true;
            }
        }
        return false;
    }

    public boolean hoofdZitVol() {
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
        info = null;
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        info = String.format(Berichten.NIEUWE_PATER, this);
        triggerChange();
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
        return Objects.equals(this.naam, other.naam);
    }

    @Override
    public String toString() {
        if (info != null) {
            return info;
        }
        int aantalGedachten = getAantalGedachten();
        return String.format(Berichten.PATER,
                getVolledigeNaam(), getPersoonlijkheid(),
                aantalGedachten, (aantalGedachten != 1) ? "n" : "");
    }
}
