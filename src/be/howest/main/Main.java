/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.main;

import be.howest.util.Tools;
import java.util.Random;

/**
 *
 * @author Hayk
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            System.out.println(Tools.getRandomInt(-55, -33, random));
        }

    }

}
