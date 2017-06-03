/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.klooster.toestand;

import be.howest.klooster.Pater;

/**
 *
 * @author Hayk
 */
public class BasisToestand implements Toestand {
    private final Pater pater;
    
    public BasisToestand(Pater pater) {
        this.pater = pater;
    }

    @Override
    public void bid() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void spreek() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void luister() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void denkNa() {
        throw new UnsupportedOperationException();
    }
}
