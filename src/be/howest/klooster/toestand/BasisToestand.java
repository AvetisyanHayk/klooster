package be.howest.klooster.toestand;

import be.howest.klooster.Pater;
import be.howest.util.Tools;

/**
 *
 * @author Hayk
 */
public final class BasisToestand extends AbstracteToestand {
    
    public BasisToestand(Pater pater) {
        super(pater);
        pater.setInfo(Tools.toZinMetCommas(pater.getVolledigeNaam(),
                pater.getPersoonlijkheid().toString(),
                getInfoOverAantalGedachten()));
    }

    @Override
    public void bid() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void spreek() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void luister() {
        throw new UnsupportedOperationException();
    }
}
