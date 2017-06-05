package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
final class BasisToestand extends AbstracteToestand {

    BasisToestand(Pater pater) {
        super(pater);
    }

    @Override
    public Gedachte bid() {
        Gedachte gedachte = super.bid();
        if (gedachte != null) {
            pater.setToestand(pater.getNormaleToestand());
        }
        return gedachte;
    }

    @Override
    public Woord spreek() {
        pater.setInfo(String.format(Berichten.SPREEK_BASISTOESTAND,
                pater.getNaam()));
        return null;
    }

    @Override
    public void luister(Woord woord) {
        super.luister(woord);
        if (woord != null) {
            pater.setToestand(pater.getNormaleToestand());
        }
    }

    @Override
    public void denkNa() {
        pater.setInfo(String.format(Berichten.DENK_NA, pater.getNaam(),
                pater.getAantalGedachten()));
        super.denkNa();
    }
}
