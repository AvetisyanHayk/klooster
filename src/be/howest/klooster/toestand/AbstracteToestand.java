package be.howest.klooster.toestand;

import be.howest.klooster.Pater;

/**
 *
 * @author Hayk
 */
public abstract class AbstracteToestand implements Toestand {

    protected final Pater pater;

    AbstracteToestand(Pater pater) {
        this.pater = pater;
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
