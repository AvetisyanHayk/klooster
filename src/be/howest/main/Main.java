/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        roger.addObserver(program);
        roger.spreek();
        roger.bid();
        
        roger.bid();
        roger.bid();
        roger.bid();
        roger.bid();
        roger.info();
        roger.denkNa();
    }

}
