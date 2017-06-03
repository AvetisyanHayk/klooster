package be.howest.klooster.toestand;

import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Woord;

/**
 *
 * @author Hayk
 */
public abstract class AbstracteToestand implements Toestand {

    protected final Pater pater;

    AbstracteToestand(Pater pater) {
        this.pater = pater;
    }
    
    @Override
    public void bid() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void luister(Woord woord) {
        if (woord != null) {
            // TODO hier komt de code anders gebeurt er niks
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Woord spreek() {
        throw new UnsupportedOperationException();
    }

    protected String getInfoOverAantalGedachten() {
        int aantalGedachten = pater.getAantalGedachten();
        StringBuilder sb = new StringBuilder();
        sb.append(aantalGedachten);
        String gedachte = " gedachte";
        if (aantalGedachten != 1) {
            gedachte += "n";
        }
        return sb.append(gedachte).append(" in zijn hoofd").toString();
    }
}
