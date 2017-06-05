package be.howest.klooster.core;

import java.util.Objects;
import java.util.Observable;
import java.util.Set;
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
    
    @Before
    public void before() {
        naam = "Roger";
        persoonlijkheid = Persoonlijkheid.createRandomPersoonlijkheid();
        roger = new Pater(naam);
        pater = new Pater(null);
        gedachte = new Gedachte(Inspiratie.getInstance().inspireerMij(),
            persoonlijkheid);
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
        assertArrayEquals(pater.getGedachten(), pater.getGedachtenSet()
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
        assertEquals(0, pater.getGedachtenSet().size());
    }
    
    @Test
    public void setGedachten_wijzigt_gedachten_als_ingegeven_gedachtenreeks_geldig_is() {
        Gedachte[] gedachten = new Gedachte[Pater.MAX_GEDACHTEN];
        gedachten[0] = gedachte;
        pater.setGedachten(gedachten);
        assertEquals(1, pater.getAantalGedachten());
        assertEquals(1, pater.getGedachten().length);
        assertEquals(1, pater.getGedachtenSet().size());
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
        while(persoonlijkheid.equals(persoonlijkheid2)) {
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
        while(persoonlijkheid.equals(persoonlijkheid2)) {
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
}
