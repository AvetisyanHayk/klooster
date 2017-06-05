package be.howest.klooster.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Hayk
 */
public class HoofdZitVolMetGedachtenToestandTest {

    private Pater pater = null;
    private Persoonlijkheid persoonlijkheid = null;
    private HoofdZitVolMetGedachtenToestand toestand = null;

    private Gedachte[] getGedachtenArray() {
        int size = Pater.MAX_GEDACHTEN;
        List<Gedachte> gedachten = new ArrayList<>();
        Inspiratie inspiratie = Inspiratie.getInstance().reset();
        int goedheid = 24;
        int creativiteit = 79;
        for (int i = 0; i < size; i++) {
            Persoonlijkheid mening = new Persoonlijkheid(goedheid, creativiteit);
            gedachten.add(new Gedachte(inspiratie.inspireerMij(), mening));
            goedheid += 2;
            creativiteit -= 3;
        }
        return gedachten.toArray(new Gedachte[size]);
    }
    
    @Before
    public void before() {
        persoonlijkheid = new Persoonlijkheid(5, 6);
        pater = new Pater(null, persoonlijkheid);
        toestand = new HoofdZitVolMetGedachtenToestand(pater);
    }

    @Test
    public void HoofdZitVolMetGedachtenToestand_gedraagt_zich_als_Toetand() {
        assertTrue(Toestand.class.isAssignableFrom(HoofdZitVolMetGedachtenToestand.class.getSuperclass()));
    }

    @Test
    public void HoofdZitVolMetGedachtenToestand_overerft_van_AbstracteToestand() {
        assertTrue(HoofdZitVolMetGedachtenToestand.class.getSuperclass().equals(AbstracteToestand.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_null_als_argument_voor_pater_is_niet_toegelaten() {
        HoofdZitVolMetGedachtenToestand nullToestand = new HoofdZitVolMetGedachtenToestand(null);
        assertNull(nullToestand.toString());
    }

    @Test
    public void bid_voegt_geen_gedachte_toe_bij_de_gedachten_van_pater() {
        Gedachte gedachte = toestand.bid();
        assertNull(gedachte);
        assertEquals(0, pater.getAantalGedachten());
    }
    
    @Test
    public void bid_triggert_denkNa() {
        Gedachte[] gedachten = getGedachtenArray();
        pater.setGedachten(gedachten);
        assertEquals(Pater.MAX_GEDACHTEN, pater.getAantalGedachten());
        toestand.bid();
        assertEquals(9, pater.getAantalGedachten());
    }

    @Test
    public void spreek_geeft_een_juiste_woord_terug() {
        assertNull(pater.spreek());
        Pater pater2 = new Pater(null);
        Gedachte gebedenGedachte1 = pater2.bid();
        Gedachte gebedenGedachte2 = pater2.bid();
        pater.addGedachte(gebedenGedachte1);
        pater.addGedachte(gebedenGedachte2);
        Woord woord1 = toestand.spreek();
        Woord woord2 = toestand.spreek();
        assertNotNull(woord1);
        assertNotNull(woord2);
        assertFalse(Objects.equals(woord1, woord2));
        assertEquals(woord1, gebedenGedachte1.verwoord(pater.getPersoonlijkheid()));
        assertEquals(woord2, gebedenGedachte2.verwoord(pater.getPersoonlijkheid()));
        woord1 = toestand.spreek();
        woord2 = toestand.spreek();
        assertNotNull(woord1);
        assertNotNull(woord2);
        assertFalse(Objects.equals(woord1, woord2));
        assertEquals(woord1, gebedenGedachte1.verwoord(pater.getPersoonlijkheid()));
        assertEquals(woord2, gebedenGedachte2.verwoord(pater.getPersoonlijkheid()));
    }
    
    @Test
    public void luister_triggert_denkNa() {
        Pater pater2 = new Pater(null, persoonlijkheid);
        pater2.bid();
        Gedachte[] gedachten = getGedachtenArray();
        pater.setGedachten(gedachten);
        assertEquals(Pater.MAX_GEDACHTEN, pater.getAantalGedachten());
        toestand.luister(pater2.spreek());
        assertEquals(9, pater.getAantalGedachten());
    }

    @Test
    public void na_nadenken_op_basis_van_creativiteit_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Gedachte[] gedachten = getGedachtenArray();
        pater.setGedachten(gedachten);

        toestand.denkNa(GedachtenOptimizerOpBasisVanCreativiteit.getInstance());

        List<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(pater.getGedachtenList());
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(meningen);
        assertEquals(new Persoonlijkheid(54, 34), gemiddeldeMening);
        Persoonlijkheid verschil = Persoonlijkheid
                .verschil(gemiddeldeMening, persoonlijkheid);
        assertEquals(new Persoonlijkheid(49, 28), verschil);
        verschil.divide(10);
        assertEquals(new Persoonlijkheid(4, 2), verschil);
        assertEquals(pater.getPersoonlijkheid(), verschil);
    }

    @Test
    public void na_nadenken_op_basis_van_goedheid_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Gedachte[] gedachten = getGedachtenArray();
        pater.setGedachten(gedachten);

        toestand.denkNa(GedachtenOptimizerOpBasisVanGoedheid.getInstance());

        List<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(pater.getGedachtenList());
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(meningen);
        assertTrue(Objects.equals(new Persoonlijkheid(32, 67), gemiddeldeMening));
        Persoonlijkheid verschil = Persoonlijkheid
                .verschil(gemiddeldeMening, persoonlijkheid);
        assertTrue(Objects.equals(new Persoonlijkheid(27, 61), verschil));
        verschil.divide(10);
        assertTrue(Objects.equals(new Persoonlijkheid(2, 6), verschil));
        assertTrue(Objects.equals(pater.getPersoonlijkheid(), verschil));
    }

    @Test
    public void na_nadenken_op_basis_van_gemiddelde_persoonlijkheid_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Gedachte[] gedachten = getGedachtenArray();
        pater.setGedachten(gedachten);

        toestand.denkNa(GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid.getInstance());

        List<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(pater.getGedachtenList());
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(meningen);
        assertEquals(new Persoonlijkheid(43, 50), gemiddeldeMening);
        Persoonlijkheid verschil = Persoonlijkheid
                .verschil(gemiddeldeMening, persoonlijkheid);
        assertEquals(new Persoonlijkheid(38, 44), verschil);
        verschil.divide(10);
        assertEquals(new Persoonlijkheid(3, 4), verschil);
        assertEquals(pater.getPersoonlijkheid(), verschil);
    }

}
