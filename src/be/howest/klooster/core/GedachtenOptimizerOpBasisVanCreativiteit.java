package be.howest.klooster.core;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
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
    public int optimaliseerGedachten(Pater pater) {
        int aantalGedachten = pater.getAantalGedachten();
        Map<Integer, List<Gedachte>> gedachtenMap
                = GedachtenOptimizer.getGedachtenMap(pater.getGedachten());
        Set<Gedachte> gedachtenSet = getGeoptimaliseerdeGedachten(gedachtenMap,
                pater.getCreativiteit());
        int aantalGeoptimaliseerd = aantalGedachten - gedachtenSet.size();
        if (aantalGeoptimaliseerd > 0) {
            pater.setGedachten(gedachtenSet.toArray(new Gedachte[Pater.MAX_GEDACHTEN]));
        }
        return aantalGeoptimaliseerd;
    }

    private Set<Gedachte> getGeoptimaliseerdeGedachten(Map<Integer, List<Gedachte>> gedachtenMap, int creativiteit) {
        Set<Gedachte> geoptimaliseerdeGedachten = new LinkedHashSet<>();
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap.entrySet()) {
            List<Gedachte> gedachtenList = entry.getValue();
            Gedachte gedachteDichtstBijCreativiteitVanPater
                    = getGedachteDichtstBijCreativiteitVanPater(gedachtenList, creativiteit);
            geoptimaliseerdeGedachten.add(gedachteDichtstBijCreativiteitVanPater);
        }
        return geoptimaliseerdeGedachten;
    }

    private Gedachte getGedachteDichtstBijCreativiteitVanPater(List<Gedachte> gedachten, int creativiteit) {
        Collections.sort(gedachten,
                new GedachteComparatorOpBasisVanCreativiteit(creativiteit));
        return gedachten.get(0);
    }
}
