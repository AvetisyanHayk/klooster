package be.howest.klooster.toestand;

import be.howest.klooster.Pater;

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
    public void luister() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void denkNa() {
        throw new UnsupportedOperationException();
    }
}
