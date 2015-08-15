package devtools.concurrent;

import org.junit.Test;

import static devtools.concurrent.ThreadHelpers.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by nico on 15.08.15.
 */
public class ThreadHelpersTest {
    @Test
    public void test() throws InterruptedException {
        final String mainThread = Thread.currentThread().getName();

        Runnable r = () -> {
            assertNotSame(mainThread, Thread.currentThread().getName());

            for (int i = 0; i < 10; i++) {
                System.out.print(i + ", ");
                assertNotSame(mainThread, Thread.currentThread().getName());
            }
        };


        asThread(r).start();
        Thread.sleep(10);
    }
}
