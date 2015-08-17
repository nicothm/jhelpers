package devtools.math;

import java.math.BigDecimal;
import java.util.Random;

import static devtools.debug.DebugHelpers.*;

/**
 * Helper for common mathematical problems.
 *
 * <P>Created by nico on 16.08.15.</P>
 */
public final class MathHelper {
    private static Random rnd = new Random();

    private MathHelper() { }

    /**
     * Creates a new random number between the given range.
     *
     * @param min the start-value of the range, inclusive
     * @param max the end-value of the range, exclusive
     * @return a random-number between min and max
     */
    public static int getRandom(final int min, final int max) {
        require(max>0 && min>0, "Min & Max must be >0.");
        require(max > min, "Max has to be  > min.");

        return rnd.nextInt( (max-min) ) + min;
    }

    /**
     * Rounds the given double-value onto the given scale.
     *
     * @param no the number for the rounding-operation
     * @param scale the number of decimals
     * @return no rounded on scale's decimals
     */
    public static double round(double no, int scale) {
        BigDecimal bd = new BigDecimal(no).setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Rounds the given number on 2 decimals.
     *
     * @see MathHelper#round(double, int)
     * @param no the number for the rounding-operation
     * @return no rounded on 2 decimals
     */
    public static double roundOnTwo(double no) {
        return round(no, 2);
    }

    /**
     * Rounds the given number on 3 decimals.
     *
     * @see MathHelper#round(double, int)
     * @param no the number for the rounding-operation
     * @return no rounded on 3 decimals
     */
    public static double roundOnThree(double no) {
        return round(no, 3);
    }
}
