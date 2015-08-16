package devtools.concurrent;

import org.junit.Test;

import static devtools.concurrent.ThreadHelpers.*;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Test
    public void threadSafeRunnableTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Runnable throwsException = () -> {
            System.out.println("extra thread with throwable.");
            throw new IllegalArgumentException("haha exception!");
        };

        pool.submit(exceptionSaveRunnable(throwsException));

        Thread.sleep(100);
    }
}
