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
    }

    @Override
    public void bid() {
        super.bid();
        pater.setToestand(pater.getNormaleToestand());
    }

    @Override
    public Woord spreek() {
        pater.setInfo(String.format(Berichten.SPREEK_BASISTOESTAND,
                pater.getNaam()));
        return null;
    }

    @Override
    public void luister(Woord woord) {
        super.luister(woord);
        pater.setToestand(pater.getNormaleToestand());
    }
}
