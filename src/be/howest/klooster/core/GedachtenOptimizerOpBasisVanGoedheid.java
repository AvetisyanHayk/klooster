package be.howest.klooster.core;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
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
        Map<Integer, List<Gedachte>> gedachtenMap
                = GedachtenOptimizer.getGedachtenMap(pater.getGedachten());
        Set<Gedachte> gedachtenList = getGeoptimaliseerdeGedachten(gedachtenMap,
                pater.getGoedheid());
        pater.setGedachten(gedachtenList.toArray(new Gedachte[Pater.MAX_GEDACHTEN]));
        return aantalGedachten - gedachtenList.size();
    }
    
    private Set<Gedachte> getGeoptimaliseerdeGedachten(
            Map<Integer, List<Gedachte>> gedachtenMap, int goedheid) {
        Set<Gedachte> geoptimaliseerdeGedachten = new LinkedHashSet<>();
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap.entrySet()) {
            List<Gedachte> gedachtenSet = entry.getValue();
            Gedachte gedachteDichtsBijGoedheid = getGedachteDichtsBijGoedheid(
                    gedachtenSet, goedheid);
            geoptimaliseerdeGedachten.add(gedachteDichtsBijGoedheid);
        }
        return geoptimaliseerdeGedachten;
    }
    
    private Gedachte getGedachteDichtsBijGoedheid(
            List<Gedachte> gedachtenSet, int goedheid) {
        Gedachte[] gedachten = gedachtenSet
                .toArray(new Gedachte[gedachtenSet.size()]);
        Arrays.sort(gedachten, new GedachteComparatorOpBasisVanGoedheid(goedheid));
        return gedachten[0];
    }
}
