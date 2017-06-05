package be.howest.klooster.core;

import java.util.List;

/**
 *
 * @author Hayk
 */
abstract class AbstracteToestand implements Toestand {

    protected final Pater pater;
    
    private static final GedachtenOptimizer OPTIMIZER_CREATIVITEIT
            = GedachtenOptimizerOpBasisVanCreativiteit.getInstance();
    private static final GedachtenOptimizer OPTIMIZER_GEMIDDELDE_PERSOONLIJKHEID
            = GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid.getInstance();
    private static final GedachtenOptimizer OPTIMIZER_GOEDHEID
            = GedachtenOptimizerOpBasisVanGoedheid.getInstance();
    private GedachtenOptimizer currentGedachtenOptimizer
            = OPTIMIZER_CREATIVITEIT;

    AbstracteToestand(Pater pater) {
        this.pater = pater;
    }
    
    @Override
    public Gedachte bid() {
        int concept = Inspiratie.getInstance().inspireerMij();
        Persoonlijkheid mening = pater.getPersoonlijkheid();
        Gedachte gedachte = new Gedachte(concept, mening);
        pater.addGedachte(gedachte);
        pater.setInfo(String.format(Berichten.BID, pater.getNaam(), gedachte));
        return gedachte;
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
    public void luisterNaar(Pater anderePater) {
        anderePater.spreekTegen(pater);
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
        denkNa(currentGedachtenOptimizer);
        veranderGedachtenOptimizer();
    }
    
    private void veranderGedachtenOptimizer() {
        if (currentGedachtenOptimizer == OPTIMIZER_CREATIVITEIT) {
            currentGedachtenOptimizer = OPTIMIZER_GEMIDDELDE_PERSOONLIJKHEID;
            return;
        }
        if (currentGedachtenOptimizer == OPTIMIZER_GEMIDDELDE_PERSOONLIJKHEID) {
            currentGedachtenOptimizer = OPTIMIZER_GOEDHEID;
            return;
        }
        currentGedachtenOptimizer = OPTIMIZER_CREATIVITEIT;
    }

    @Override
    public void denkNa(GedachtenOptimizer optimizer) {
        setInfoVoorHetNadenken();
        int aantalGeoptimaliseerdeGedachten = optimizer.optimaliseerGedachten(pater);
        if (aantalGeoptimaliseerdeGedachten > 0 || !pater.hoofdZitVol()) {
            veranderPersoonlijkheid();
            setInfoNadenkenGelukt();
        } else {
            setInfoNadenkenMislukt();
        }
        setInfoNaHetNadenken();
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

    private void veranderPersoonlijkheid() {
        List<Gedachte> gedachten = pater.getGedachtenList();
        List<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(gedachten);
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid
                .combineer(meningen);
        Persoonlijkheid persoonlijkheid = pater.getPersoonlijkheid();
        persoonlijkheid = Persoonlijkheid.verschil(persoonlijkheid, gemiddeldeMening);
        persoonlijkheid.divide(10);
        pater.setPersoonlijkheid(persoonlijkheid);
    }
}
