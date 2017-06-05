package be.howest.klooster.core;

import java.util.Set;

/**
 *
 * @author Hayk
 */
abstract class AbstracteToestand implements Toestand {

    protected final Pater pater;

    AbstracteToestand(Pater pater) {
        this.pater = pater;
    }

    @Override
    public void bid() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid mening = pater.getPersoonlijkheid();
        Gedachte gedachte = new Gedachte(concept, mening);
        pater.addGedachte(gedachte);
        pater.setInfo(String.format(Berichten.BID, pater.getNaam(), gedachte));
    }

    @Override
    public void luister(Woord woord) {
        if (woord != null) {
            Persoonlijkheid gemiddeldeMening = Persoonlijkheid.combineer(
                    pater.getPersoonlijkheid(), woord.getBegeestering(),
                    woord.getGedachte().getMening()
            );
            pater.addGedachte(new Gedachte(woord.getConcept(), gemiddeldeMening));
        }
    }

    @Override
    public Woord spreek() {
        Gedachte nextGedachte = pater.nextGedachte();
        if (nextGedachte != null) {
            return nextGedachte.verwoord(pater.getPersoonlijkheid());
        }
        return null;
    }

    @Override
    public void spreekTegen(Pater anderePater) {
        anderePater.luister(spreek());
    }
    
    @Override
    public void denkNa() {
        denkNa(GedachtenOptimizerOpBasisVanGoedheid.getInstance());
    }

    @Override
    public void denkNa(GedachtenOptimizer optimizer) {
        setInfoVoorHetNadenken();
        int aantalGeoptimaliseerdeGedachten = optimaliseerGedachten(optimizer);
        setInfoNaHetNadenken();
        if (aantalGeoptimaliseerdeGedachten > 0 || !pater.hoofdZitVol()) {
            veranderPersoonlijkheid();
            setInfoNadenkenGelukt();

        } else {
            setInfoNadenkenMislukt();
        }
    }

    private void setInfoVoorHetNadenken() {
        pater.setInfo(String.format(Berichten.DENK_NA_VOORAF, pater.getNaam(),
                pater.getPersoonlijkheid()));
    }

    private void setInfoNaHetNadenken() {
        pater.setInfo(String.format(Berichten.DENK_NA_ACHTERAF, pater.getNaam(),
                pater.getPersoonlijkheid()));
    }

    private void setInfoNadenkenGelukt() {
        pater.setInfo(String.format(Berichten.DENK_NA_GELUKT, pater.getNaam(),
                pater.getAantalGedachten()));
    }

    private void setInfoNadenkenMislukt() {
        pater.setInfo(String.format(Berichten.DENK_NA_MISLUKT,
                pater.getNaam()));
    }

    private int optimaliseerGedachten(GedachtenOptimizer optimizer) {
        return optimizer.optimaliseerGedachten(pater);
    }

    private void veranderPersoonlijkheid() {
        Set<Gedachte> gedachten = pater.getGedachtenSet();
        Set<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(gedachten);
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid
                .combineer(meningen);
        Persoonlijkheid persoonlijkheid = pater.getPersoonlijkheid();
        persoonlijkheid.subtract(gemiddeldeMening).divide(10);
        pater.setPersoonlijkheid(persoonlijkheid);
    }
}
