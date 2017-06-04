package be.howest.klooster.core;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hayk
 */
public class PersoonlijkheidComparatorTest {

    private PersoonlijkheidComparator comparator = null;
    private Persoonlijkheid persoonlijkheid = null;

    private Set<Persoonlijkheid> getPersoonlijkhedenSet() {
        int size = Pater.MAX_GEDACHTEN;
        Set<Persoonlijkheid> persoonlijkheden = new TreeSet<>(comparator);
        persoonlijkheden.add(new Persoonlijkheid(66, 30));
        int creativiteit = 67;
        int goedheid = 12;
        for (int i = 0; i < size; i++) {
            if (i != 12) {
                persoonlijkheden.add(new Persoonlijkheid(goedheid, creativiteit));
                creativiteit -= 2;
                goedheid += 3;
            }
        }
        return persoonlijkheden;
    }

    @Before
    public void before() {
        comparator = new PersoonlijkheidComparator();
        persoonlijkheid = new Persoonlijkheid(4, 8);
    }

    @Test
    public void class_implements_comparator() {
        assertTrue(Comparator.class
                .isAssignableFrom(PersoonlijkheidComparator.class));
    }

    @Test
    public void class_implements_serializable() {
        assertTrue(Serializable.class
                .isAssignableFrom(PersoonlijkheidComparator.class));
    }

    @Test
    public void compare_geeft_0_terug_als_beide_persoonlijkheden_null_zijn() {
        assertEquals(0, comparator.compare(null, null));
    }

    @Test
    public void compare_geeft_de_negatieve_waarde_van_de_goedheidswaarde_van_de_eerste_persoonlijkheid_als_alleen_de_tweede_persoonlijkheid_null_is() {
        assertEquals(0 - persoonlijkheid.getGoedheid(), comparator.compare(persoonlijkheid, null));
    }

    @Test
    public void compare_geeft_een_hogere_waarde_terug_dan_de_som_van_maximale_goedheids_en_creativiteitswaarden_als_alleen_de_eerste_persoonlijkheid_null_is() {
        assertEquals(Persoonlijkheid.MAX_GOEDHEID + Persoonlijkheid.MAX_CREATIVITEIT + 1, comparator.compare(null, persoonlijkheid));
    }

    @Test
    public void compare_geeft_0_terug_bij_als_twee_persoonlijkheden_gelijk_zijn_aan_elkaar() {
        Persoonlijkheid anderePersoonlijkheid = new Persoonlijkheid(4, 8);
        assertEquals(0, comparator.compare(persoonlijkheid, anderePersoonlijkheid));
        assertEquals(0, comparator.compare(anderePersoonlijkheid, persoonlijkheid));
    }

    @Test
    public void compare_geeft_1_of_min_1_terug_als_het_verschil_van_twee_goedheidswaarden_1_is() {
        Persoonlijkheid anderePersoonlijkheid = new Persoonlijkheid(5, 8);
        assertEquals(-1, comparator.compare(persoonlijkheid, anderePersoonlijkheid));
        assertEquals(1, comparator.compare(anderePersoonlijkheid, persoonlijkheid));
    }

    @Test
    public void compare_geeft_2_of_min_2_terug_als_het_verschil_van_twee_goedheidswaarden_2_is() {
        Persoonlijkheid anderePersoonlijkheid = new Persoonlijkheid(6, 8);
        assertEquals(-2, comparator.compare(persoonlijkheid, anderePersoonlijkheid));
        assertEquals(2, comparator.compare(anderePersoonlijkheid, persoonlijkheid));
    }

    @Test
    public void compare_geeft_1_of_min_1_terug_bij_dezelfde_goedheidswaarden_als_het_verschil_van_creativiteitswaarden_1_is() {
        Persoonlijkheid anderePersoonlijkheid = new Persoonlijkheid(4, 9);
        assertEquals(-1, comparator.compare(persoonlijkheid, anderePersoonlijkheid));
        assertEquals(1, comparator.compare(anderePersoonlijkheid, persoonlijkheid));
    }

    @Test
    public void compare_geeft_2_of_min_2_terug_bij_dezelfde_goedheidswaarden_als_het_verschil_van_creativiteitswaarden_2_is() {
        Persoonlijkheid anderePersoonlijkheid = new Persoonlijkheid(4, 10);
        assertEquals(-2, comparator.compare(persoonlijkheid, anderePersoonlijkheid));
        assertEquals(2, comparator.compare(anderePersoonlijkheid, persoonlijkheid));
    }

    @Test
    public void TreeSet_van_persoonlijkheden_wordt_correct_gesorteerd() {
        Set<Persoonlijkheid> persoonlijkheden = getPersoonlijkhedenSet();
        Persoonlijkheid[] persoonlijkhedenArray = persoonlijkheden.toArray(
                new Persoonlijkheid[persoonlijkheden.size()]);
        int goedheid = 12;
        int creativiteit = 67;
        for (int i = 0; i < persoonlijkhedenArray.length - 2; i++) {
            Persoonlijkheid item = new Persoonlijkheid(goedheid, creativiteit);
            assertTrue(Objects.equals(item, persoonlijkhedenArray[i]));
            goedheid += 3;
            creativiteit -= 2;
        }
        Persoonlijkheid item1 = new Persoonlijkheid(goedheid, 30);
        assertTrue(Objects.equals(item1, persoonlijkhedenArray[persoonlijkhedenArray.length - 2]));
        Persoonlijkheid item2 = new Persoonlijkheid(goedheid, creativiteit);
        assertTrue(Objects.equals(item2, persoonlijkhedenArray[persoonlijkhedenArray.length - 1]));
    }
}
