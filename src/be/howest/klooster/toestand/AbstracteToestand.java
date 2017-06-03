package be.howest.klooster.toestand;

import be.howest.klooster.core.Berichten;
import be.howest.klooster.core.Gedachte;
import be.howest.klooster.core.Inspiratie;
import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Persoonlijkheid;
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
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid mening = pater.getPersoonlijkheid();
        Gedachte gedachte = new Gedachte(concept, mening);
        pater.addGedachte(gedachte);
        pater.setInfo(String.format(Berichten.BID, pater.getNaam(), gedachte))
                .triggerChange();
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
}
