package be.howest.klooster.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Hayk
 */
public class GedachteComparatorOpBasisVanConceptTest {

    private Persoonlijkheid mening1;
    private Persoonlijkheid mening2;
    private Gedachte gedachte;
    private GedachteComparatorOpBasisVanConcept comparator;

    private Gedachte[] getGedachtenArray() {
        int max = Inspiratie.MAX + 1;
        int size = max * max;
        Gedachte[] gedachten = new Gedachte[size];
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        for (int i = 0; i < size; i++) {
            if (i % max != 0) {
                gedachten[i] = new Gedachte(inspiratie.inspireerMij(), mening1);
            }
        }
        return gedachten;
    }

    private List<Gedachte> getGedachtenList() {
        return Arrays.asList(getGedachtenArray());
    }

    @Before
    public void before() {
        mening1 = Persoonlijkheid.createRandomPersoonlijkheid();
        mening2 = Persoonlijkheid.createRandomPersoonlijkheid();
        gedachte = new Gedachte(1, mening1);
        comparator = new GedachteComparatorOpBasisVanConcept();
    }

    @Test
    public void class_implements_comparator() {
        assertTrue(Comparator.class.isAssignableFrom(GedachteComparatorOpBasisVanConcept.class));
    }
    
    @Test
    public void compare_geeft_0_terug_als_beide_gedachten_null_zijn() {
        assertEquals(0, comparator.compare(null, null));
    }

    @Test
    public void compare_geeft_de_negatieve_waarde_van_het_concept_van_de_eerste_gedachte_als_alleen_de_tweede_gedachte_null_is() {
        assertEquals(0 - gedachte.getConcept(), comparator.compare(gedachte, null));
    }

    @Test
    public void compare_geeft_een_hogere_waarde_terug_dan_de_maximale_conceptwaarde_als_alleen_de_eerste_gedachte_null_is() {
        assertEquals(Inspiratie.MAX + 1, comparator.compare(null, gedachte));
    }

    

    @Test
    public void compare_geeft_0_terug_bij_twee_gedachten_over_hetzelfde_concept() {
        Gedachte andereGedachte = new Gedachte(1, mening2);
        assertEquals(0, comparator.compare(gedachte, andereGedachte));
        assertEquals(0, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void compare_geeft_1_of_min_1_terug_als_het_verschil_van_twee_conceptwaarden_van_twee_gedachten_1_of_min_1_zijn() {
        Gedachte andereGedachte = new Gedachte(2, mening2);
        assertEquals(-1, comparator.compare(gedachte, andereGedachte));
        assertEquals(1, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void compare_geeft_2_of_min_2_terug_als_het_verschil_van_twee_conceptwaarden_van_twee_gedachten_2_of_min_2_zijn() {
        Gedachte andereGedachte = new Gedachte(3, mening2);
        assertEquals(-2, comparator.compare(gedachte, andereGedachte));
        assertEquals(2, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void array_van_gedachten_wordt_correct_gesorteerd_op_basis_van_de_comparator() {
        int max = Inspiratie.MAX + 1;
        int size = max * max;
        Gedachte[] gedachten = getGedachtenArray();

        Arrays.sort(gedachten, comparator);
        for (int i = 0; i < size - max; i++) {
            assertEquals(i / max + 1, gedachten[i].getConcept());
        }
        for (int i = size - max; i < size; i++) {
            assertNull(gedachten[i]);
        }
    }

    @Test
    public void ArrayList_van_gedachten_wordt_correct_gesorteerd_op_basis_van_de_comparator() {
        int max = Inspiratie.MAX + 1;
        int size = max * max;
        List<Gedachte> gedachten = getGedachtenList();
        Collections.sort(gedachten, comparator);
        for (int i = 0; i < size - max; i++) {
            assertEquals(i / max + 1, gedachten.get(i).getConcept());
        }
        for (int i = size - max; i < size; i++) {
            assertNull(gedachten.get(i));
        }
    }

    @Test
    public void TreeSet_van_gedachten_wordt_correct_gesorteerd_op_basis_van_de_comparator() {
        int max = Inspiratie.MAX + 1;
        Set<Gedachte> gedachtenSet = new TreeSet<>(comparator);
        gedachtenSet.addAll(getGedachtenList());
        Gedachte[] gedachten
                = gedachtenSet.toArray(new Gedachte[gedachtenSet.size()]);

        for (int i = 0; i < gedachten.length - 1; i++) {
            assertEquals(i + 1, gedachten[i].getConcept());
        }
        assertNull(gedachten[gedachten.length - 1]);
    }

}
