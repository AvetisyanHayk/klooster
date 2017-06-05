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
public class NormaleToestandTest {

    private Pater pater = null;
    private Persoonlijkheid persoonlijkheid = null;
    private NormaleToestand toestand = null;
    
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
        toestand = new NormaleToestand(pater);
    }

    @Test
    public void NormaleToestandTest_gedraagt_zich_als_Toetand() {
        assertTrue(Toestand.class.isAssignableFrom(NormaleToestand.class));
    }

    @Test
    public void NormaleToestand_overerft_van_AbstracteToestand() {
        assertTrue(NormaleToestand.class.getSuperclass().equals(AbstracteToestand.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_met_null_als_argument_voor_pater_is_niet_toegelaten() {
        NormaleToestand nullToestand = new NormaleToestand(null);
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
    public void spreek_geeft_een_juiste_woord_terug() {
        assertNull(pater.spreek());
        Gedachte gebedenGedachte1 = toestand.bid();
        Gedachte gebedenGedachte2 = toestand.bid();
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
    public void luister_doet_niks_bij_een_null_woord() {
        Pater pater2 = new Pater(null, pater.getPersoonlijkheid());
        assertTrue(Objects.equals(pater, pater2));
        toestand.luister(null);
        assertTrue(Objects.equals(pater, pater2));
        assertEquals(0, pater.getAantalGedachten());
        toestand.luister(pater2.spreek());
        assertTrue(Objects.equals(pater, pater2));
        assertEquals(0, pater.getAantalGedachten());
    }

    @Test
    public void luistert_voegt_een_gedachte_toe_bij_pater_op_basis_van_woord_op_een_correcte_manier() {
        Gedachte gedachte = new Gedachte(Inspiratie.getInstance().inspireerMij(), Persoonlijkheid.createRandomPersoonlijkheid());
        Woord woord = gedachte.verwoord(persoonlijkheid);
        toestand.luister(woord);
        assertEquals(1, pater.getAantalGedachten());
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(
                pater.getPersoonlijkheid(),
                woord.getBegeestering(),
                woord.getGedachte().getMening()
        );
        Gedachte aangenomenGedachte = new Gedachte(woord.getConcept(),
                gemiddeldeMening);
        assertTrue(Objects.equals(pater.nextGedachte(), aangenomenGedachte));
    }

    @Test
    public void na_nadenken_bij_gedachten_over_verschillende_concepten_heeft_een_pater_nog_altijd_dezelfde_gedachten() {
        Pater pater2 = new Pater(pater.getNaam(), pater.getPersoonlijkheid());
        for (int i = 0; i < Inspiratie.MAX; i++) {
            Gedachte gebedenGedachte = toestand.bid();
            pater2.addGedachte(gebedenGedachte);
        }
        assertTrue(Objects.equals(pater, pater2));
        toestand.denkNa();
        assertTrue(pater.getGedachtenList().containsAll(pater2.getGedachtenList()));
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
