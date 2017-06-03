package be.howest.klooster.toestand;

import be.howest.klooster.core.Woord;

/**
 *
 * @author Hayk
 */
public interface Toestand {
    void bid();
    void spreek();
    void luister(Woord woord);
}
