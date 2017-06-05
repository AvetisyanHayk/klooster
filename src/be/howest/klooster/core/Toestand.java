package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
interface Toestand {

    Gedachte bid();

    Woord spreek();

    void luister(Woord woord);

    void denkNa();

    void denkNa(GedachtenOptimizer optimizer);
}