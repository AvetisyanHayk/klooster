package be.howest.klooster.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerTest {

    private Map<Integer, List<Gedachte>> gedachtenMap1 = null;

    private Gedachte[] getGedachtenArray() {
        int max = Inspiratie.MAX + 1;
        int size = max * max;
        Gedachte[] gedachten = new Gedachte[size];
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        for (int i = 0; i < size; i++) {
            if (i % max != 0) {
                Persoonlijkheid mening = new Persoonlijkheid(0, 0);
                gedachten[i] = new Gedachte(inspiratie.inspireerMij(), mening);
            }
        }
        return gedachten;
    }

    private Map<Integer, List<Gedachte>> getGedachtenMap() {
        Gedachte[] gedachten = getGedachtenArray();
        GedachteComparatorOpBasisVanConcept comparator = new GedachteComparatorOpBasisVanConcept();
        Map<Integer, List<Gedachte>> gedachtenMap = new LinkedHashMap<>();
        Arrays.sort(gedachten, comparator);
        for (Gedachte gedachte : gedachten) {
            if (gedachte != null) {
                int concept = gedachte.getConcept();
                List<Gedachte> gedachtenList = gedachtenMap.get(gedachte.getConcept());
                if (gedachtenList == null) {
                    gedachtenList = new ArrayList<>();
                }
                gedachtenList.add(gedachte);
                gedachtenMap.put(concept, gedachtenList);
            }
        }
        return gedachtenMap;
    }

    @Before
    public void before() {
        gedachtenMap1 = getGedachtenMap();
    }

    @Test
    public void getGedachtenMap_geeft_correcte_map_van_gedachten_terug() {
        Gedachte[] gedachten = getGedachtenArray();
        Map<Integer, List<Gedachte>> gedachtenMap2
                = GedachtenOptimizer.getGedachtenMap(gedachten);
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap1.entrySet()) {
            List<Gedachte> gedachtenList1 = entry.getValue();
            List<Gedachte> gedachtenList2 = gedachtenMap2.get(entry.getKey());
            Gedachte[] gedachten1 = gedachtenList1
                    .toArray(new Gedachte[gedachtenList1.size()]);
            Gedachte[] gedachten2 = gedachtenList2
                    .toArray(new Gedachte[gedachtenList2.size()]);
            assertArrayEquals(gedachten1, gedachten2);
        }
    }

    @Test
    public void getGedachtenMap_geeft_een_map_met_het_juist_aantal_gedachten_terug() {
        int max = Inspiratie.MAX + 1;
        int size = max * max;
        int grootteGedachtenArray = getGedachtenArray().length;
        int aantalNullGedachten = size - (size - max);
        int aantalGedachtenInMap = 0;
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap1.entrySet()) {
            int listSize = entry.getValue().size();
            assertEquals(max, listSize);
            aantalGedachtenInMap += listSize;
        }
        assertEquals(aantalGedachtenInMap, grootteGedachtenArray - aantalNullGedachten);
    }

    @Test
    public void getGedachtenMap_geeft_gedachten_terug_geclasseerd_in_een_map_per_concept_als_key() {
        Gedachte[] gedachten = getGedachtenArray();
        Map<Integer, List<Gedachte>> gedachtenMap2
                = GedachtenOptimizer.getGedachtenMap(gedachten);
        int current = 0;
        for (Map.Entry<Integer, List<Gedachte>> entry : gedachtenMap2.entrySet()) {
            assertTrue(entry.getKey() == ++current);
            List<Gedachte> gedachtenList = entry.getValue();
            for (int i = 0; i < gedachtenList.size(); i++) {
                assertEquals(entry.getKey(),
                        Integer.valueOf(gedachtenList.get(i).getConcept()));
            }
        }
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            List<Gedachte> gedachtenList = gedachtenMap2.get(concept);
            for (int i = 0; i < gedachtenList.size(); i++) {
                assertEquals(concept, gedachtenList.get(i).getConcept());
            }
        }
    }

}
