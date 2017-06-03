package be.howest.klooster.core;

import java.util.Objects;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class WoordTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_een_null_argument_als_gedachte_werpt_exception() {
        assertNull(new Woord(null, new Persoonlijkheid(1, 1)).getGedachte());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_een_null_argument_als_begeestering_werpt_exception() {
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte = new Gedachte(1, persoonlijkheid);
        assertNull(new Woord(gedachte, null).getBegeestering());
    }
    
    @Test
    public void getGedachte_geeft_correcte_gedachte_terug() {
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid begeestering = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte = new Gedachte(1, persoonlijkheid);
        Woord woord = new Woord(gedachte, begeestering);
        assertEquals(gedachte, woord.getGedachte());
    }
    
    @Test
    public void getBegeestering_geeft_correcte_begeestering_terug() {
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid begeestering = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte = new Gedachte(1, persoonlijkheid);
        Woord woord = new Woord(gedachte, begeestering);
        assertEquals(begeestering, woord.getBegeestering());
    }
    
    @Test
    public void getConcept_geeft_het_correct_concept_terug() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid begeestering = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte = new Gedachte(concept, persoonlijkheid);
        Woord woord = new Woord(gedachte, begeestering);
        assertEquals(concept, woord.getConcept());
    }
    
    @Test
    public void twee_woorden_met_dezelfde_gedachten_en_begeesteringen_zijn_gelijk() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid begeestering = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte = new Gedachte(concept, persoonlijkheid);
        Woord woord1 = new Woord(gedachte, begeestering);
        Woord woord2 = new Woord(gedachte, begeestering);
        assertTrue(woord1.equals(woord2));
    }
    
    @Test
    public void twee_woorden_met_dezelfde_gedachten_en_begeesteringen_hebben_dezelfde_hashCode() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid begeestering = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte = new Gedachte(concept, persoonlijkheid);
        Woord woord1 = new Woord(gedachte, begeestering);
        Woord woord2 = new Woord(gedachte, begeestering);
        assertEquals(woord1.hashCode(), woord2.hashCode());
    }
    
    @Test
    public void twee_woorden_met_dezelfde_gedachten_en_verschillende_begeesteringen_zijn_niet_gelijk() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid begeestering1 = new Persoonlijkheid(33, 59);
        Persoonlijkheid begeestering2 = new Persoonlijkheid(22, 49);
        Gedachte gedachte = new Gedachte(concept, persoonlijkheid);
        Woord woord1 = new Woord(gedachte, begeestering1);
        Woord woord2 = new Woord(gedachte, begeestering2);
        assertFalse(woord1.equals(woord2));
    }
    
    @Test
    public void twee_woorden_met_dezelfde_gedachten_en_verschillende_begeesteringen_hebben_verschillende_hashCodes() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid begeestering1 = new Persoonlijkheid(33, 59);
        Persoonlijkheid begeestering2 = new Persoonlijkheid(22, 49);
        Gedachte gedachte = new Gedachte(concept, persoonlijkheid);
        Woord woord1 = new Woord(gedachte, begeestering1);
        Woord woord2 = new Woord(gedachte, begeestering2);
        assertNotEquals(woord1.hashCode(), woord2.hashCode());
    }
    
    @Test
    public void twee_woorden_met_verschillende_gedachten_en_dezelfde_begeestering_zijn_niet_gelijk() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(33, 59);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(22, 49);
        Persoonlijkheid begeestering = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte1 = new Gedachte(concept, persoonlijkheid1);
        Gedachte gedachte2 = new Gedachte(concept, persoonlijkheid2);
        Woord woord1 = new Woord(gedachte1, begeestering);
        Woord woord2 = new Woord(gedachte2, begeestering);
        assertFalse(woord1.equals(woord2));
    }
    
    @Test
    public void twee_woorden_met_verschillende_gedachten_en_dezelfde_begeestering_hebben_verschillende_hashCodes() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(33, 59);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(22, 49);
        Persoonlijkheid begeestering = Persoonlijkheid.createRandomPersoonlijkheid();
        Gedachte gedachte1 = new Gedachte(concept, persoonlijkheid1);
        Gedachte gedachte2 = new Gedachte(concept, persoonlijkheid2);
        Woord woord1 = new Woord(gedachte1, begeestering);
        Woord woord2 = new Woord(gedachte2, begeestering);
        assertNotEquals(woord1.hashCode(), woord2.hashCode());
    }
    
    @Test
    public void twee_verschillende_woorden_zijn_niet_gelijk() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(33, 59);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(22, 49);
        Persoonlijkheid begeestering1 = new Persoonlijkheid(8, 68);
        Persoonlijkheid begeestering2 = new Persoonlijkheid(80, 6);
        Gedachte gedachte1 = new Gedachte(concept, persoonlijkheid1);
        Gedachte gedachte2 = new Gedachte(concept, persoonlijkheid2);
        Woord woord1 = new Woord(gedachte1, begeestering1);
        Woord woord2 = new Woord(gedachte2, begeestering2);
        assertFalse(woord1.equals(woord2));
    }
    
    @Test
    public void twee_verschillende_woorden_zijn_hebben_verschillende_hashCodes() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(33, 59);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(22, 49);
        Persoonlijkheid begeestering1 = new Persoonlijkheid(8, 68);
        Persoonlijkheid begeestering2 = new Persoonlijkheid(80, 6);
        Gedachte gedachte1 = new Gedachte(concept, persoonlijkheid1);
        Gedachte gedachte2 = new Gedachte(concept, persoonlijkheid2);
        Woord woord1 = new Woord(gedachte1, begeestering1);
        Woord woord2 = new Woord(gedachte2, begeestering2);
        assertNotEquals(woord1.hashCode(), woord2.hashCode());
    }
}
