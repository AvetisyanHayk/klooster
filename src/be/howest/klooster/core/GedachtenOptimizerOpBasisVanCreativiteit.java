package be.howest.klooster.core;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisVanCreativiteit
        implements GedachtenOptimizer {

    private static GedachtenOptimizer uniqueInstance;

    public static synchronized GedachtenOptimizer getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GedachtenOptimizerOpBasisVanCreativiteit();
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
        for (Map.Entry<Integer, Set<Gedachte>> entry : gedachtenMap.entrySet()) {
            Set<Gedachte> gedachtenSet = entry.getValue();
            Gedachte gedachteMetHoogsteCreativiteit
                    = getGedachteMetHoogsteCreativiteit(gedachtenSet);
            geoptimaliseerdeGedachten.add(gedachteMetHoogsteCreativiteit);
        }
        return geoptimaliseerdeGedachten;
    }

    private Gedachte getGedachteMetHoogsteCreativiteit(
            Set<Gedachte> gedachtenSet) {
        Gedachte[] gedachten = gedachtenSet
                .toArray(new Gedachte[gedachtenSet.size()]);
        Arrays.sort(gedachten, new GedachteComparatorOpBasisVanCreativiteit());
        return gedachten[0];
    }
}
