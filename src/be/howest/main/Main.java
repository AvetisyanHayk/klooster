/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.main;

import be.howest.klooster.core.Pater;
import be.howest.klooster.core.Program;
import java.util.Arrays;

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
        Pater pater = new Pater("John Smith");
        pater.addObserver(program);
    }

}
