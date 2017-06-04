package be.howest.klooster.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hayk
 */
public interface GedachtenOptimizer {

    int optimaliseerGedachten(Pater pater);

    public static Map<Integer, List<Gedachte>> getGedachtenMap(Gedachte[] gedachten) {
        Arrays.sort(gedachten, new GedachteComparatorOpBasisVanConcept());
        Map<Integer, List<Gedachte>> gedachtenMap = new LinkedHashMap<>();
        for (Gedachte gedachte : gedachten) {
            if (gedachte != null) {
                int concept = gedachte.getConcept();
                List<Gedachte> gedachtenList = gedachtenMap.get(concept);
                if (gedachtenList == null) {
                    gedachtenList = new ArrayList<>();
                }
                gedachtenList.add(gedachte);
                gedachtenMap.put(concept, gedachtenList);
            }
        }
        return gedachtenMap;
    }
}
