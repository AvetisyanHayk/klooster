package be.howest.klooster.toestand;

import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Woord;

/**
 *
 * @author Hayk
 */
public final class NormaleToestand extends AbstracteToestand {
    
    public NormaleToestand(Pater pater) {
        super(pater);
    }

    @Override
    public void bid() {
        super.bid();
        if (pater.hoofdZitVol()) {
            pater.setToestand(pater.getHoofdZitVolMetGedachtenToestand());
        }
    }

    @Override
    public void spreek() {
        super.spreek();
        if (!pater.hasGedachten()) {
            pater.setToestand(pater.getBasisToestand());
        }
    }

    @Override
    public void luister(Woord woord) {
        super.luister(woord);
        if (pater.hoofdZitVol()) {
            pater.setToestand(pater.getHoofdZitVolMetGedachtenToestand());
        }
    }
}
