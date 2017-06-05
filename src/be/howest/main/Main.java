package be.howest.main;

import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Program;

/**
 *
 * @author Hayk
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Program program = new Program();
        Pater roger = new Pater("Roger");
        Pater jonas = new Pater("Jonas");
        roger.addObserver(program);
        jonas.addObserver(program);
        
        roger.spreek();
        roger.bid();
        roger.bid();
        roger.bid();
        jonas.bid();
        roger.spreekTegen(jonas);
        roger.luisterNaar(jonas);
        jonas.bid();
        jonas.denkNa();
        roger.denkNa();
        roger.info();
        jonas.info();
    }

}
