package be.howest.klooster.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class GedachteComparatorOpBasisVanCreativiteitTest {

    private Persoonlijkheid mening1 = null;
    private Gedachte gedachte = null;
    private GedachteComparatorOpBasisVanCreativiteit comparator = null;

    private Gedachte[] getGedachtenArray() {
        int max = Persoonlijkheid.MAX_CREATIVITEIT + 1;
        int step = max / 10;
        int size = max + step;
        Gedachte[] gedachten = new Gedachte[size];
        int creativiteit = Persoonlijkheid.MAX_CREATIVITEIT;
        Inspiratie inspiratie = Inspiratie.getInstance();
        for (int i = Persoonlijkheid.MIN_CREATIVITEIT; i < size; i++) {
            if (i % step != 0) {
                Persoonlijkheid mening = new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID, creativiteit);
                gedachten[i] = new Gedachte(inspiratie.inspireerMij(), mening);
                creativiteit--;
            }
        }
        return gedachten;
    }

    private List<Gedachte> getGedachtenList() {
        return Arrays.asList(getGedachtenArray());
    }

    @Before
    public void before() {
        mening1 = new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID, 5);
        gedachte = new Gedachte(1, mening1);
        comparator = new GedachteComparatorOpBasisVanCreativiteit();
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
    public void compare_geeft_de_negatieve_waarde_van_de_creativiteit_van_de_eerste_gedachte_als_alleen_de_tweede_gedachte_null_is() {
        assertEquals(0 - gedachte.getCreativiteit(), comparator.compare(gedachte, null));
    }

    @Test
    public void compare_geeft_een_hogere_waarde_terug_dan_de_maximale_creativiteitswaarde_als_alleen_de_eerste_gedachte_null_is() {
        assertEquals(Persoonlijkheid.MAX_CREATIVITEIT + 1, comparator.compare(null, gedachte));
    }

    @Test
    public void compare_geeft_0_terug_bij_twee_gedachten_met_dezelfde_creativiteitswaarden() {
        Persoonlijkheid andereMening = new Persoonlijkheid(Persoonlijkheid.MAX_GOEDHEID, 5);
        Gedachte andereGedachte = new Gedachte(2, andereMening);
        assertEquals(0, comparator.compare(gedachte, andereGedachte));
        assertEquals(0, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void compare_geeft_1_of_min_1_terug_als_het_verschil_van_twee_creativiteitswaarden_van_twee_gedachten_1_of_min_1_is() {
        Persoonlijkheid andereMening = new Persoonlijkheid(Persoonlijkheid.MAX_GOEDHEID, 6);
        Gedachte andereGedachte = new Gedachte(2, andereMening);
        assertEquals(-1, comparator.compare(gedachte, andereGedachte));
        assertEquals(1, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void compare_geeft_2_of_min_2_terug_als_het_verschil_van_twee_creativiteitswaarden_van_twee_gedachten_2_of_min_2_is() {
        Persoonlijkheid andereMening = new Persoonlijkheid(Persoonlijkheid.MAX_GOEDHEID, 7);
        Gedachte andereGedachte = new Gedachte(2, andereMening);
        assertEquals(-2, comparator.compare(gedachte, andereGedachte));
        assertEquals(2, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void array_van_gedachten_wordt_correct_gesorteerd_op_basis_van_creativiteit() {
        int max = Persoonlijkheid.MAX_CREATIVITEIT + 1;
        int step = max / 10;
        int size = max + step;
        Gedachte[] gedachten = getGedachtenArray();

        Arrays.sort(gedachten, comparator);
        for (int i = 0; i < max - 1; i++) {
            assertEquals(i + 1, gedachten[i].getCreativiteit());
        }
        for (int i = max - 1; i < size; i++) {
            assertNull(gedachten[i]);
        }
    }

    @Test
    public void ArrayList_van_gedachten_wordt_correct_gesorteerd_op_basis_van_creativiteit() {
        int max = Persoonlijkheid.MAX_CREATIVITEIT + 1;
        int step = max / 10;
        int size = max + step;
        List<Gedachte> gedachten = getGedachtenList();

        Collections.sort(gedachten, comparator);
        for (int i = 0; i < max - 1; i++) {
            assertEquals(i + 1, gedachten.get(i).getCreativiteit());
        }
        for (int i = max - 1; i < size; i++) {
            assertNull(gedachten.get(i));
        }
    }

    @Test
    public void TreeSet_van_gedachten_wordt_correct_gesorteerd_op_basis_van_creativiteit() {
        Set<Gedachte> gedachtenSet = new TreeSet<>(comparator);
        gedachtenSet.addAll(getGedachtenList());
        Gedachte[] gedachten
                = gedachtenSet.toArray(new Gedachte[gedachtenSet.size()]);

        for (int i = 0; i < gedachten.length - 1; i++) {
            assertEquals(i + 1, gedachten[i].getCreativiteit());
        }
        assertNull(gedachten[gedachten.length - 1]);
    }

    @Test
    public void TreeSet_van_gedachten_wordt_correct_reversed_gesorteerd_op_basis_van_creativiteit() {
        int max = Persoonlijkheid.MAX_CREATIVITEIT + 1;
        Set<Gedachte> gedachtenSet = new TreeSet<>(comparator.reversed());
        gedachtenSet.addAll(getGedachtenList());
        Gedachte[] gedachten
                = gedachtenSet.toArray(new Gedachte[gedachtenSet.size()]);

        assertNull(gedachten[0]);
        for (int i = 1; i < gedachten.length; i++) {
            assertEquals(max - i, gedachten[i].getCreativiteit());
        }
    }
}
