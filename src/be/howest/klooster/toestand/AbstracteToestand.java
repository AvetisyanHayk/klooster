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
public abstract class AbstracteToestand implements Toestand {

    protected final Pater pater;
    protected String info = "";

    AbstracteToestand(Pater pater) {
        this.pater = pater;
    }

    protected String getInfoOverAantalGedachten() {
        int aantalGedachten = pater.getAantalGedachten();
        StringBuilder sb = new StringBuilder();
        sb.append(aantalGedachten);
        String gedacht = " gedacht";
        if (aantalGedachten != 1) {
            gedacht += "en";
        }
        return sb.append(gedacht).append(" in zijn hoofd").toString();
    }

    @Override
    public String toString() {
        return info;
    }
}
