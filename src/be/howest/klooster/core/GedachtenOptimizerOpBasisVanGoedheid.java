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
    public int optimaliseerGedachten(Pater pater) {
        int aantalGedachten = pater.getAantalGedachten();
        Map<Integer, List<Gedachte>> gedachtenMap
                = GedachtenOptimizer.getGedachtenMap(pater.getGedachten());
        Set<Gedachte> gedachtenSet = getGeoptimaliseerdeGedachten(gedachtenMap,
                pater.getGoedheid());
        pater.setGedachten(gedachtenSet.toArray(new Gedachte[Pater.MAX_GEDACHTEN]));
        return aantalGedachten - gedachtenSet.size();
    }
    
    private Set<Gedachte> getGeoptimaliseerdeGedachten(Map<Integer, List<Gedachte>> gedachtenMap, int goedheid) {
        Set<Gedachte> geoptimaliseerdeGedachten = new LinkedHashSet<>();
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap.entrySet()) {
            List<Gedachte> gedachtenList = entry.getValue();
            Gedachte gedachteDichtstBijGoedheidVanPater
                    = getGedachteDichtstBijGoedheidVanPater(gedachtenList, goedheid);
            geoptimaliseerdeGedachten.add(gedachteDichtstBijGoedheidVanPater);
        }
        return geoptimaliseerdeGedachten;
    }
    
    private Gedachte getGedachteDichtstBijGoedheidVanPater(List<Gedachte> gedachten, int goedheid) {
        Collections.sort(gedachten,
                new GedachteComparatorOpBasisVanGoedheid(goedheid));
        return gedachten.get(0);
    }
}
