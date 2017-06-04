package be.howest.klooster.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Hayk
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    BerichtenTest.class,
    PersoonlijkheidTest.class,
    WoordTest.class,
    GedachteTest.class,
    InspiratieTest.class,
    GedachteComparatorOpBasisVanConceptTest.class,
    GedachteComparatorOpBasisVanCreativiteitTest.class,
    GedachteComparatorOpBasisVanGoedheidTest.class,
    GedachtenOptimizerTest.class,
    GedachtenOptimizerOpBasisVanCreativiteitTest.class
})
public class TestSuite_Core {
    
}
