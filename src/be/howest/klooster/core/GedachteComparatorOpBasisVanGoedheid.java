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
                    Math.abs(gedachte1.getGoedheid() - gedachte2.getGoedheid()));
        }
        return gedachte1.getGoedheid() - gedachte2.getGoedheid();
    }
    
    
}
