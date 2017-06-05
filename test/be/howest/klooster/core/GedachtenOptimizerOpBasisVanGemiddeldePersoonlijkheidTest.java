/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.klooster.core;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheidTest {
    
    private Gedachte[] gedachten = null;
    private GedachtenOptimizer optimizer = null;

    private void initGedachtenArray() {
        int size = Pater.MAX_GEDACHTEN;
        gedachten = new Gedachte[size];
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        int creativiteit = 67;
        int goedheid = 12;
        for (int i = 0; i < size; i++) {
            if (i != 12) {
                Persoonlijkheid mening = new Persoonlijkheid(goedheid, creativiteit);
                creativiteit -= 2;
                goedheid += 3;
                gedachten[i] = new Gedachte(inspiratie.inspireerMij(), mening);
            }
        }
    }

    @Before
    public void before() {
        initGedachtenArray();
        optimizer = GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid.getInstance();
    }

    @Test
    public void optimalizeerGedachten_optimaliseert_gedachten_correct_op_basis_van_creativiteit_tov_creativiteit_van_pater_1() {
        Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        Pater pater = new Pater(null, persoonlijkheid);
        pater.setGedachten(gedachten);
        int aantalGeoptimalizeerd = optimizer.optimaliseerGedachten(pater);
        assertEquals(10, aantalGeoptimalizeerd);
        Gedachte[] nagedacht = pater.getGedachten();
        for (int concept = Inspiratie.MIN; concept <= Inspiratie.MAX; concept++) {
            assertEquals(concept, nagedacht[concept - 1].getConcept());
        }
        assertEquals(new Persoonlijkheid(39, 49), nagedacht[0].getMening());
        assertEquals(new Persoonlijkheid(28, 56), nagedacht[1].getMening());
        assertEquals(new Persoonlijkheid(31, 54), nagedacht[2].getMening());
        assertEquals(new Persoonlijkheid(34, 52), nagedacht[3].getMening());
        assertEquals(new Persoonlijkheid(37, 50), nagedacht[4].getMening());
        assertEquals(new Persoonlijkheid(40, 48), nagedacht[5].getMening());
        assertEquals(new Persoonlijkheid(43, 46), nagedacht[6].getMening());
        assertEquals(new Persoonlijkheid(46, 44), nagedacht[7].getMening());
        assertEquals(new Persoonlijkheid(49, 42), nagedacht[8].getMening());
        for (int index = Inspiratie.MAX; index < nagedacht.length; index++) {
            assertNull(nagedacht[index]);
        }
    }
    
}
