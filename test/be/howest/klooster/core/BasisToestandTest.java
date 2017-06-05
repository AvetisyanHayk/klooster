package be.howest.klooster.core;

import java.util.Objects;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Hayk
 */
public class BasisToestandTest {

    private Pater pater = null;
    private Pater roger = null;
    private Persoonlijkheid persoonlijkheid = null;
    private BasisToestand toestand = null;

    @Before
    public void before() {
        persoonlijkheid = new Persoonlijkheid(5, 6);
        pater = new Pater(null, persoonlijkheid);
        roger = new Pater("Roger", persoonlijkheid);
        toestand = new BasisToestand(pater);
    }

    @Test
    public void BasisToestand_gedraagt_zich_als_Toetand() {
        assertTrue(Toestand.class.isAssignableFrom(BasisToestand.class));
    }

    @Test
    public void BasisToestand_overerft_van_AbstracteToestand() {
        assertTrue(BasisToestand.class.getSuperclass().equals(AbstracteToestand.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_null_als_argument_voor_pater_is_niet_toegelaten() {
        BasisToestand nullToestand = new BasisToestand(null);
        assertNull(nullToestand.toString());
    }

    @Test
    public void bid_voegt_gedachte_toe_bij_de_gedachten_van_pater() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            Gedachte gedachte = toestand.bid();
            assertNotNull(gedachte);
            assertTrue(Objects.equals(gedachte, pater.getGedachten()[i]));
            assertEquals(i + 1, pater.getAantalGedachten());
        }
    }
    
    @Test
    public void spreek_geeft_null_terug() {
        assertNull(toestand.spreek());
    }

    @Test
    public void luisteren_naar_een_null_woord_voegt_geen_gedachte_toe_bij_de_gedachten_van_pater() {
        toestand.luister(null);
        assertEquals(0, pater.getAantalGedachten());
    }
    
    @Test
    public void luisteren_naar_een_woord_voegt_wel_een_gedachte_toe_bij_de_gedachten_van_pater() {
        roger.bid();
        toestand.luister(roger.spreek());
        assertEquals(1, pater.getAantalGedachten());
    }
    
    @Test
    public void na_nadenken_op_basis_van_creativiteit_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Pater pater2 = new Pater(null, pater.getPersoonlijkheid());
        toestand.denkNa(GedachtenOptimizerOpBasisVanCreativiteit.getInstance());
        assertEquals(0, pater.getAantalGedachten());
        assertTrue(Objects.equals(pater, pater2));
    }

    @Test
    public void na_nadenken_op_basis_van_goedheid_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Pater pater2 = new Pater(null, pater.getPersoonlijkheid());
        toestand.denkNa(GedachtenOptimizerOpBasisVanGoedheid.getInstance());
        assertEquals(0, pater.getAantalGedachten());
        assertTrue(Objects.equals(pater, pater2));
    }

    @Test
    public void na_nadenken_op_basis_van_gemiddelde_persoonlijkheid_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Pater pater2 = new Pater(null, pater.getPersoonlijkheid());
        toestand.denkNa(GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid.getInstance());
        assertEquals(0, pater.getAantalGedachten());
        assertTrue(Objects.equals(pater, pater2));
    }
}
