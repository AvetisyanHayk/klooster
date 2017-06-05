package be.howest.klooster.core;

import java.util.Objects;

/**
 *
 * @author Hayk
 */
class Woord {

    private final Gedachte gedachte;
    private final Persoonlijkheid begeestering;

    Woord(Gedachte gedachte, Persoonlijkheid begeestering) {
        if (gedachte == null || begeestering == null) {
            throw new IllegalArgumentException();
        }
        this.begeestering = begeestering;
        this.gedachte = gedachte;
    }

    Persoonlijkheid getBegeestering() {
        return begeestering;
    }

    Gedachte getGedachte() {
        return gedachte;
    }

    int getConcept() {
        return gedachte.getConcept();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.gedachte);
        hash = 41 * hash + Objects.hashCode(this.begeestering);
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
        final Woord other = (Woord) obj;
        if (!Objects.equals(this.gedachte, other.gedachte)) {
            return false;
        }
        return Objects.equals(this.begeestering, other.begeestering);
    }
}
