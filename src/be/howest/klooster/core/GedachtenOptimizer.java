package be.howest.klooster.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Hayk
 */
public interface GedachtenOptimizer {

    int optimizeGedachten(Pater pater);

    public static Map<Integer, Set<Gedachte>> getGedachtenMap(Set<Gedachte> gedachten) {
        Map<Integer, Set<Gedachte>> gedachtenMap = new LinkedHashMap<>();
        for (Gedachte gedachte : gedachten) {
            if (gedachte == null) {
                throw new UnsupportedOperationException("kjsdhfksjdhfs");
            }
            int concept = gedachte.getConcept();
            Set<Gedachte> gedachtenSet = gedachtenMap.get(concept);
            if (gedachtenSet == null) {
                gedachtenSet = new TreeSet<>(new GedachteComparatorOpBasisVanConcept());
                gedachtenSet.add(gedachte);
            }
            gedachtenMap.put(concept, gedachtenSet);
        }
        return gedachtenMap;
    }
}
