package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
interface Toestand {

    void bid();

    Woord spreek();
    
    void spreekTegen(Pater anderePater);

    void luister(Woord woord);

    void denkNa();

    void denkNa(GedachtenOptimizer optimizer);
}