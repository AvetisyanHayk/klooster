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
        throw new UnsupportedOperationException();
    }

    @Override
    public void spreek() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void luister(Woord woord) {
        throw new UnsupportedOperationException();
    }

    private void denkNa() {
        throw new UnsupportedOperationException();
    }
}
