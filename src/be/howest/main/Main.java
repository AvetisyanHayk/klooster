/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.main;

import be.howest.klooster.core.GedachtenOptimizerOpBasisVanCreativiteit;
import be.howest.klooster.core.GedachtenOptimizerOpBasisVanGemiddeldePersoonlijkheid;
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
