package be.howest.klooster.core;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Hayk
 */
class PersoonlijkheidComparator
        implements Comparator<Persoonlijkheid>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(Persoonlijkheid persoonlijkheid1, Persoonlijkheid persoonlijkheid2) {
        if (persoonlijkheid1 == null && persoonlijkheid2 == null) {
            return 0;
        }
        if (persoonlijkheid1 == null) {
            return Persoonlijkheid.MAX_GOEDHEID + Persoonlijkheid.MAX_CREATIVITEIT + 1;
        }
        if (persoonlijkheid2 == null) {
            return 0 - persoonlijkheid1.getGoedheid();
        }
        if (persoonlijkheid1.equals(persoonlijkheid2)) {
            return 0;
        }
        if (persoonlijkheid1.getGoedheid() == persoonlijkheid2.getGoedheid()) {
            return persoonlijkheid1.getCreativiteit() - persoonlijkheid2.getCreativiteit();
        }
        return persoonlijkheid1.getGoedheid() - persoonlijkheid2.getGoedheid();
    }
}
