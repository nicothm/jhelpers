package devtools.math;

import java.math.BigDecimal;
import java.util.Random;

import static devtools.debug.DebugHelpers.*;

/**
 * Created by nico on 16.08.15.
 */
public final class MathHelper {
    private static Random rnd = new Random();

    private MathHelper() { }

    public static int getRandom(final int min, final int max) {
        require(max>0 && min>0, "Min & Max must be >0.");
        require(max > min, "Max has to be  > min.");

        return rnd.nextInt( (max-min) ) + min;
    }

    public static double round(double no, int scale) {
        BigDecimal bd = new BigDecimal(no).setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public static double roundOnTwo(double no) {
        return round(no, 2);
    }
    public static double roundOnThree(double no) {
        return round(no, 3);
    }
}
