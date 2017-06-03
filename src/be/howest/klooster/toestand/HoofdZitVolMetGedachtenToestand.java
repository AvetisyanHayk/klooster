package be.howest.klooster.toestand;

import be.howest.klooster.core.Berichten;
import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Woord;

/**
 *
 * @author Hayk
 */
public final class HoofdZitVolMetGedachtenToestand extends AbstracteToestand {

    public HoofdZitVolMetGedachtenToestand(Pater pater) {
        super(pater);
    }

    @Override
    public void bid() {
        super.denkNa();
    }

    @Override
    public void luister(Woord woord) {
        super.denkNa();
    }
    
    @Override
    public void denkNa() {
        pater.setInfo(String.format(Berichten.DENK_NA_HOOFD_ZIT_VOL,
                pater.getNaam()));
        super.denkNa();
        if (!pater.hoofdZitVol() && pater.getToestand()
                .equals(pater.getHoofdZitVolMetGedachtenToestand())) {
            pater.setToestand(pater.getNormaleToestand());
        }
    }
}
