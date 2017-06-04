package be.howest.klooster.core;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisVanCreativiteitTest {

    private Gedachte[] gedachten = null;
    private GedachtenOptimizer optimizer = null;

    private void initGedachtenArray() {
        int size = Pater.MAX_GEDACHTEN;
        gedachten = new Gedachte[size];
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        int creativiteit = 67;
        for (int i = 0; i < size; i++) {
            if (i != 12) {
                Persoonlijkheid mening = new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID, creativiteit);
                creativiteit -= 2;
                gedachten[i] = new Gedachte(inspiratie.inspireerMij(), mening);
            }
        }
    }

    @Before
    public void before() {
        initGedachtenArray();
        optimizer = GedachtenOptimizerOpBasisVanCreativiteit.getInstance();
    }

    @Test
    public void optimalizeerGedachten_optimaliseert_gedachten_correct_op_basis_van_creativiteit_tov_creativiteit_van_pater_1() {
        Persoonlijkheid persoonlijkheid
                = new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID, 20);
        Pater pater = new Pater(null, persoonlijkheid);
        pater.setGedachten(gedachten);
        int aantalGeoptimalizeerd = optimizer.optimaliseerGedachten(pater);
        assertEquals(10, aantalGeoptimalizeerd);
        Gedachte[] nagedacht = pater.getGedachten();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, nagedacht[concept - 1].getConcept());
        }
        assertEquals(31, nagedacht[0].getCreativiteit());
        assertEquals(47, nagedacht[1].getCreativiteit());
        assertEquals(45, nagedacht[2].getCreativiteit());
        assertEquals(43, nagedacht[3].getCreativiteit());
        assertEquals(41, nagedacht[4].getCreativiteit());
        assertEquals(39, nagedacht[5].getCreativiteit());
        assertEquals(37, nagedacht[6].getCreativiteit());
        assertEquals(35, nagedacht[7].getCreativiteit());
        assertEquals(33, nagedacht[8].getCreativiteit());
        for (int index = Inspiratie.MAX; index < Pater.MAX_GEDACHTEN; index++) {
            assertNull(nagedacht[index]);
        }
    }
    
    @Test
    public void optimalizeerGedachten_optimaliseert_gedachten_correct_op_basis_van_creativiteit_tov_creativiteit_van_pater_2() {
        Persoonlijkheid persoonlijkheid
                = new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID, 40);
        Pater pater = new Pater(null, persoonlijkheid);
        pater.setGedachten(gedachten);
        int aantalGeoptimalizeerd = optimizer.optimaliseerGedachten(pater);
        assertEquals(10, aantalGeoptimalizeerd);
        Gedachte[] nagedacht = pater.getGedachten();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, nagedacht[concept - 1].getConcept());
        }
        assertEquals(49, nagedacht[0].getCreativiteit());
        assertEquals(47, nagedacht[1].getCreativiteit());
        assertEquals(45, nagedacht[2].getCreativiteit());
        assertEquals(43, nagedacht[3].getCreativiteit());
        assertEquals(41, nagedacht[4].getCreativiteit());
        assertEquals(39, nagedacht[5].getCreativiteit());
        assertEquals(37, nagedacht[6].getCreativiteit());
        assertEquals(35, nagedacht[7].getCreativiteit());
        assertEquals(33, nagedacht[8].getCreativiteit());
        for (int index = Inspiratie.MAX; index < Pater.MAX_GEDACHTEN; index++) {
            assertNull(nagedacht[index]);
        }
    }
    
    @Test
    public void optimalizeerGedachten_optimaliseert_gedachten_correct_op_basis_van_creativiteit_tov_creativiteit_van_pater_3() {
        Persoonlijkheid persoonlijkheid
                = new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID, 65);
        Pater pater = new Pater(null, persoonlijkheid);
        pater.setGedachten(gedachten);
        int aantalGeoptimalizeerd = optimizer.optimaliseerGedachten(pater);
        assertEquals(10, aantalGeoptimalizeerd);
        Gedachte[] nagedacht = pater.getGedachten();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, nagedacht[concept - 1].getConcept());
        }
        assertEquals(67, nagedacht[0].getCreativiteit());
        assertEquals(65, nagedacht[1].getCreativiteit());
        assertEquals(63, nagedacht[2].getCreativiteit());
        assertEquals(61, nagedacht[3].getCreativiteit());
        assertEquals(59, nagedacht[4].getCreativiteit());
        assertEquals(57, nagedacht[5].getCreativiteit());
        assertEquals(55, nagedacht[6].getCreativiteit());
        assertEquals(53, nagedacht[7].getCreativiteit());
        assertEquals(51, nagedacht[8].getCreativiteit());
        for (int index = Inspiratie.MAX; index < Pater.MAX_GEDACHTEN; index++) {
            assertNull(nagedacht[index]);
        }
    }
}
