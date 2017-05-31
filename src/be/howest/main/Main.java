/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.main;

import be.howest.klooster.Pater;
import be.howest.klooster.Program;

/**
 *
 * @author Hayk
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pater pater = new Pater("John Smith");
        Program program = new Program(pater);
        pater.addObserver(program);
    }

}
