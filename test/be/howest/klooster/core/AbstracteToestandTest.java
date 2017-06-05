package be.howest.klooster.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayk
 */
public class AbstracteToestandTest {
    
    @Test
    public void AbstracteToestand_implementeert_Toetand() {
        assertTrue(Toestand.class.isAssignableFrom(AbstracteToestand.class));
    }
    
}
