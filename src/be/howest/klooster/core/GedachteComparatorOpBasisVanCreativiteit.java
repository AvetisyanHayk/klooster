package be.howest.klooster.core;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Hayk
 */
public class GedachteComparatorOpBasisVanCreativiteit
        implements Comparator<Gedachte>, Serializable {

    private static final long serialVersionUID = 1L;

    private final int tenOpzichteVan;
    
    public GedachteComparatorOpBasisVanCreativiteit() {
        tenOpzichteVan = -1;
    }
    
    public GedachteComparatorOpBasisVanCreativiteit(int tenOpzichteVan) {
        if (!Persoonlijkheid.isValidCreativiteit(tenOpzichteVan)) {
            throw new IllegalArgumentException();
        }
        this.tenOpzichteVan = tenOpzichteVan;
    }
    
    @Override
    public int compare(Gedachte gedachte1, Gedachte gedachte2) {
        if (gedachte1 == null && gedachte2 == null) {
            return 0;
        }
        if (gedachte1 == null) {
            return Persoonlijkheid.MAX_CREATIVITEIT + 1;
        }
        if (gedachte2 == null) {
            return 0 - gedachte1.getCreativiteit() - 1;
        }
        if (tenOpzichteVan == -1) {
            return gedachte1.getCreativiteit() - gedachte2.getCreativiteit();
        }
        return Math.abs(tenOpzichteVan - gedachte1.getCreativiteit()) -
                Math.abs(tenOpzichteVan - gedachte2.getCreativiteit());
    }
}
