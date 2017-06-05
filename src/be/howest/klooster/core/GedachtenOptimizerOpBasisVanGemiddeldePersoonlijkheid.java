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
        Set<Gedachte> gedachtenSet = getGeoptimaliseerdeGedachten(gedachtenMap);
        pater.setGedachten(gedachtenSet.toArray(new Gedachte[Pater.MAX_GEDACHTEN]));
        return aantalGedachten - gedachtenSet.size();
    }

    private Set<Gedachte> getGeoptimaliseerdeGedachten(Map<Integer, List<Gedachte>> gedachtenMap) {
        Set<Gedachte> geoptimaliseerdeGedachten = new LinkedHashSet<>();
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap.entrySet()) {
            List<Gedachte> gedachtenList = entry.getValue();
            Gedachte gedachteVanGemiddeldeMening
                    = getGedachteVanGemiddeldeMening(gedachtenList, entry.getKey());
            geoptimaliseerdeGedachten.add(gedachteVanGemiddeldeMening);
        }
        return geoptimaliseerdeGedachten;
    }

    private Gedachte getGedachteVanGemiddeldeMening(List<Gedachte> gedachten, int concept) {
        List<Persoonlijkheid> persoonlijkheden
                = Gedachte.mapMeningenUitGedachten(new LinkedHashSet<>(gedachten));
        Persoonlijkheid gemiddeldePersoonlijkheid = Persoonlijkheid
                .combineer(persoonlijkheden);
        return new Gedachte(concept, gemiddeldePersoonlijkheid);
    }
}
