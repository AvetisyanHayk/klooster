package be.howest.klooster.core;

import java.io.Serializable;
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
public class GedachteComparatorOpBasisVanGoedheidTest {
    private Persoonlijkheid mening1 = null;
    private Gedachte gedachte = null;
    private GedachteComparatorOpBasisVanGoedheid comparator = null;

    private Gedachte[] getGedachtenArray() {
        int max = Persoonlijkheid.MAX_GOEDHEID + 1;
        int step = max / 10;
        int size = max + step;
        Gedachte[] gedachten = new Gedachte[size];
        int goedheid = Persoonlijkheid.MAX_GOEDHEID;
        Inspiratie inspiratie = Inspiratie.getInstance();
        for (int i = Persoonlijkheid.MIN_GOEDHEID; i < size; i++) {
            if (i % step != 0) {
                Persoonlijkheid mening = new Persoonlijkheid(goedheid, Persoonlijkheid.MIN_CREATIVITEIT);
                gedachten[i] = new Gedachte(inspiratie.inspireerMij(), mening);
                goedheid--;
            }
        }
        return gedachten;
    }

    private List<Gedachte> getGedachtenList() {
        return Arrays.asList(getGedachtenArray());
    }

    private Gedachte[] getSpecifiekeGedachtenArray() {
        int minCreativiteit = Persoonlijkheid.MIN_CREATIVITEIT;
        Inspiratie inspiratie = Inspiratie.getInstance();
        Persoonlijkheid specMening1 = new Persoonlijkheid(2, minCreativiteit);
        Persoonlijkheid specMening2 = new Persoonlijkheid(4, minCreativiteit);
        Persoonlijkheid specMening3 = new Persoonlijkheid(44, minCreativiteit);
        Persoonlijkheid specMening4 = new Persoonlijkheid(41, minCreativiteit);
        Persoonlijkheid specMening6 = new Persoonlijkheid(39, minCreativiteit);
        Persoonlijkheid specMening7 = new Persoonlijkheid(41, minCreativiteit);
        Persoonlijkheid specMening9 = new Persoonlijkheid(0, minCreativiteit);
        Gedachte[] gedachten = new Gedachte[10];
        gedachten[1] = new Gedachte(inspiratie.inspireerMij(), specMening1);
        gedachten[2] = new Gedachte(inspiratie.inspireerMij(), specMening2);
        gedachten[3] = new Gedachte(inspiratie.inspireerMij(), specMening3);
        gedachten[4] = new Gedachte(inspiratie.inspireerMij(), specMening4);
        gedachten[6] = new Gedachte(inspiratie.inspireerMij(), specMening6);
        gedachten[7] = new Gedachte(inspiratie.inspireerMij(), specMening7);
        gedachten[9] = new Gedachte(inspiratie.inspireerMij(), specMening9);
        return gedachten;
    }

    private List<Gedachte> getSpecifiekeGedachtenList() {
        return Arrays.asList(getSpecifiekeGedachtenArray());
    }

    @Before
    public void before() {
        mening1 = new Persoonlijkheid(5, Persoonlijkheid.MIN_CREATIVITEIT);
        gedachte = new Gedachte(1, mening1);
        comparator = new GedachteComparatorOpBasisVanGoedheid();
    }

    @Test
    public void class_implements_comparator() {
        assertTrue(Comparator.class
                .isAssignableFrom(GedachteComparatorOpBasisVanGoedheid.class));
    }

    @Test
    public void class_implements_serializable() {
        assertTrue(Serializable.class
                .isAssignableFrom(GedachteComparatorOpBasisVanGoedheid.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_goedheidswaarde_als_argument_die_lager_ligt_dan_min_werpt_exception() {
        assertNotNull(new GedachteComparatorOpBasisVanGoedheid(
                Persoonlijkheid.MIN_GOEDHEID - 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_goedheidswaarde_als_argument_die_hoger_ligt_dan_max_werpt_exception() {
        assertNotNull(new GedachteComparatorOpBasisVanGoedheid(
                Persoonlijkheid.MAX_GOEDHEID + 1));
    }

    @Test
    public void constructor_met_goedheidswaarde_als_argument_binnen_de_scope_van_max_en_min_is_toegelaten() {
        for (int goedheid = Persoonlijkheid.MIN_GOEDHEID;
                goedheid <= Persoonlijkheid.MAX_GOEDHEID;
                goedheid++) {
            assertNotNull(new GedachteComparatorOpBasisVanGoedheid(
                    goedheid));
        }
    }

    @Test
    public void compare_geeft_0_terug_als_beide_gedachten_null_zijn() {
        assertEquals(0, comparator.compare(null, null));
    }

    @Test
    public void compare_geeft_een_lager_dan_de_negatieve_waarde_van_de_goedheid_van_de_eerste_gedachte_als_alleen_de_tweede_gedachte_null_is() {
        assertEquals(0 - gedachte.getGoedheid()- 1, comparator.compare(gedachte, null));
    }

    @Test
    public void compare_geeft_een_hogere_waarde_terug_dan_de_maximale_goedheidswaarde_als_alleen_de_eerste_gedachte_null_is() {
        assertEquals(Persoonlijkheid.MAX_GOEDHEID + 1, comparator.compare(null, gedachte));
    }

    @Test
    public void compare_geeft_0_terug_bij_twee_gedachten_met_dezelfde_goedheidswaarden() {
        Persoonlijkheid andereMening = new Persoonlijkheid(5, Persoonlijkheid.MAX_CREATIVITEIT);
        Gedachte andereGedachte = new Gedachte(2, andereMening);
        assertEquals(0, comparator.compare(gedachte, andereGedachte));
        assertEquals(0, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void compare_geeft_1_of_min_1_terug_als_het_verschil_van_twee_goedheidswaarden_van_twee_gedachten_1_of_min_1_is() {
        Persoonlijkheid andereMening = new Persoonlijkheid(6, Persoonlijkheid.MAX_CREATIVITEIT);
        Gedachte andereGedachte = new Gedachte(2, andereMening);
        assertEquals(-1, comparator.compare(gedachte, andereGedachte));
        assertEquals(1, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void compare_geeft_2_of_min_2_terug_als_het_verschil_van_twee_goedheidswaarden_van_twee_gedachten_2_of_min_2_is() {
        Persoonlijkheid andereMening = new Persoonlijkheid(7, Persoonlijkheid.MAX_CREATIVITEIT);
        Gedachte andereGedachte = new Gedachte(2, andereMening);
        assertEquals(-2, comparator.compare(gedachte, andereGedachte));
        assertEquals(2, comparator.compare(andereGedachte, gedachte));
    }

    @Test
    public void array_van_gedachten_wordt_correct_gesorteerd_op_basis_van_goedheid() {
        int max = Persoonlijkheid.MAX_GOEDHEID + 1;
        int step = max / 10;
        int size = max + step;
        Gedachte[] gedachten = getGedachtenArray();

        Arrays.sort(gedachten, comparator);
        for (int i = 0; i < max - 1; i++) {
            assertEquals(i + 1, gedachten[i].getGoedheid());
        }
        for (int i = max - 1; i < size; i++) {
            assertNull(gedachten[i]);
        }
    }

    @Test
    public void ArrayList_van_gedachten_wordt_correct_gesorteerd_op_basis_van_goedheid() {
        int max = Persoonlijkheid.MAX_GOEDHEID + 1;
        int step = max / 10;
        int size = max + step;
        List<Gedachte> gedachten = getGedachtenList();

        Collections.sort(gedachten, comparator);
        for (int i = 0; i < max - 1; i++) {
            assertEquals(i + 1, gedachten.get(i).getGoedheid());
        }
        for (int i = max - 1; i < size; i++) {
            assertNull(gedachten.get(i));
        }
    }

    @Test
    public void TreeSet_van_gedachten_wordt_correct_gesorteerd_op_basis_van_goedheid() {
        Set<Gedachte> gedachtenSet = new TreeSet<>(comparator);
        gedachtenSet.addAll(getGedachtenList());
        Gedachte[] gedachten
                = gedachtenSet.toArray(new Gedachte[gedachtenSet.size()]);

        for (int i = 0; i < gedachten.length - 1; i++) {
            assertEquals(i + 1, gedachten[i].getGoedheid());
        }
        assertNull(gedachten[gedachten.length - 1]);
    }

    @Test
    public void TreeSet_van_gedachten_wordt_correct_reversed_gesorteerd_op_basis_van_goedheid() {
        int max = Persoonlijkheid.MAX_GOEDHEID + 1;
        Set<Gedachte> gedachtenSet = new TreeSet<>(comparator.reversed());
        gedachtenSet.addAll(getGedachtenList());
        Gedachte[] gedachten
                = gedachtenSet.toArray(new Gedachte[gedachtenSet.size()]);

        assertNull(gedachten[0]);
        for (int i = 1; i < gedachten.length; i++) {
            assertEquals(max - i, gedachten[i].getGoedheid());
        }
    }

    @Test
    public void array_van_gedachten_wordt_correct_gesorteerd_op_basis_van_goedheid_ten_opzichte_van_een_specifieke_goedheidswaarde() {
        int specGoedheid = 44;
        Gedachte[] gedachten = getSpecifiekeGedachtenArray();
        GedachteComparatorOpBasisVanGoedheid specComparator
                = new GedachteComparatorOpBasisVanGoedheid(specGoedheid);

        Arrays.sort(gedachten, specComparator);
        assertEquals(44, gedachten[0].getGoedheid());
        assertEquals(41, gedachten[1].getGoedheid());
        assertEquals(41, gedachten[2].getGoedheid());
        assertEquals(39, gedachten[3].getGoedheid());
        assertEquals(4, gedachten[4].getGoedheid());
        assertEquals(2, gedachten[5].getGoedheid());
        assertEquals(0, gedachten[6].getGoedheid());
        assertNull(gedachten[7]);
        assertNull(gedachten[8]);
        assertNull(gedachten[9]);
    }

    @Test
    public void ArrayList_van_gedachten_wordt_correct_gesorteerd_op_basis_van_goedheid_ten_opzichte_van_een_specifieke_goedheidswaarde() {
        int specGoedheid = 44;
        List<Gedachte> gedachten = getSpecifiekeGedachtenList();
        GedachteComparatorOpBasisVanGoedheid specComparator
                = new GedachteComparatorOpBasisVanGoedheid(specGoedheid);

        Collections.sort(gedachten, specComparator);
        assertEquals(44, gedachten.get(0).getGoedheid());
        assertEquals(41, gedachten.get(1).getGoedheid());
        assertEquals(41, gedachten.get(2).getGoedheid());
        assertEquals(39, gedachten.get(3).getGoedheid());
        assertEquals(4, gedachten.get(4).getGoedheid());
        assertEquals(2, gedachten.get(5).getGoedheid());
        assertEquals(0, gedachten.get(6).getGoedheid());
        assertNull(gedachten.get(7));
        assertNull(gedachten.get(8));
        assertNull(gedachten.get(9));
    }

    @Test
    public void TreeSet_van_gedachten_wordt_correct_gesorteerd_op_basis_van_goedheid_ten_opzichte_van_een_specifieke_goedheidswaarde() {
        int specGoedheid = 44;
        GedachteComparatorOpBasisVanGoedheid specComparator
                = new GedachteComparatorOpBasisVanGoedheid(specGoedheid);
        Set<Gedachte> gedachtenSet = new TreeSet<>(specComparator);
        gedachtenSet.addAll(getSpecifiekeGedachtenList());
        Gedachte[] gedachten
                = gedachtenSet.toArray(new Gedachte[gedachtenSet.size()]);

        assertEquals(44, gedachten[0].getGoedheid());
        assertEquals(41, gedachten[1].getGoedheid());
        assertEquals(39, gedachten[2].getGoedheid());
        assertEquals(4, gedachten[3].getGoedheid());
        assertEquals(2, gedachten[4].getGoedheid());
        assertEquals(0, gedachten[5].getGoedheid());
        assertNull(gedachten[6]);
    }

    @Test
    public void TreeSet_van_gedachten_wordt_correct_reversed_gesorteerd_op_basis_van_goedheid_ten_opzichte_van_een_specifieke_goedheidswaarde() {
        int specGoedheid = 44;
        GedachteComparatorOpBasisVanGoedheid specComparator
                = new GedachteComparatorOpBasisVanGoedheid(specGoedheid);
        Set<Gedachte> gedachtenSet = new TreeSet<>(specComparator.reversed());
        gedachtenSet.addAll(getSpecifiekeGedachtenList());
        Gedachte[] gedachten
                = gedachtenSet.toArray(new Gedachte[gedachtenSet.size()]);

        assertNull(gedachten[0]);
        assertEquals(0, gedachten[1].getGoedheid());
        assertEquals(2, gedachten[2].getGoedheid());
        assertEquals(4, gedachten[3].getGoedheid());
        assertEquals(39, gedachten[4].getGoedheid());
        assertEquals(41, gedachten[5].getGoedheid());
        assertEquals(44, gedachten[6].getGoedheid());
    }
}
