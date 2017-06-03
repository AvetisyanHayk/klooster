package be.howest.klooster.core;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Hayk
 */
public final class Program implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o);
    }

}
