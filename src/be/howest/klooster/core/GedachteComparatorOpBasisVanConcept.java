package be.howest.klooster.core;

import java.util.Comparator;

/**
 *
 * @author Hayk
 */
public class GedachteComparatorOpBasisVanConcept
        implements Comparator<Gedachte> {

    @Override
    public int compare(Gedachte gedachte1, Gedachte gedachte2) {
        return gedachte1.getConcept() - gedachte2.getConcept();
    }
}
