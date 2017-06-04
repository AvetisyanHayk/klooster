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

}
