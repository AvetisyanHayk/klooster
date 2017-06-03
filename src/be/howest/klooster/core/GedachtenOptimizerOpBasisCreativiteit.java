package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisCreativiteit
        implements GedachtenOptimizer {

    private static GedachtenOptimizer uniqueInstance;

    public static synchronized GedachtenOptimizer getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GedachtenOptimizerOpBasisCreativiteit();
        }
        return uniqueInstance;
    }

    @Override
    public void optimizeGedachten(Gedachte[] gedachten) {
        throw new UnsupportedOperationException();
    }

}
