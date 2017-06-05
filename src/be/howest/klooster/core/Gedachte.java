package be.howest.klooster.core;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Hayk
 */
public class Gedachte {

    private final int concept;
    private final Persoonlijkheid mening;

    Gedachte(int concept, Persoonlijkheid mening) {
        if (mening == null || !Inspiratie.isValidConcept(concept)) {
            throw new IllegalArgumentException();
        }
        this.concept = concept;
        this.mening = mening;
    }

    int getConcept() {
        return concept;
    }

    int getGoedheid() {
        return mening.getGoedheid();
    }

    int getCreativiteit() {
        return mening.getCreativiteit();
    }

    Persoonlijkheid getMening() {
        return mening;
    }

    Woord verwoord(Persoonlijkheid begeestering) {
        if (begeestering == null) {
            throw new IllegalArgumentException();
        }
        return new Woord(this, begeestering);
    }

    static List<Persoonlijkheid> mapMeningenUitGedachten(Collection<Gedachte> gedachten) {
        if (gedachten == null) {
            return null;
        }
        return gedachten.stream().filter(gedachte -> gedachte != null)
                .map(Gedachte::getMening).collect(
                Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format(Berichten.GEDACHTE, mening, concept);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.concept;
        hash = 97 * hash + Objects.hashCode(this.mening);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gedachte other = (Gedachte) obj;
        if (this.concept != other.concept) {
            return false;
        }
        return Objects.equals(this.mening, other.mening);
    }
}
