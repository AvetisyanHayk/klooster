package be.howest.klooster.toestand;

import be.howest.klooster.core.Berichten;
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
    public void luister(Woord woord) {
        super.luister(woord);
        if (woord != null && pater.hoofdZitVol()) {
            pater.setToestand(pater.getHoofdZitVolMetGedachtenToestand());
        }
    }
    
    @Override
    public void denkNa() {
        pater.setInfo(String.format(Berichten.DENK_NA, pater.getNaam(),
                pater.getAantalGedachten()));
        super.denkNa();
    }
}
