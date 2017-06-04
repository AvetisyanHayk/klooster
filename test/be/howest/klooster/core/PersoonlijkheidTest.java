package be.howest.klooster.core;

import java.util.Objects;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class PersoonlijkheidTest {

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
            Persoonlijkheid persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
            int goedheid = persoonlijkheid.getGoedheid();
            int creativiteit = persoonlijkheid.getGoedheid();
            assertTrue(goedheid >= Persoonlijkheid.MIN_GOEDHEID
                    && goedheid <= Persoonlijkheid.MAX_GOEDHEID);
            assertTrue(creativiteit >= Persoonlijkheid.MIN_CREATIVITEIT
                    && creativiteit <= Persoonlijkheid.MAX_CREATIVITEIT);
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
}
