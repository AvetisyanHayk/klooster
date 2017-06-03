package be.howest.klooster.core;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisVanGoedheid
        implements GedachtenOptimizer {

    private static GedachtenOptimizer uniqueInstance;

    public static synchronized GedachtenOptimizer getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GedachtenOptimizerOpBasisVanGoedheid();
        }
        return uniqueInstance;
    }

    @Override
    public int optimizeGedachten(Pater pater) {
        int aantalGedachten = pater.getAantalGedachten();
        Map<Integer, Set<Gedachte>> gedachtenMap
                = GedachtenOptimizer.getGedachtenMap(pater.getGedachten());
        Set<Gedachte> gedachtenSet = getGeoptimaliseerdeGedachten(gedachtenMap,
                pater.getGoedheid());
        pater.setGedachten(gedachtenSet.toArray(new Gedachte[Pater.MAX_GEDACHTEN]));
        return aantalGedachten - gedachtenSet.size();
    }
    
    private Set<Gedachte> getGeoptimaliseerdeGedachten(
            Map<Integer, Set<Gedachte>> gedachtenMap, int goedheid) {
        Set<Gedachte> geoptimaliseerdeGedachten = new LinkedHashSet<>();
        for (Integer concept : gedachtenMap.keySet()) {
            Set<Gedachte> gedachtenSet = gedachtenMap.get(concept);
            Gedachte gedachteDichtsBijGoedheid = getGedachteDichtsBijGoedheidScore(
                    gedachtenSet, goedheid);
            geoptimaliseerdeGedachten.add(gedachteDichtsBijGoedheid);
        }
        return geoptimaliseerdeGedachten;
    }
    
    private Gedachte getGedachteDichtsBijGoedheidScore(
            Set<Gedachte> gedachtenSet, int goedheid) {
        Gedachte[] gedachten = gedachtenSet
                .toArray(new Gedachte[gedachtenSet.size()]);
        Arrays.sort(gedachten, new GedachteComparatorOpBasisVanGoedheid(goedheid));
        return gedachten[0];
    }
}
