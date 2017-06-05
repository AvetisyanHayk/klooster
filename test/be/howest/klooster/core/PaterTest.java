package be.howest.klooster.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Hayk
 */
public class PaterTest {

    private String naam = null;
    private Persoonlijkheid persoonlijkheid = null;
    private Pater roger = null;
    private Pater pater = null;
    private Gedachte gedachte = null;

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
        naam = "Roger";
        persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        roger = new Pater(naam);
        pater = new Pater(null);
        gedachte = new Gedachte(Inspiratie.getInstance().inspireerMij(),
                persoonlijkheid);
    }

    private static class DummyGedachtenOptimizer implements GedachtenOptimizer {

        @Override
        public int optimaliseerGedachten(Pater pater) {
            return doeNiks();
        }

        private int doeNiks() {
            return 0;
        }
    }

    @Test
    public void pater_is_Observable() {
        assertTrue(Pater.class.getSuperclass().equals(Observable.class));
    }

    @Test
    public void nieuwe_pater_zonder_naam_krijgt_klassenaam_Pater() {
        assertEquals(Pater.class.getSimpleName(), pater.getNaam());
        assertEquals(Pater.class.getSimpleName(), new Pater(null, null).getNaam());
        assertEquals(Pater.class.getSimpleName(), new Pater(null,
                persoonlijkheid).getNaam());
    }

    @Test
    public void nieuwe_pater_zonder_persoonlijkheid_krijgt_geldige_random_Persoonlijkheid() {
        assertTrue(Persoonlijkheid.isValidPersoonlijkheid(
                pater.getPersoonlijkheid()));
    }

    @Test
    public void nieuwe_pater_heeft_geen_gedachten() {
        assertEquals(0, pater.getAantalGedachten());
        assertEquals(0, roger.getAantalGedachten());
        assertEquals(0, new Pater(null, null).getAantalGedachten());
        assertEquals(0, new Pater(null, persoonlijkheid).getAantalGedachten());
    }

    @Test
    public void getNaam_geeft_correcte_naam_van_pater_terug() {
        assertEquals(naam, roger.getNaam());
        assertEquals(naam, new Pater(naam, persoonlijkheid).getNaam());
    }

    @Test
    public void getPersoonlijkheid_geeft_correcte_Persoonlijkheid_van_pater_terug() {
        Pater pater1 = new Pater(naam, persoonlijkheid);
        Pater pater2 = new Pater(null, persoonlijkheid);
        assertEquals(persoonlijkheid, pater1.getPersoonlijkheid());
        assertEquals(persoonlijkheid, pater2.getPersoonlijkheid());
    }

    @Test
    public void gedGedachten_geeft_immutable_gedachten_terug() {
        pater.addGedachte(gedachte);
        Gedachte[] gedachten = pater.getGedachten();
        gedachten[0] = null;
        assertNull(gedachten[0]);
        assertNotNull(pater.getGedachten()[0]);
        assertNotEquals(gedachten[0], pater.getGedachten()[0]);
    }

    @Test
    public void gedGedachten_geeft_correct_aantal_gedachten_terug() {
        pater.addGedachte(gedachte);
        Gedachte[] gedachten = pater.getGedachten();
        assertEquals(1, pater.getAantalGedachten());
        assertEquals(1, gedachten.length);
    }

    @Test
    public void gedGedachtenSet_geeft_correcte_gedachten_terug() {
        pater.addGedachte(gedachte);
        assertArrayEquals(pater.getGedachten(), pater.getGedachtenList()
                .toArray(new Gedachte[pater.getAantalGedachten()]));
    }

    @Test
    public void setGedachten_wijzigt_gedachten_niet_als_ingegeven_argument_null_is() {
        pater.addGedachte(gedachte);
        Gedachte[] gedachten = pater.getGedachten();
        pater.setGedachten(null);
        assertNotNull(pater.getGedachten());
        assertArrayEquals(gedachten, pater.getGedachten());
        assertEquals(1, pater.getAantalGedachten());
    }

    @Test
    public void setGedachten_wijzigt_gedachten_niet_als_ingegeven_gedachtenreeks_groter_is_dan_toegelaten() {
        Gedachte[] gedachten = new Gedachte[Pater.MAX_GEDACHTEN + 1];
        gedachten[0] = gedachte;
        pater.setGedachten(gedachten);
        assertEquals(0, pater.getAantalGedachten());
        assertEquals(0, pater.getGedachten().length);
        assertEquals(0, pater.getGedachtenList().size());
    }

    @Test
    public void setGedachten_wijzigt_gedachten_niet_als_ingegeven_gedachtenreeks_kleiner_is_dan_toegelaten() {
        Gedachte[] gedachten = new Gedachte[Pater.MAX_GEDACHTEN - 1];
        gedachten[0] = gedachte;
        pater.setGedachten(gedachten);
        assertEquals(0, pater.getAantalGedachten());
        assertEquals(0, pater.getGedachten().length);
        assertEquals(0, pater.getGedachtenList().size());
    }

    @Test
    public void setGedachten_wijzigt_gedachten_als_ingegeven_gedachtenreeks_geldig_is() {
        Gedachte[] gedachten = new Gedachte[Pater.MAX_GEDACHTEN];
        gedachten[0] = gedachte;
        pater.setGedachten(gedachten);
        assertEquals(1, pater.getAantalGedachten());
        assertEquals(1, pater.getGedachten().length);
        assertEquals(1, pater.getGedachtenList().size());
        assertEquals(gedachte, pater.getGedachten()[0]);
    }

    @Test
    public void getAantalGedachten_geeft_correct_aantal_van_gedachten_terug() {
        assertEquals(0, pater.getAantalGedachten());
        pater.addGedachte(gedachte);
        assertEquals(1, pater.getAantalGedachten());
        pater.addGedachte(gedachte);
        assertEquals(2, pater.getAantalGedachten());
    }

    @Test
    public void addGedachte_voegt_niks_toe_als_ingegeven_argument_null_is() {
        assertEquals(0, pater.getAantalGedachten());
        assertFalse(pater.addGedachte(null));
        assertEquals(0, pater.getAantalGedachten());
    }

    @Test
    public void addGedachte_voegt_gedachten_toe() {
        assertEquals(0, pater.getAantalGedachten());
        assertTrue(pater.addGedachte(gedachte));
        assertEquals(1, pater.getAantalGedachten());
        assertTrue(pater.addGedachte(gedachte));
        assertEquals(2, pater.getAantalGedachten());
    }

    @Test
    public void addGedachte_voegt_geen_gedachten_toe_als_hoofd_van_pater_vol_zit() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            assertEquals(i, pater.getAantalGedachten());
            assertTrue(pater.addGedachte(gedachte));
        }
        assertEquals(Pater.MAX_GEDACHTEN, pater.getAantalGedachten());
        assertFalse(pater.addGedachte(gedachte));
        assertEquals(Pater.MAX_GEDACHTEN, pater.getAantalGedachten());
    }

    @Test
    public void getVolledigeNaam_geeft_correcte_naam_terug_als_pater_geen_naam_heeft() {
        assertEquals(Pater.class.getSimpleName(), pater.getVolledigeNaam());
    }

    @Test
    public void getVolledigeNaam_geeft_correcte_naam_terug_als_pater_wel_een_naam_heeft() {
        assertEquals(Pater.class.getSimpleName() + " " + roger.getNaam(),
                roger.getVolledigeNaam());
    }

    @Test
    public void hoofdZitVol_geeft_correcte_true_of_false_waarden_terug() {
        assertFalse(pater.hoofdZitVol());
        for (int i = 0; i < Pater.MAX_GEDACHTEN - 1; i++) {
            pater.addGedachte(gedachte);
            assertFalse(pater.hoofdZitVol());
        }
        pater.addGedachte(gedachte);
        assertTrue(pater.hoofdZitVol());
        pater.addGedachte(gedachte);
        assertTrue(pater.hoofdZitVol());
    }

    @Test
    public void twee_pater_zijn_gelijk_als_ze_dezelfde_naam_gedachten_persoonlijkheid_hebben_en_zich_in_dezelfde_toestand_bevinden() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        assertTrue(Objects.equals(roger, roger2));
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertTrue(Objects.equals(roger, roger2));
    }

    @Test
    public void twee_paters_hebben_dezelfde_hashCode_als_ze_dezelfde_naam_gedachten_persoonlijkheid_hebben_en_zich_in_dezelfde_toestand_bevinden() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        assertEquals(roger.hashCode(), roger2.hashCode());
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertEquals(roger.hashCode(), roger2.hashCode());
    }

    @Test
    public void twee_paters_met_verschillende_namen_zijn_verschillende_objecten() {
        assertFalse(Objects.equals(pater, roger));
        pater.addGedachte(gedachte);
        roger.addGedachte(gedachte);
        assertFalse(Objects.equals(pater, roger));
    }

    @Test
    public void twee_paters_met_verschillende_namen_hebben_verschillende_hashCodes() {
        assertNotEquals(pater.hashCode(), roger.hashCode());
        pater.addGedachte(gedachte);
        roger.addGedachte(gedachte);
        assertNotEquals(pater.hashCode(), roger.hashCode());
    }

    @Test
    public void twee_paters_met_verschillende_persoonlijkheden_zijn_verschillende_objecten() {
        Persoonlijkheid persoonlijkheid2 = Persoonlijkheid.createRandomPersoonlijkheid();
        while (persoonlijkheid.equals(persoonlijkheid2)) {
            persoonlijkheid2 = Persoonlijkheid.createRandomPersoonlijkheid();
        }
        Pater roger2 = new Pater(roger.getNaam(), persoonlijkheid2);
        assertFalse(Objects.equals(roger, roger2));
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertFalse(Objects.equals(roger, roger2));
    }

    @Test
    public void twee_paters_met_verschillende_persoonlijkheden_hebben_verschillende_hashCodes() {
        Persoonlijkheid persoonlijkheid2 = Persoonlijkheid.createRandomPersoonlijkheid();
        while (persoonlijkheid.equals(persoonlijkheid2)) {
            persoonlijkheid2 = Persoonlijkheid.createRandomPersoonlijkheid();
        }
        Pater roger2 = new Pater(roger.getNaam(), persoonlijkheid2);
        assertNotEquals(roger.hashCode(), roger2.hashCode());
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertNotEquals(roger.hashCode(), roger2.hashCode());
    }

    @Test
    public void twee_paters_met_verschillende_gedachten_zijn_verschillende_objecten() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        roger.addGedachte(gedachte);
        assertFalse(Objects.equals(roger, roger2));
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertFalse(Objects.equals(roger, roger2));
    }

    @Test
    public void twee_paters_met_verschillende_gedachten_hebben_verschillende_hashCodes() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        roger.addGedachte(gedachte);
        assertNotEquals(roger.hashCode(), roger2.hashCode());
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertNotEquals(roger.hashCode(), roger2.hashCode());
    }

    @Test
    public void twee_paters_in_verschillende_toestanden_zijn_verschillende_objecten() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        roger.setToestand(roger.getHoofdZitVolMetGedachtenToestand());
        roger.addGedachte(gedachte);
        assertFalse(Objects.equals(roger, roger2));
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertFalse(Objects.equals(roger, roger2));
    }

    @Test
    public void twee_paters_in_verschillende_toestanden_hebben_verschillende_hashCodes() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        roger.setToestand(roger.getHoofdZitVolMetGedachtenToestand());
        roger.addGedachte(gedachte);
        assertNotEquals(roger.hashCode(), roger2.hashCode());
        roger.addGedachte(gedachte);
        roger2.addGedachte(gedachte);
        assertNotEquals(roger.hashCode(), roger2.hashCode());
    }

    /* Test toestanden en acties */
    @Test
    public void nieuwe_pater_is_in_basisToestand() {
        assertTrue(pater.getToestand().getClass().equals(BasisToestand.class));
        assertTrue(roger.getToestand().getClass().equals(BasisToestand.class));
    }

    @Test
    public void toestand_van_nieuwe_pater_verandert_naar_normaleToestand_na_het_bidden() {
        pater.bid();
        roger.bid();
        assertTrue(pater.getToestand().getClass().equals(NormaleToestand.class));
        assertTrue(roger.getToestand().getClass().equals(NormaleToestand.class));
    }

    @Test
    public void nieuwe_pater_blijft_in_basisToestand_na_het_luisteren_na_een_null_woord() {
        pater.luister(null);
        assertTrue(pater.getToestand().getClass().equals(BasisToestand.class));
        pater.luisterNaar(roger);
        assertTrue(pater.getToestand().getClass().equals(BasisToestand.class));
        pater.luister(roger.spreek());
        assertTrue(pater.getToestand().getClass().equals(BasisToestand.class));
    }

    @Test
    public void toestand_van_nieuwe_pater_verandert_naar_normaleToestand_na_het_luisteren_naar_een_woord() {
        pater.bid();
        roger.luisterNaar(pater);
        assertTrue(roger.getToestand().getClass().equals(NormaleToestand.class));
        roger.luister(pater.spreek());
        assertTrue(roger.getToestand().getClass().equals(NormaleToestand.class));
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        roger2.luister(roger.spreek());
        assertTrue(roger2.getToestand().getClass().equals(NormaleToestand.class));
    }

    @Test
    public void pater_in_normaleToestand_blijft_in_normaleToestand_na_het_spreken() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        Pater roger3 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        for (int i = 0; i < Pater.MAX_GEDACHTEN - 1; i++) {
            pater.bid();
            pater.spreekTegen(roger);
            assertTrue(pater.getToestand().getClass().equals(NormaleToestand.class));
            roger2.luisterNaar(pater);
            assertTrue(pater.getToestand().getClass().equals(NormaleToestand.class));
            roger3.luister(pater.spreek());
            assertTrue(pater.getToestand().getClass().equals(NormaleToestand.class));
        }
    }

    @Test
    public void na_maximum_aantal_keren_luisteren_verandert_de_toestand_van_pater_naar_hoofdZitVolMetGedachtenToestand() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
            roger.luisterNaar(pater);
        }
        assertTrue(roger.getToestand().getClass().equals(HoofdZitVolMetGedachtenToestand.class));
    }

    @Test
    public void na_maximum_aantal_keren_bidden_verandert_de_toestand_van_pater_naar_hoofdZitVolMetGedachtenToestand() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
            roger.bid();
        }
        assertTrue(pater.getToestand().getClass().equals(HoofdZitVolMetGedachtenToestand.class));
        assertTrue(roger.getToestand().getClass().equals(HoofdZitVolMetGedachtenToestand.class));
    }

    @Test
    public void in_hoofdZitVolMetGedachtenToestand_kan_pater_niet_bidden() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
        }
        Gedachte gebedenGedachte = pater.bid();
        assertNull(gebedenGedachte);
    }

    @Test
    public void bidden_in_hoofdZitVolMetGedachtenToestand_zal_pater_doen_nadenken_en_veranderen_naar_normaleToestand_als_nadenken_gelukt_is() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
        }
        pater.bid();
        assertTrue(pater.getToestand().getClass().equals(NormaleToestand.class));
    }

    @Test
    public void luisteren_in_hoofdZitVolMetGedachtenToestand_zal_pater_doen_nadenken_en_veranderen_naar_normaleToestand_als_nadenken_gelukt_is() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
            roger.luisterNaar(pater);
        }
        pater.bid();
        roger.luisterNaar(pater);
        assertTrue(roger.getToestand().getClass().equals(NormaleToestand.class));
    }

    @Test
    public void nadenken_in_hoofdZitVolMetGedachtenToestand_zal_toestand_van_pater_veranderen_naar_normaleToestand_als_nadenken_gelukt_is() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
        }
        pater.denkNa();
        assertTrue(pater.getToestand().getClass().equals(NormaleToestand.class));
    }

    @Test
    public void nadenken_in_hoofdZitVolMetGedachtenToestand_zal_toestand_van_pater_veranderen_naar_normaleToestand_als_nadenken_mislukt_is() {
        DummyGedachtenOptimizer dummyOptimizer = new DummyGedachtenOptimizer();
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
        }
        pater.denkNa(dummyOptimizer);
        assertTrue(pater.getToestand().getClass().equals(HoofdZitVolMetGedachtenToestand.class));
    }

    @Test
    public void na_nadenken_bij_gedachten_over_verschillende_concepten_heeft_een_pater_nog_altijd_dezelfde_gedachten() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        for (int i = 0; i < Inspiratie.MAX; i++) {
            Gedachte gebedenGedachte = roger.bid();
            roger2.addGedachte(gebedenGedachte);
        }
        roger2.setToestand(roger2.getNormaleToestand());
        assertTrue(Objects.equals(roger, roger2));
        roger.denkNa();
        roger2.setToestand(roger2.getNormaleToestand());
        assertTrue(roger.getGedachtenList().containsAll(roger2.getGedachtenList()));
    }

    @Test
    public void na_maximum_aantal_keren_bidden_heeft_de_pater_maximum_aantal_gedachten() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
            roger.bid();
        }
        assertEquals(Pater.MAX_GEDACHTEN, pater.getAantalGedachten());
        assertEquals(Pater.MAX_GEDACHTEN, roger.getAantalGedachten());
    }

    @Test
    public void nextGedachte_geeft_gedachtes_op_correcte_volgorde_terug() {
        Gedachte gebedenGedachte = roger.bid();
        Gedachte rogerNextGedachte = roger.nextGedachte();
        assertTrue(Objects.equals(roger.getGedachten()[0], rogerNextGedachte));
        assertTrue(Objects.equals(gebedenGedachte, rogerNextGedachte));
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            pater.bid();
        }
        Gedachte[] gedachten = pater.getGedachten();
        for (Gedachte item : gedachten) {
            Gedachte next = pater.nextGedachte();
            assertTrue(Objects.equals(item, next));
        }
    }

    @Test
    public void nextGedachte_geeft_null_terug_als_pater_geen_gedachten_heeft() {
        assertNull(roger.nextGedachte());
        assertNull(pater.nextGedachte());
        roger.bid();
        Gedachte[] gedachten = new Gedachte[Pater.MAX_GEDACHTEN];
        roger.setGedachten(gedachten);
        assertNull(roger.nextGedachte());
    }

    @Test
    public void pater_spreekt_een_juiste_woord_uit() {
        assertNull(roger.spreek());
        Gedachte gebedenGedachte1 = roger.bid();
        Gedachte gebedenGedachte2 = roger.bid();
        Woord woord1 = roger.spreek();
        Woord woord2 = roger.spreek();
        assertNotNull(woord1);
        assertNotNull(woord2);
        assertFalse(Objects.equals(woord1, woord2));
        assertEquals(woord1, gebedenGedachte1.verwoord(roger.getPersoonlijkheid()));
        assertEquals(woord2, gebedenGedachte2.verwoord(roger.getPersoonlijkheid()));
        woord1 = roger.spreek();
        woord2 = roger.spreek();
        assertNotNull(woord1);
        assertNotNull(woord2);
        assertFalse(Objects.equals(woord1, woord2));
        assertEquals(woord1, gebedenGedachte1.verwoord(roger.getPersoonlijkheid()));
        assertEquals(woord2, gebedenGedachte2.verwoord(roger.getPersoonlijkheid()));
    }

    @Test
    public void pater_luistert_niet_naar_een_null_woord() {
        Pater roger2 = new Pater(roger.getNaam(), roger.getPersoonlijkheid());
        assertTrue(Objects.equals(roger, roger2));
        roger.luister(null);
        assertTrue(Objects.equals(roger, roger2));
        assertEquals(0, roger.getAantalGedachten());
        roger.luister(roger2.spreek());
        assertTrue(Objects.equals(roger, roger2));
        assertEquals(0, roger.getAantalGedachten());
        roger2.luisterNaar(roger);
        assertTrue(Objects.equals(roger, roger2));
        assertEquals(0, roger.getAantalGedachten());
    }

    @Test
    public void pater_luistert_naar_woord_op_een_correcte_manier() {
        for (int i = 0; i < Pater.MAX_GEDACHTEN; i++) {
            Gedachte gebedenGedachte = roger.bid();
            pater.luister(roger.spreek());
            assertEquals(i + 1, pater.getAantalGedachten());
            Woord woord = gebedenGedachte.verwoord(roger.getPersoonlijkheid());
            Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(
                    pater.getPersoonlijkheid(),
                    woord.getBegeestering(),
                    woord.getGedachte().getMening()
            );
            Gedachte aangenomenGedachte = new Gedachte(woord.getConcept(),
                    gemiddeldeMening);
            assertTrue(Objects.equals(pater.nextGedachte(), aangenomenGedachte));
        }
    }

    @Test
    public void na_nadenken_op_basis_van_creativiteit_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Gedachte[] gedachten = getGedachtenArray();
        Persoonlijkheid persoonlijkheidVanJonas = new Persoonlijkheid(5, 6);
        Pater jonas = new Pater("Jonas", persoonlijkheidVanJonas);
        jonas.setGedachten(gedachten);
        jonas.setToestand(jonas.getHoofdZitVolMetGedachtenToestand());

        jonas.denkNa(GedachtenOptimizerOpBasisVanCreativiteit.getInstance());

        List<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(jonas.getGedachtenList());
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(meningen);
        assertEquals(new Persoonlijkheid(54, 34), gemiddeldeMening);
        Persoonlijkheid verschil = Persoonlijkheid
                .verschil(gemiddeldeMening, persoonlijkheidVanJonas);
        assertEquals(new Persoonlijkheid(49, 28), verschil);
        verschil.divide(10);
        assertEquals(new Persoonlijkheid(4, 2), verschil);
        assertEquals(jonas.getPersoonlijkheid(), verschil);
    }

    @Test
    public void na_nadenken_op_basis_van_goedheid_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Gedachte[] gedachten = getGedachtenArray();
        Persoonlijkheid persoonlijkheidVanJonas = new Persoonlijkheid(5, 6);
        Pater jonas = new Pater("Jonas", persoonlijkheidVanJonas);
        jonas.setGedachten(gedachten);
        jonas.setToestand(jonas.getHoofdZitVolMetGedachtenToestand());

        jonas.denkNa(GedachtenOptimizerOpBasisVanGoedheid.getInstance());

        List<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(jonas.getGedachtenList());
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(meningen);
        assertEquals(new Persoonlijkheid(32, 67), gemiddeldeMening);
        Persoonlijkheid verschil = Persoonlijkheid
                .verschil(gemiddeldeMening, persoonlijkheidVanJonas);
        assertEquals(new Persoonlijkheid(27, 61), verschil);
        verschil.divide(10);
        assertEquals(new Persoonlijkheid(2, 6), verschil);
        assertEquals(jonas.getPersoonlijkheid(), verschil);
    }

    @Test
    public void na_nadenken_op_basis_van_gemiddelde_persoonlijkheid_verandert_persoonlijkheid_van_pater_op_correcte_manier() {
        Gedachte[] gedachten = getGedachtenArray();
        Persoonlijkheid persoonlijkheidVanJonas = new Persoonlijkheid(5, 6);
        Pater jonas = new Pater("Jonas", persoonlijkheidVanJonas);
        jonas.setGedachten(gedachten);
        jonas.setToestand(jonas.getHoofdZitVolMetGedachtenToestand());

        jonas.denkNa(GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid.getInstance());

        for (Gedachte item : jonas.getGedachten()) {
            System.err.println(item.getConcept() + ": " + item.getGoedheid() + " / " + item.getCreativiteit());
        }
        List<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(jonas.getGedachtenList());
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(meningen);
        assertEquals(new Persoonlijkheid(43, 50), gemiddeldeMening);
        Persoonlijkheid verschil = Persoonlijkheid
                .verschil(gemiddeldeMening, persoonlijkheidVanJonas);
        assertEquals(new Persoonlijkheid(38, 44), verschil);
        verschil.divide(10);
        assertEquals(new Persoonlijkheid(3, 4), verschil);
        assertEquals(jonas.getPersoonlijkheid(), verschil);
    }
}
