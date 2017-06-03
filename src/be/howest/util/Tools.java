package be.howest.util;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Hayk
 */
public final class Tools {

    private Tools() {
    }

    public static int getRandomInt(int limit) {
        if (limit > 0) {
            return getRandomInt(0, limit);
        }
        if (limit < 0) {
            return getRandomInt(limit, 0);
        }
        return limit;
    }

    public static int getRandomInt(int min, int max) {
        return getRandomInt(min, max, new Random());
    }

    public static int getRandomInt(int min, int max, Random random) {
        if (min == max) {
            return min;
        }
        if (max < min) {
            int temp = max;
            max = min;
            min = temp;
        }
        return random.nextInt(Math.abs(max - min) + 1) + min;
    }
    
    public static String toZin(String... woorden) {
        return toZin(woorden, "");
    }
    
    public static String toZinMetCommas(String... woorden) {
        return toZin(woorden, ",");
    }
    
    private static String toZin(String[] woorden, String delimiter) {
        if (woorden == null || woorden.length == 0) {
            return "";
        }
        if (woorden.length == 1) {
            return woorden[0];
        }
        return Arrays.<String>asList(woorden).stream()
                .reduce("", (previous, current)
                        -> (!"".equals(previous))
                ? previous + delimiter + " " + current
                : current);
    }

}
