package be.howest.klooster.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class PersoonlijkheidTest {

    private List<Persoonlijkheid> getPersoonlijkhedenList() {
        int size = Pater.MAX_GEDACHTEN;
        List<Persoonlijkheid> persoonlijkheden = new ArrayList<>();
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

    @Test
    public void constructor_met_een_verkeerde_goedheidswaarde_corrigeert_de_goedheidswaarde() {
        assertEquals(Persoonlijkheid.MIN_GOEDHEID,
                new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID - 1, 25).getGoedheid());
        assertEquals(Persoonlijkheid.MAX_GOEDHEID,
                new Persoonlijkheid(Persoonlijkheid.MAX_GOEDHEID + 1, 25).getGoedheid());
    }

    @Test
    public void constructor_met_een_verkeerde_creativiteitswaarde_corrigeert_de_creativiteitswaarde() {
        assertEquals(Persoonlijkheid.MIN_CREATIVITEIT,
                new Persoonlijkheid(25, Persoonlijkheid.MIN_CREATIVITEIT).getCreativiteit());
        assertEquals(Persoonlijkheid.MAX_GOEDHEID,
                new Persoonlijkheid(25, Persoonlijkheid.MAX_CREATIVITEIT + 1).getCreativiteit());
    }

    @Test
    public void getGoedheid_geeft_correcte_Goedheid_terug() {
        assertEquals(Persoonlijkheid.MIN_GOEDHEID,
                new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID, 25).getGoedheid());
        assertEquals(50, new Persoonlijkheid(50, 25).getGoedheid());
        assertEquals(Persoonlijkheid.MAX_GOEDHEID,
                new Persoonlijkheid(Persoonlijkheid.MAX_GOEDHEID, 25).getGoedheid());
    }

    @Test
    public void getCreativiteit_geeft_correcte_Creativiteit_van_mening_terug() {
        assertEquals(Persoonlijkheid.MIN_CREATIVITEIT,
                new Persoonlijkheid(25, Persoonlijkheid.MIN_CREATIVITEIT).getCreativiteit());
        assertEquals(50, new Persoonlijkheid(25, 50).getCreativiteit());
        assertEquals(Persoonlijkheid.MAX_CREATIVITEIT,
                new Persoonlijkheid(25, Persoonlijkheid.MAX_CREATIVITEIT).getCreativiteit());
    }

    @Test
    public void twee_persoonlijkheden_met_dezelfde_goedheids_en_creativiteitswaarden_zijn_gelijk() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(25, 35);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(25, 35);
        assertTrue(persoonlijkheid1.equals(persoonlijkheid2));
    }

    @Test
    public void twee_persoonlijkheden_met_dezelfde_goedheids_en_creativiteitswaarden_hebben_dezelfde_hashCode() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(25, 35);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(25, 35);
        assertEquals(persoonlijkheid1.hashCode(), persoonlijkheid2.hashCode());
    }

    @Test
    public void een_persoonlijkheden_is_niet_null() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(25, 35);
        assertFalse(Objects.equals(persoonlijkheid1, null));
    }

    @Test
    public void twee_persoonlijkheden_met_verschillende_goedheidswaarden_zijn_niet_gelijk() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(24, 35);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(25, 35);
        assertFalse(persoonlijkheid1.equals(persoonlijkheid2));
    }

    @Test
    public void twee_persoonlijkheden_met_verschillende_creativiteitswaarden_zijn_niet_gelijk() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(25, 34);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(25, 35);
        assertFalse(persoonlijkheid1.equals(persoonlijkheid2));
    }

    @Test
    public void createRandomPersoonlijkheid_geeft_correcte_persoonlijkheid_terug() {
        for (int i = 0; i < 1000; i++) {
            Persoonlijkheid persoonlijkheid =
                    Persoonlijkheid.createRandomPersoonlijkheid();
            assertTrue(Persoonlijkheid.isValidPersoonlijkheid(persoonlijkheid));
        }

    }

    @Test
    public void goedheid_is_niet_geldig_als_het_lagere_waarde_heeft_dan_min() {
        assertFalse(Persoonlijkheid
                .isValidGoedheid(Persoonlijkheid.MIN_GOEDHEID - 1));
    }

    @Test
    public void goedheid_is_niet_geldig_als_het_hogere_waarde_heeft_dan_max() {
        assertFalse(Persoonlijkheid
                .isValidGoedheid(Persoonlijkheid.MAX_GOEDHEID + 1));
    }

    @Test
    public void goedheid_is_geldig_als_het_binnen_de_scope_van_min_en_max_valt() {
        for (int goedheid = Persoonlijkheid.MIN_GOEDHEID;
                goedheid <= Persoonlijkheid.MAX_GOEDHEID; goedheid++) {
            assertTrue(Persoonlijkheid.isValidGoedheid(goedheid));
        }
    }

    @Test
    public void creativiteit_is_niet_geldig_als_het_lagere_waarde_heeft_dan_min() {
        assertFalse(Persoonlijkheid
                .isValidCreativiteit(Persoonlijkheid.MIN_CREATIVITEIT - 1));
    }

    @Test
    public void creativiteit_is_niet_geldig_als_het_hogere_waarde_heeft_dan_max() {
        assertFalse(Persoonlijkheid
                .isValidCreativiteit(Persoonlijkheid.MAX_CREATIVITEIT + 1));
    }

    @Test
    public void creativiteit_is_geldig_als_het_binnen_de_scope_van_min_en_max_valt() {
        for (int creativiteit = Persoonlijkheid.MIN_CREATIVITEIT;
                creativiteit <= Persoonlijkheid.MAX_CREATIVITEIT; creativiteit++) {
            assertTrue(Persoonlijkheid.isValidCreativiteit(creativiteit));
        }
    }

    @Test
    public void clone_geeft_kopie_van_persoonlijkheid() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(2, 4);
        Persoonlijkheid persoonlijkheid2 = persoonlijkheid1.clone();
        assertTrue(Objects.equals(persoonlijkheid1, persoonlijkheid2));
        persoonlijkheid1.divide(2);
        assertFalse(Objects.equals(persoonlijkheid1, persoonlijkheid2));
        persoonlijkheid2.divide(2);
        assertTrue(Objects.equals(persoonlijkheid1, persoonlijkheid2));
        persoonlijkheid2.divide(2);
        assertFalse(Objects.equals(persoonlijkheid1, persoonlijkheid2));
    }

    @Test
    public void add_doet_niks_als_null_wordt_megegeven_als_argument() {
        Persoonlijkheid persoonlijkheid1 = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid persoonlijkheid2 = persoonlijkheid1.clone();
        persoonlijkheid1.add(null);
        assertTrue(Objects.equals(persoonlijkheid1, persoonlijkheid2));
    }

    @Test
    public void add_voegt_tweede_persoonlijkheid_bij_de_eerste() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(5, 3);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(14, 48);
        persoonlijkheid1.add(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1, new Persoonlijkheid(19, 51)));
        persoonlijkheid2 = new Persoonlijkheid(90, 60);
        persoonlijkheid1.add(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1,
                new Persoonlijkheid(Persoonlijkheid.MAX_GOEDHEID,
                        Persoonlijkheid.MAX_CREATIVITEIT)));
    }

    @Test
    public void subtract_doet_niks_als_null_wordt_megegeven_als_argument() {
        Persoonlijkheid persoonlijkheid1 = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid persoonlijkheid2 = persoonlijkheid1.clone();
        persoonlijkheid1.add(null);
        assertTrue(Objects.equals(persoonlijkheid1, persoonlijkheid2));
    }

    @Test
    public void bij_subtract_worden_twee_persoonlijkheden_afgetrokken() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(25, 90);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(14, 48);
        persoonlijkheid1.subtract(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1, new Persoonlijkheid(11, 42)));
        persoonlijkheid2 = new Persoonlijkheid(22, 43);
        persoonlijkheid1.subtract(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1,
                new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID,
                        Persoonlijkheid.MIN_CREATIVITEIT)));
    }

    @Test
    public void multiply_doet_niks_als_null_wordt_megegeven_als_argument() {
        Persoonlijkheid persoonlijkheid1 = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid persoonlijkheid2 = persoonlijkheid1.clone();
        persoonlijkheid1.multiply(null);
        assertTrue(Objects.equals(persoonlijkheid1, persoonlijkheid2));
    }

    @Test
    public void multiply_vermenigvuldigt_eerste_persoonlijkheid_met_de_tweede() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(5, 3);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(6, 7);
        persoonlijkheid1.multiply(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1, new Persoonlijkheid(30, 21)));
        persoonlijkheid2 = new Persoonlijkheid(90, 60);
        persoonlijkheid1.multiply(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1,
                new Persoonlijkheid(Persoonlijkheid.MAX_GOEDHEID,
                        Persoonlijkheid.MAX_CREATIVITEIT)));
    }

    @Test
    public void divide_doet_niks_als_null_wordt_megegeven_als_argument() {
        Persoonlijkheid persoonlijkheid1 = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid persoonlijkheid2 = persoonlijkheid1.clone();
        persoonlijkheid1.divide(null);
        assertTrue(Objects.equals(persoonlijkheid1, persoonlijkheid2));
    }

    @Test
    public void divide_deelt_eerste_persoonlijkheid_door_de_tweede() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(31, 21);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(2, 7);
        persoonlijkheid1.divide(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1, new Persoonlijkheid(15, 3)));
        persoonlijkheid2 = new Persoonlijkheid(22, 43);
        persoonlijkheid1.divide(persoonlijkheid2);
        assertTrue(Objects.equals(persoonlijkheid1,
                new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID,
                        Persoonlijkheid.MIN_CREATIVITEIT)));
    }

    @Test
    public void divide_deelt_persoonlijkheid_door_de_meegegeven_waarde() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(31, 21);
        persoonlijkheid1.divide(10);
        assertTrue(Objects.equals(persoonlijkheid1, new Persoonlijkheid(3, 2)));
        persoonlijkheid1.divide(10);
        assertTrue(Objects.equals(persoonlijkheid1,
                new Persoonlijkheid(Persoonlijkheid.MIN_GOEDHEID,
                        Persoonlijkheid.MIN_CREATIVITEIT)));
    }

    @Test
    public void avg_geeft_null_terug_als_beide_argumenten_null_zijn() {
        assertNull(Persoonlijkheid.avg(null, null));
    }

    @Test
    public void avg_geeft_null_terug_als_eerste_argument_null_is() {
        assertNull(Persoonlijkheid.avg(null,
                Persoonlijkheid.createRandomPersoonlijkheid()));
    }

    @Test
    public void avg_geeft_gemiddeldePersoonlijkheid_van_twee_persoonlijkheden_terug() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(10, 31);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(34, 68);
        Persoonlijkheid gemiddeld = Persoonlijkheid.avg(persoonlijkheid1, persoonlijkheid2);
        assertTrue(Objects.equals(gemiddeld, new Persoonlijkheid(22, 49)));
    }
    
    @Test
    public void verschil_geeft_null_terug_als_beide_persoonlijkheden_null_zijn() {
        assertNull(Persoonlijkheid.verschil(null, null));
    }
    
    @Test
    public void verschil_geeft_eerste_persoonlijkheid_terug_als_tweede_persoonlijkheid_null_is() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(5, 8);
        Persoonlijkheid verschil = Persoonlijkheid.verschil(persoonlijkheid1, null);
        assertTrue(Objects.equals(verschil, persoonlijkheid1));
    }
    
    @Test
    public void verschil_geeft_tweede_persoonlijkheid_terug_als_eerste_persoonlijkheid_null_is() {
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(34, 79);
        Persoonlijkheid verschil = Persoonlijkheid.verschil(null, persoonlijkheid2);
        assertTrue(Objects.equals(verschil, persoonlijkheid2));
    }
    
    @Test
    public void verschil_geeft_het_verschil_van_twee_persoonlijkheden_terug() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(5, 8);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(34, 79);
        Persoonlijkheid hardGecodeerdVerschil = new Persoonlijkheid(29, 71);
        Persoonlijkheid verschil1 = Persoonlijkheid.verschil(persoonlijkheid1, persoonlijkheid2);
        Persoonlijkheid verschil2 = Persoonlijkheid.verschil(persoonlijkheid2, persoonlijkheid1);
        assertTrue(Objects.equals(verschil1, verschil2));
        assertTrue(Objects.equals(verschil1, hardGecodeerdVerschil));
        assertTrue(Objects.equals(verschil2, hardGecodeerdVerschil));
    }

    @Test
    public void getGemiddeldePersoonlijkheid_geeft_null_terug_als_ingegeven_set_of_reeks_van_persoonlijkheden_null_is() {
        Map<Integer, List<Persoonlijkheid>> map;
        map = new HashMap<>();
        Persoonlijkheid[] persoonlijkhedenArray;
        persoonlijkhedenArray = null;
        Persoonlijkheid original = Persoonlijkheid.createRandomPersoonlijkheid();
        Persoonlijkheid clone = original.clone();
        assertNotNull(original);
        assertNotNull(clone);
        original = Persoonlijkheid.combineer(map.get(0));
        assertNull(original);
        original = clone.clone();
        assertNotNull(original);
        original = Persoonlijkheid.combineer(persoonlijkhedenArray);
        assertNull(original);
    }

    @Test
    public void getGemiddeldePersoonlijkheid_geeft_correcte_gemiddelde_persoonlijkheid_set_of_reeks_van_persoonlijkheden() {
        List<Persoonlijkheid> persoonlijkhedenSet = getPersoonlijkhedenList();
        Persoonlijkheid[] persoonlijkhedenArray = persoonlijkhedenSet
                .toArray(new Persoonlijkheid[persoonlijkhedenSet.size()]);
        Persoonlijkheid gemiddelde = new Persoonlijkheid(39, 49);
        assertTrue(Objects.equals(gemiddelde, Persoonlijkheid
                .combineer(persoonlijkhedenSet)));
        assertTrue(Objects.equals(gemiddelde, Persoonlijkheid
                .combineer(persoonlijkhedenArray)));
    }
    
    @Test
    public void null_is_geen_valid_Persoonlijkheid() {
        assertFalse(Persoonlijkheid.isValidPersoonlijkheid(null));
    }
    
    @Test
    public void Persoonlijkheid_met_geldige_goedheids_en_creativiteitswaarden_is_valid_Persoonlijkheid() {
        Persoonlijkheid persoonlijkheid1 = new Persoonlijkheid(
                Persoonlijkheid.MIN_GOEDHEID, Persoonlijkheid.MAX_CREATIVITEIT);
        Persoonlijkheid persoonlijkheid2 = new Persoonlijkheid(
                Persoonlijkheid.MAX_GOEDHEID, Persoonlijkheid.MIN_CREATIVITEIT);
        assertTrue(Persoonlijkheid.isValidPersoonlijkheid(persoonlijkheid1));
        assertTrue(Persoonlijkheid.isValidPersoonlijkheid(persoonlijkheid2));
    }
}
