package be.howest.klooster.toestand;

import be.howest.klooster.core.Woord;
import be.howest.klooster.core.GedachtenOptimizer;

/**
 *
 * @author Hayk
 */
public interface Toestand {
    void bid();
    Woord spreek();
    void luister(Woord woord);
    void denkNa();
    void denkNa(GedachtenOptimizer optimizer);
}
