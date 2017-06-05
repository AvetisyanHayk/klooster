/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.klooster.core;

import java.util.Observer;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Hayk
 */
public class ProgramTest {
    
    @Test
    public void Program_is_observer() {
        assertTrue(Observer.class.isAssignableFrom(Program.class));
    }
}
