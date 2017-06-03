package be.howest.klooster.core;

import be.howest.util.Tools;
import java.util.Random;

/**
 *
 * @author Hayk
 */
public final class Persoonlijkheid {

    public static final int MIN_GOEDHEID = 0;
    public static final int MAX_GOEDHEID = 99;
    private static final int MAX_GOEDHEID_EVIL = MAX_GOEDHEID / 3;
    private static final int MAX_GOEDHEID_NEUTRAL = 2 * (MAX_GOEDHEID / 3);

    public static final int MIN_CREATIVITEIT = 0;
    public static final int MAX_CREATIVITEIT = 99;
    private static final int MAX_CREATIVITEIT_LAWFUL = MAX_CREATIVITEIT / 3;
    private static final int MAX_CREATIVITEIT_NEUTRAAL = 2 * (MAX_CREATIVITEIT / 3);

    private int goedheid = MIN_GOEDHEID;
    private int creativiteit = MIN_CREATIVITEIT;

    private Persoonlijkheid() {
    }
    
    Persoonlijkheid(int goedheid, int creativiteit) {
        setGoedheid(goedheid);
        setCreativiteit(creativiteit);
    }
    
    static Persoonlijkheid createRandomPersoonlijkheid() {
        Random random = new Random();
        Persoonlijkheid instance = new Persoonlijkheid();
        instance.goedheid = Tools.getRandomInt(MIN_GOEDHEID, MAX_GOEDHEID, random);
        instance.creativiteit = Tools.getRandomInt(MIN_CREATIVITEIT,
                MAX_CREATIVITEIT, random);
        return instance;
    }
    
    int getGoedheid() {
        return goedheid;
    }

    void setGoedheid(int goedheid) {
        if (goedheid < MIN_GOEDHEID) {
            this.goedheid = MIN_GOEDHEID;
        } else if (goedheid > MAX_GOEDHEID) {
            this.goedheid = MAX_GOEDHEID;
        } else {
            this.goedheid = goedheid;
        }
    }

    int getCreativiteit() {
        return creativiteit;
    }

    void setCreativiteit(int creativiteit) {
        if (creativiteit < MIN_CREATIVITEIT) {
            this.creativiteit = MIN_CREATIVITEIT;
        } else if (creativiteit > MAX_CREATIVITEIT) {
            this.creativiteit = MAX_CREATIVITEIT;
        } else {
            this.creativiteit = creativiteit;
        }
    }

    private String goedheidToString() {
        if (goedheid == MIN_GOEDHEID) {
            return Berichten.PUUR_KWAAD;
        }
        if (goedheid > MIN_GOEDHEID && goedheid <= MAX_GOEDHEID_EVIL) {
            return Berichten.KWAAD;
        }
        if (goedheid > MAX_GOEDHEID_EVIL && goedheid <= MAX_GOEDHEID_NEUTRAL) {
            return Berichten.NEUTRAAL;
        }
        if (goedheid > MAX_GOEDHEID_NEUTRAL && goedheid < MAX_GOEDHEID) {
            return Berichten.GOED;
        }
        if (goedheid == MAX_GOEDHEID) {
            return Berichten.ABSOLUUT_GOED;
        }
        return "";
    }
    
    private String creativiteitToString() {
        if (creativiteit >= MIN_CREATIVITEIT && creativiteit <= MAX_CREATIVITEIT_LAWFUL) {
            return Berichten.RECHTMATIG;
        }
        if (creativiteit > MAX_CREATIVITEIT_LAWFUL && creativiteit <= MAX_CREATIVITEIT_NEUTRAAL) {
            return Berichten.NEUTRAAL;
        }
        if (creativiteit > MAX_CREATIVITEIT_NEUTRAAL && creativiteit <= MAX_CREATIVITEIT) {
            return Berichten.CHAOTISCH;
        }
        return "";
    }

    @Override
    public String toString() {
        return goedheidToString() + " " + creativiteitToString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.goedheid;
        hash = 29 * hash + this.creativiteit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persoonlijkheid other = (Persoonlijkheid) obj;
        if (this.goedheid != other.goedheid) {
            return false;
        }
        return this.creativiteit == other.creativiteit;
    }
}
