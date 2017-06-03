package be.howest.klooster.toestand;

import be.howest.klooster.core.Berichten;
import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Woord;

/**
 *
 * @author Hayk
 */
public final class BasisToestand extends AbstracteToestand {

    public BasisToestand(Pater pater) {
        super(pater);
        int aantalGedachten = pater.getAantalGedachten();
        pater.setInfo(String.format(Berichten.OVERZICHT,
                pater.getVolledigeNaam(), pater.getPersoonlijkheid(),
                aantalGedachten, (aantalGedachten != 1) ? "n" : ""));
    }

    @Override
    public void bid() {
        super.bid();
        pater.setToestand(pater.getNormaleToestand());
    }

    @Override
    public Woord spreek() {
        pater.setInfo(String.format(Berichten.SPREEK_BASISTOESTAND,
                pater.getNaam())).triggerChange();
        return null;
    }

    @Override
    public void luister(Woord woord) {
        super.luister(woord);
        pater.setToestand(pater.getNormaleToestand());
    }
}
