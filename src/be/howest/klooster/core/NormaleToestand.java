package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
final class NormaleToestand extends AbstracteToestand {

    NormaleToestand(Pater pater) {
        super(pater);
    }

    @Override
    public void bid() {
        super.bid();
        if (pater.hoofdZitVol()) {
            pater.setToestand(pater.getHoofdZitVolMetGedachtenToestand());
        }
    }

    @Override
    public void luister(Woord woord) {
        super.luister(woord);
        if (woord != null && pater.hoofdZitVol()) {
            pater.setToestand(pater.getHoofdZitVolMetGedachtenToestand());
        }
    }

    @Override
    public void denkNa() {
        pater.setInfo(String.format(Berichten.DENK_NA, pater.getNaam(),
                pater.getAantalGedachten()));
        super.denkNa();
    }
}
