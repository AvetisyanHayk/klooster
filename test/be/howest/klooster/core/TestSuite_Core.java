package be.howest.klooster.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Hayk
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AbstracteToestandTest.class,
    BasisToestandTest.class,
    BerichtenTest.class,
    GedachteComparatorOpBasisVanConceptTest.class,
    GedachteComparatorOpBasisVanCreativiteitTest.class,
    GedachteComparatorOpBasisVanGoedheidTest.class,
    GedachteTest.class,
    GedachtenOptimizerOpBasisVanCreativiteitTest.class,
    GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheidTest.class,
    GedachtenOptimizerOpBasisVanGoedheidTest.class,
    GedachtenOptimizerTest.class,
    HoofdZitVolMetGedachtenToestandTest.class,
    InspiratieTest.class,
    NormaleToestandTest.class,
    PaterTest.class,
    PersoonlijkheidComparatorTest.class,
    PersoonlijkheidTest.class,
    ProgramTest.class,
    WoordTest.class
})
public class TestSuite_Core {

}
