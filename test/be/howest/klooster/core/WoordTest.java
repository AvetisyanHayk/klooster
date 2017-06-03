package be.howest.klooster.core;

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
    
}
