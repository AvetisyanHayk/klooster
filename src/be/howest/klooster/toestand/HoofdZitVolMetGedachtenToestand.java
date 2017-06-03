package be.howest.klooster.toestand;

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
}
