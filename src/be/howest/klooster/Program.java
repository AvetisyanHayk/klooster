package be.howest.klooster;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public final class Program implements Observer {

    private final Set<Pater> paters = new LinkedHashSet<>();

    public Program(Pater... paters) {
        this(Arrays.asList(paters));
    }

    public Program(Collection<Pater> paters) {
        addAllPaters(paters);
    }

    public void addPater(Pater pater) {
        paters.add(pater);
    }

    public void addAllPaters(Pater... paters) {
        addAllPaters(Arrays.asList(paters));
    }

    public void addAllPaters(Collection<Pater> paters) {
        this.paters.addAll(paters);
    }

    public void remove(Pater pater) {
        paters.remove(pater);
    }

    public void remove(String name) {
        if (name != null) {
            for (Pater pater : paters) {
                if (name.equals(pater.getNaam())) {
                    remove(pater);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        paters.forEach(System.out::println);
    }

}
