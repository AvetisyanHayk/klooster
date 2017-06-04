package be.howest.klooster.toestand;

import be.howest.klooster.core.Berichten;
import be.howest.klooster.core.Gedachte;
import be.howest.klooster.core.Inspiratie;
import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Persoonlijkheid;
import be.howest.klooster.core.Woord;
import be.howest.klooster.core.GedachtenOptimizer;
import be.howest.klooster.core.GedachtenOptimizerOpBasisVanGoedheid;
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
            // TODO hier komt de code anders gebeurt er niks
            throw new UnsupportedOperationException();
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Woord spreek() {
        throw new UnsupportedOperationException();
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
        Set<Gedachte> gedachten = pater.getSetVanGedachten();
        Set<Persoonlijkheid> meningen = Gedachte.mapMeningenUitGedachten(gedachten);
        Persoonlijkheid gemiddeldeMening = Persoonlijkheid
                .getGemiddeldePersoonlijkheid(meningen);
        Persoonlijkheid persoonlijkheid = pater.getPersoonlijkheid();
        persoonlijkheid.subtract(gemiddeldeMening).divide(10);
        pater.setPersoonlijkheid(persoonlijkheid);
    }
}
