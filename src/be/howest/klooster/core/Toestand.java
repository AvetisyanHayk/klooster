package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
interface Toestand {

    Gedachte bid();

    Woord spreek();
    
    void spreekTegen(Pater anderePater);

    void luister(Woord woord);
    
    void luisterNaar(Pater anderePater);

    void denkNa();

    void denkNa(GedachtenOptimizer optimizer);
}