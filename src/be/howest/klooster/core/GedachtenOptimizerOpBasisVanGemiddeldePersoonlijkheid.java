package be.howest.klooster.core;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid
        implements GedachtenOptimizer {

    private static GedachtenOptimizer uniqueInstance;

    public static synchronized GedachtenOptimizer getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance
                    = new GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid();
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

    private Set<Gedachte> getGeoptimaliseerdeGedachten(
            Map<Integer, List<Gedachte>> gedachtenMap, int goedheid) {
        Set<Gedachte> geoptimaliseerdeGedachten = new LinkedHashSet<>();
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap.entrySet()) {
            List<Gedachte> gedachtenList = entry.getValue();
            Gedachte gedachteMetHoogsteCreativiteit
                    = getGedachteMetHoogsteCreativiteit(gedachtenList, entry.getKey());
            geoptimaliseerdeGedachten.add(gedachteMetHoogsteCreativiteit);
        }
        return geoptimaliseerdeGedachten;
    }

    private Gedachte getGedachteMetHoogsteCreativiteit(
            List<Gedachte> gedachtenSet, int concept) {
        int gemiddeldeGoedheid = 0;
        int gemiddeldeCreativiteit = 0;
        for (Gedachte gedachte : gedachtenSet) {
            gemiddeldeGoedheid += gedachte.getGoedheid();
            gemiddeldeCreativiteit = gedachte.getCreativiteit();
        }
        gemiddeldeGoedheid /= gedachtenSet.size();
        gemiddeldeCreativiteit /= gedachtenSet.size();
        return new Gedachte(concept,
                new Persoonlijkheid(gemiddeldeGoedheid, gemiddeldeCreativiteit));
    }

}
