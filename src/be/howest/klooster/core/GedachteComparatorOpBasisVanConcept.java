package be.howest.klooster.core;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Hayk
 */
public class GedachteComparatorOpBasisVanConcept
        implements Comparator<Gedachte>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(Gedachte gedachte1, Gedachte gedachte2) {
        return gedachte1.getConcept() - gedachte2.getConcept();
    }
}
