package be.howest.klooster.core;

import java.util.Comparator;

/**
 *
 * @author Hayk
 */
public class GedachteComparatorOpBasisVanGoedheid
    implements Comparator<Gedachte> {
    
    private final int tenOpzichteVanGoedheidScore;
    
    public GedachteComparatorOpBasisVanGoedheid() {
        this(-1);
    }
    
    public GedachteComparatorOpBasisVanGoedheid(int tenOpzichteVanGoedheidScore) {
        this.tenOpzichteVanGoedheidScore = tenOpzichteVanGoedheidScore;
    }

    @Override
    public int compare(Gedachte gedachte1, Gedachte gedachte2) {
        if (tenOpzichteVanGoedheidScore >= 0) {
            return Math.abs(tenOpzichteVanGoedheidScore -
                    Math.abs(gedachte1.getConcept() - gedachte2.getConcept()));
        }
        return gedachte1.getMening().getGoedheid() -
                gedachte2.getMening().getGoedheid();
    }
    
    
}
