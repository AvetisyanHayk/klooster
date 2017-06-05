package be.howest.klooster.core;

/**
 *
 * @author Hayk
 */
final class HoofdZitVolMetGedachtenToestand extends AbstracteToestand {

    HoofdZitVolMetGedachtenToestand(Pater pater) {
        super(pater);
    }

    @Override
    public Gedachte bid() {
        denkNa();
        return null;
    }

    @Override
    public void luister(Woord woord) {
        if (woord != null) {
            denkNa();
        }
    }
    
    @Override
    public void denkNa() {
        pater.setInfo(String.format(Berichten.DENK_NA_HOOFD_ZIT_VOL,
                pater.getNaam()));
        super.denkNa();
        if (!pater.hoofdZitVol() && pater.getToestand()
                .equals(pater.getHoofdZitVolMetGedachtenToestand())) {
            pater.setToestand(pater.getNormaleToestand());
        }
    }
}
