package be.howest.klooster.core;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisVanGoedheidTest {
    
    private Gedachte[] gedachten = null;
    private GedachtenOptimizer optimizer = null;

    private void initGedachtenArray() {
        int size = Pater.MAX_GEDACHTEN;
        gedachten = new Gedachte[size];
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        int goedheid = 67;
        for (int i = 0; i < size; i++) {
            if (i != 12) {
                Persoonlijkheid mening = new Persoonlijkheid(goedheid, Persoonlijkheid.MIN_CREATIVITEIT);
                goedheid -= 2;
                gedachten[i] = new Gedachte(inspiratie.inspireerMij(), mening);
            }
        }
    }

    @Before
    public void before() {
        initGedachtenArray();
        optimizer = GedachtenOptimizerOpBasisVanGoedheid.getInstance();
    }

    @Test
    public void optimalizeerGedachten_optimaliseert_gedachten_correct_op_basis_van_goedheid_tov_goedheid_van_pater_1() {
        Persoonlijkheid persoonlijkheid
                = new Persoonlijkheid(20, Persoonlijkheid.MIN_CREATIVITEIT);
        Pater pater = new Pater(null, persoonlijkheid);
        pater.setGedachten(gedachten);
        int aantalGeoptimalizeerd = optimizer.optimaliseerGedachten(pater);
        assertEquals(10, aantalGeoptimalizeerd);
        Gedachte[] nagedacht = pater.getGedachten();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, nagedacht[concept - 1].getConcept());
        }
        assertEquals(31, nagedacht[0].getGoedheid());
        assertEquals(47, nagedacht[1].getGoedheid());
        assertEquals(45, nagedacht[2].getGoedheid());
        assertEquals(43, nagedacht[3].getGoedheid());
        assertEquals(41, nagedacht[4].getGoedheid());
        assertEquals(39, nagedacht[5].getGoedheid());
        assertEquals(37, nagedacht[6].getGoedheid());
        assertEquals(35, nagedacht[7].getGoedheid());
        assertEquals(33, nagedacht[8].getGoedheid());
        for (int index = Inspiratie.MAX; index < Pater.MAX_GEDACHTEN; index++) {
            assertNull(nagedacht[index]);
        }
    }
    
    @Test
    public void optimalizeerGedachten_optimaliseert_gedachten_correct_op_basis_van_goedheid_tov_goedheid_van_pater_2() {
        Persoonlijkheid persoonlijkheid
                = new Persoonlijkheid(40, Persoonlijkheid.MIN_CREATIVITEIT);
        Pater pater = new Pater(null, persoonlijkheid);
        pater.setGedachten(gedachten);
        int aantalGeoptimalizeerd = optimizer.optimaliseerGedachten(pater);
        assertEquals(10, aantalGeoptimalizeerd);
        Gedachte[] nagedacht = pater.getGedachten();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, nagedacht[concept - 1].getConcept());
        }
        assertEquals(49, nagedacht[0].getGoedheid());
        assertEquals(47, nagedacht[1].getGoedheid());
        assertEquals(45, nagedacht[2].getGoedheid());
        assertEquals(43, nagedacht[3].getGoedheid());
        assertEquals(41, nagedacht[4].getGoedheid());
        assertEquals(39, nagedacht[5].getGoedheid());
        assertEquals(37, nagedacht[6].getGoedheid());
        assertEquals(35, nagedacht[7].getGoedheid());
        assertEquals(33, nagedacht[8].getGoedheid());
        for (int index = Inspiratie.MAX; index < Pater.MAX_GEDACHTEN; index++) {
            assertNull(nagedacht[index]);
        }
    }
    
    @Test
    public void optimalizeerGedachten_optimaliseert_gedachten_correct_op_basis_van_goedheid_tov_goedheid_van_pater_3() {
        Persoonlijkheid persoonlijkheid
                = new Persoonlijkheid(65, Persoonlijkheid.MIN_CREATIVITEIT);
        Pater pater = new Pater(null, persoonlijkheid);
        pater.setGedachten(gedachten);
        int aantalGeoptimalizeerd = optimizer.optimaliseerGedachten(pater);
        assertEquals(10, aantalGeoptimalizeerd);
        Gedachte[] nagedacht = pater.getGedachten();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, nagedacht[concept - 1].getConcept());
        }
        assertEquals(67, nagedacht[0].getGoedheid());
        assertEquals(65, nagedacht[1].getGoedheid());
        assertEquals(63, nagedacht[2].getGoedheid());
        assertEquals(61, nagedacht[3].getGoedheid());
        assertEquals(59, nagedacht[4].getGoedheid());
        assertEquals(57, nagedacht[5].getGoedheid());
        assertEquals(55, nagedacht[6].getGoedheid());
        assertEquals(53, nagedacht[7].getGoedheid());
        assertEquals(51, nagedacht[8].getGoedheid());
        for (int index = Inspiratie.MAX; index < Pater.MAX_GEDACHTEN; index++) {
            assertNull(nagedacht[index]);
        }
    }
    
}
