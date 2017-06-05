package be.howest.klooster.core;

import org.junit.Test;
import static org.junit.Assert.*;
import static be.howest.klooster.core.Berichten.*;
import java.util.Locale;
import org.junit.Before;

/**
 *
 * @author Hayk
 */
public class BerichtenTest {
    
    @Before
    public void before() {
        System.err.print("\n" + getClass().getSimpleName()
                .toUpperCase(Locale.ENGLISH) + ":");
    }
    
    @Test
    public void GEDACHTE_bevat_correcte_formaatparameters() {
        assertNotNull(GEDACHTE);
        System.err.println(String.format(GEDACHTE, "neutraal neutraal", 0));
    }

    @Test
    public void PATER_bevat_correcte_formaatparameters() {
        assertNotNull(PATER);
        System.err.println(String.format(PATER, "Roger", "neutraal neutraal", 1, ""));
    }

    @Test
    public void NIEUWE_PATER_bevat_correcte_formaatparameters() {
        assertNotNull(NIEUWE_PATER);
        System.err.println(String.format(NIEUWE_PATER, "Roger"));
    }

    @Test
    public void SPREEK_BASISTOESTAND_bevat_correcte_formaatparameters() {
        assertNotNull(SPREEK_BASISTOESTAND);
        System.err.println(String.format(SPREEK_BASISTOESTAND, "Roger"));
    }

    @Test
    public void BID_bevat_correcte_formaatparameters() {
        assertNotNull(BID);
        System.err.println(String.format(BID, "Roger", "neutraal neutraal"));
    }

    @Test
    public void DENK_NA_bevat_correcte_formaatparameters() {
        assertNotNull(DENK_NA);
        System.err.println(String.format(DENK_NA, "Roger", 7));
    }

    @Test
    public void DENK_NA_HOOFD_ZIT_VOL_bevat_correcte_formaatparameters() {
        assertNotNull(DENK_NA_HOOFD_ZIT_VOL);
        System.err.println(String.format(DENK_NA_HOOFD_ZIT_VOL, "Roger"));
    }

    @Test
    public void DENK_NA_VOORAF_bevat_correcte_formaatparameters() {
        assertNotNull(DENK_NA_VOORAF);
        System.err.println(String.format(DENK_NA_VOORAF, "Roger", "neutraal neutraal"));
    }

    @Test
    public void DENK_NA_ACHTERAF_bevat_correcte_formaatparameters() {
        assertNotNull(DENK_NA_ACHTERAF);
        System.err.println(String.format(DENK_NA_ACHTERAF, "Roger", "neutraal neutraal"));
    }

    @Test
    public void DENK_NA_GELUKT_bevat_correcte_formaatparameters() {
        assertNotNull(DENK_NA_GELUKT);
        System.err.println(String.format(DENK_NA_GELUKT, "Roger", 7));
    }

    @Test
    public void DENK_NA_MISLUKT_bevat_correcte_formaatparameters() {
        assertNotNull(DENK_NA_MISLUKT);
        System.err.println(String.format(DENK_NA_MISLUKT, "Roger"));
    }

}
