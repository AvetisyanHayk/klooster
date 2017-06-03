package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
public class GedachtenOptimizerOpBasisGemiddeldePersoonlijkheid
        implements GedachtenOptimizer {

    private static GedachtenOptimizer uniqueInstance;

    public static synchronized GedachtenOptimizer getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance
                    = new GedachtenOptimizerOpBasisGemiddeldePersoonlijkheid();
        }
        return uniqueInstance;
    }

    @Override
    public int optimizeGedachten(Pater pater) {
        throw new UnsupportedOperationException();
    }

}
