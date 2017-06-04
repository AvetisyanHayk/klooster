package be.howest.klooster.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class InspiratieTest {
    
    @Test
    public void inspireerMij_geeft_correcte_waarden() {
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, inspiratie.inspireerMij());
        }
        assertEquals(Inspiratie.MIN, inspiratie.inspireerMij());
    }
    
    @Test
    public void reset_werkt_correct() {
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        assertEquals(Inspiratie.MIN, inspiratie.inspireerMij());
        inspiratie.inspireerMij();
        inspiratie.inspireerMij();
        inspiratie.inspireerMij();
        assertNotEquals(Inspiratie.MIN, inspiratie.inspireerMij());
        inspiratie.reset();
        assertEquals(Inspiratie.MIN, inspiratie.inspireerMij());
    }

    @Test
    public void concept_is_niet_geldig_als_het_lagere_waarde_heeft_dan_min() {
        assertFalse(Inspiratie.isValidConcept(Inspiratie.MIN - 1));
    }
    
    @Test
    public void concept_is_niet_geldig_als_het_hogere_waarde_heeft_dan_max() {
        assertFalse(Inspiratie.isValidConcept(Inspiratie.MAX + 1));
    }
    
    @Test
    public void concept_is_geldig_als_het_binnen_de_scope_van_min_en_max_valt() {
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertTrue(Inspiratie.isValidConcept(concept));
        }
    }
}
