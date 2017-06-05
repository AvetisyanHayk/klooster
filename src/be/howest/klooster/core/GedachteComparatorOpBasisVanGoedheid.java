package be.howest.klooster.core;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Hayk
 */
class GedachteComparatorOpBasisVanGoedheid
        implements Comparator<Gedachte>, Serializable {

    private static final long serialVersionUID = 1L;

    private final int tenOpzichteVan;

    GedachteComparatorOpBasisVanGoedheid() {
        tenOpzichteVan = -1;
    }

    GedachteComparatorOpBasisVanGoedheid(int tenOpzichteVan) {
        if (!Persoonlijkheid.isValidGoedheid(tenOpzichteVan)) {
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
            return Persoonlijkheid.MAX_GOEDHEID + 1;
        }
        if (gedachte2 == null) {
            return 0 - gedachte1.getGoedheid() - 1;
        }
        if (tenOpzichteVan == -1) {
            return gedachte1.getGoedheid() - gedachte2.getGoedheid();
        }
        return Math.abs(tenOpzichteVan - gedachte1.getGoedheid())
                - Math.abs(tenOpzichteVan - gedachte2.getGoedheid());
    }
}
