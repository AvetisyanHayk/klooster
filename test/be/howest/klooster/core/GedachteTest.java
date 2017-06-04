package be.howest.klooster.core;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class GedachteTest {
    
    private Set<Gedachte>getGedachtenSet() {
        int size = Pater.MAX_GEDACHTEN;
        Set<Gedachte> gedachten = new LinkedHashSet<>();
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        int creativiteit = 67;
        int goedheid = 12;
        for (int i = 0; i < size; i++) {
            if (i != 12) {
                Persoonlijkheid mening = new Persoonlijkheid(goedheid, creativiteit);
                creativiteit -= 2;
                goedheid += 3;
                gedachten.add(new Gedachte(inspiratie.inspireerMij(), mening));
            }
        }
        return gedachten;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_verkeerde_concept_waarde_werpt_exception() {
        int minmin = Inspiratie.MIN - 1;
        int maxplus = Inspiratie.MAX + 1;
        assertEquals(minmin, new Gedachte(minmin, new Persoonlijkheid(1, 1)).getConcept());
        assertEquals(maxplus, new Gedachte(maxplus, new Persoonlijkheid(1, 1)).getConcept());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_een_null_argument_als_Mening_werpt_exception() {
        assertNull(new Gedachte(1, null).getConcept());
    }

    @Test
    public void getConcept_geeft_correcte_conceptwaarden_terug() {
        assertEquals(1, new Gedachte(1, new Persoonlijkheid(1, 1)).getConcept());
        assertEquals(5, new Gedachte(5, new Persoonlijkheid(1, 1)).getConcept());
        assertEquals(9, new Gedachte(9, new Persoonlijkheid(1, 1)).getConcept());
    }
    
    @Test
    public void getMening_geeft_correcte_Mening_terug() {
        Persoonlijkheid mening = new Persoonlijkheid(1, 1);
        assertEquals(mening, new Gedachte(1, mening).getMening());
    }
    
    @Test
    public void getGoedheid_geeft_correcte_Goedheid_van_mening_terug() {
        Persoonlijkheid mening = new Persoonlijkheid(24, 75);
        assertEquals(24, new Gedachte(1, mening).getGoedheid());
    }
    
    @Test
    public void getCreativiteit_geeft_correcte_Creativiteit_van_mening_terug() {
        Persoonlijkheid mening = new Persoonlijkheid(24, 75);
        assertEquals(75, new Gedachte(1, mening).getCreativiteit());
    }
    
    @Test
    public void twee_gedachtes_over_hetzelfde_concept_en_met_dezelfde_mening_zijn_gelijk() {
        int concept = 3;
        Persoonlijkheid mening1 = new Persoonlijkheid(22, 59);
        Persoonlijkheid mening2 = new Persoonlijkheid(22, 59);
        Gedachte gedachte1 = new Gedachte(concept, mening1);
        Gedachte gedachte2 = new Gedachte(concept, mening2);
        assertTrue(Objects.equals(gedachte1, gedachte2));
    }
    
    @Test
    public void twee_gedachtes_over_hetzelfde_concept_en_met_dezelfde_mening_hebben_dezelfde_hashCode() {
        int concept = 3;
        Persoonlijkheid mening1 = new Persoonlijkheid(22, 59);
        Persoonlijkheid mening2 = new Persoonlijkheid(22, 59);
        Gedachte gedachte1 = new Gedachte(concept, mening1);
        Gedachte gedachte2 = new Gedachte(concept, mening2);
        assertEquals(gedachte1.hashCode(), gedachte2.hashCode());
    }
    
    @Test
    public void twee_gedachtes_over_hetzelfde_concept_maar_met_verschillende_meningen_zijn_niet_gelijk() {
        int concept = 3;
        Persoonlijkheid mening1 = new Persoonlijkheid(22, 59);
        Persoonlijkheid mening2 = new Persoonlijkheid(33, 49);
        Gedachte gedachte1 = new Gedachte(concept, mening1);
        Gedachte gedachte2 = new Gedachte(concept, mening2);
        assertFalse(Objects.equals(gedachte1, gedachte2));
    }
    
    @Test
    public void twee_gedachtes_over_hetzelfde_concept_maar_met_verschillende_meningen_hebben_verschillende_hashCodes() {
        int concept = 3;
        Persoonlijkheid mening1 = new Persoonlijkheid(22, 59);
        Persoonlijkheid mening2 = new Persoonlijkheid(33, 49);
        Gedachte gedachte1 = new Gedachte(concept, mening1);
        Gedachte gedachte2 = new Gedachte(concept, mening2);
        assertNotEquals(gedachte1.hashCode(), gedachte2.hashCode());
    }
    
    @Test
    public void twee_gedachtes_over_verschillende_concepten_maar_met_dezelfde_mening_zijn_niet_gelijk() {
        int concept1 = 1;
        int concept2 = 2;
        Persoonlijkheid mening = new Persoonlijkheid(22, 59);
        Gedachte gedachte1 = new Gedachte(concept1, mening);
        Gedachte gedachte2 = new Gedachte(concept2, mening);
        assertFalse(Objects.equals(gedachte1, gedachte2));
    }
    
    @Test
    public void twee_gedachtes_over_verschillende_concepten_maar_met_dezelfde_mening_hebben_verschillende_hashCodes() {
        int concept1 = 1;
        int concept2 = 2;
        Persoonlijkheid mening = new Persoonlijkheid(22, 59);
        Gedachte gedachte1 = new Gedachte(concept1, mening);
        Gedachte gedachte2 = new Gedachte(concept2, mening);
        assertNotEquals(gedachte1.hashCode(), gedachte2.hashCode());
    }
    
    @Test
    public void twee_verschillende_gedachten_zijn_niet_gelijk() {
        int concept1 = 1;
        int concept2 = 2;
        Persoonlijkheid mening1 = new Persoonlijkheid(22, 59);
        Persoonlijkheid mening2 = new Persoonlijkheid(22, 59);
        Gedachte gedachte1 = new Gedachte(concept1, mening1);
        Gedachte gedachte2 = new Gedachte(concept2, mening2);
        assertFalse(Objects.equals(gedachte1, gedachte2));
    }
    
    @Test
    public void twee_verschillende_gedachten_hebben_verschillende_hashCodes() {
        int concept1 = 1;
        int concept2 = 2;
        Persoonlijkheid mening1 = new Persoonlijkheid(22, 59);
        Persoonlijkheid mening2 = new Persoonlijkheid(22, 59);
        Gedachte gedachte1 = new Gedachte(concept1, mening1);
        Gedachte gedachte2 = new Gedachte(concept2, mening2);
        assertNotEquals(gedachte1.hashCode(), gedachte2.hashCode());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verwoord_werpt_exception_bij_null_argument_als_begeestering() {
        Persoonlijkheid mening = Persoonlijkheid.createRandomPersoonlijkheid();
        int concept = Inspiratie.getInstance().inspireerMij();
        Gedachte gedachte = new Gedachte(concept, mening);
        assertNull(null, gedachte.verwoord(null));
    }
    
    @Test
    public void verwoord_geeft_het_juiste_woord_terug() {
        Persoonlijkheid mening = Persoonlijkheid.createRandomPersoonlijkheid();
        int concept = Inspiratie.getInstance().inspireerMij();
        Gedachte gedachte = new Gedachte(concept, mening);
        Woord woord = new Woord(gedachte, mening);
        assertEquals(woord, gedachte.verwoord(mening));
    }
    
    @Test
    public void mapMeningenUitGedachten_geeft_correcte_meningen_terug() {
        Set<Gedachte> gedachten = getGedachtenSet();
        Set<Persoonlijkheid> persoonlijkheden1 = new TreeSet<>(new PersoonlijkheidComparator());
        gedachten.forEach(gedachte -> {
            persoonlijkheden1.add(gedachte.getMening());
        });
        Set<Persoonlijkheid> persoonlijkheden2 = Gedachte.mapMeningenUitGedachten(gedachten);
        assertEquals(persoonlijkheden1.size(), persoonlijkheden2.size());
        for (int i = 0; i < persoonlijkheden1.size(); i++) {
            persoonlijkheden1.containsAll(persoonlijkheden2);
        }
    }
    
    @Test
    public void mapMeningenUitGedachten_geeft_null_terug_als_gedachten_null_zijn() {
        assertNull(Gedachte.mapMeningenUitGedachten(null));
    }
}
