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
        if (gedachte1 == null && gedachte2 == null) {
            return 0;
        }
        if (gedachte1 == null) {
            return Inspiratie.MAX + 1;
        }
        if (gedachte2 == null) {
            return 0 - gedachte1.getConcept();
        }
        return gedachte1.getConcept() - gedachte2.getConcept();
    }
}
