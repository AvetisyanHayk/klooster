package be.howest.klooster.core;

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

    Gedachte[] getGedachten() {
        return getGedachtenSet().toArray(new Gedachte[getAantalGedachten()]);
    }
    
    Set<Gedachte> getGedachtenSet() {
        Set<Gedachte> gedachtenSet = new LinkedHashSet<>();
        for (Gedachte gedachte : gedachten) {
            if (gedachte != null) {
                gedachtenSet.add(gedachte);
            }
        }
        return gedachtenSet;
    }

    void setGedachten(Gedachte[] gedachten) {
        if (gedachten != null && gedachten.length == MAX_GEDACHTEN) {
            this.gedachten = gedachten;
        }
    }

    Toestand getToestand() {
        return toestand;
    }

    Toestand getBasisToestand() {
        return basisToestand;
    }

    Toestand getNormaleToestand() {
        return normaleToestand;
    }

    Toestand getHoofdZitVolMetGedachtenToestand() {
        return hoofdZitVolMetGedachtenToestand;
    }

    void setToestand(Toestand toestand) {
        this.toestand = toestand;
    }

    public Persoonlijkheid getPersoonlijkheid() {
        return persoonlijkheid;
    }
    
    void setPersoonlijkheid(Persoonlijkheid persoonlijkheid) {
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

    void setInfo(String info) {
        this.info = info;
        triggerChange();
    }

    boolean addGedachte(Gedachte gedachte) {
        if (gedachte != null && !hoofdZitVol()) {
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

    boolean hasGedachten() {
        for (Gedachte gedachte : gedachten) {
            if (gedachte != null) {
                return true;
            }
        }
        return false;
    }

    boolean hoofdZitVol() {
        for (Gedachte gedachte : gedachten) {
            if (gedachte == null) {
                return false;
            }
        }
        return true;
    }

    void triggerChange() {
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
        hash = 83 * hash + Objects.hashCode(this.naam);
        hash = 83 * hash + Objects.hashCode(this.persoonlijkheid);
        hash = 83 * hash + Arrays.deepHashCode(this.gedachten);
        hash = 83 * hash + Objects.hashCode(this.toestand.getClass());
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
        if (!Objects.equals(this.persoonlijkheid, other.persoonlijkheid)) {
            return false;
        }
        if (!Arrays.deepEquals(this.gedachten, other.gedachten)) {
            return false;
        }
        return Objects.equals(this.toestand.getClass(), other.toestand.getClass());
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
