package devtools.concurrent;

/**
 * Helpers for working with concurrency, threads, runnables & callables.
 * <br/><br/>Created by nico on 15.08.15.
 */
public final class ThreadHelpers {
    private ThreadHelpers() {}

    /**
     * Converts the given runnable into a thread.
     * @param r the runnable for the thread
     * @return a thread that contains r
     */
    public static Thread asThread(Runnable r) {
        return new Thread(r);
    }

    /**
     * Returns a runnable that runs the given runnable,
     * except that it explicitly prints-out a stacktrace and exits the program, if an exception is thrown. <br/>
     *
     * This can be useful when working with threadpools, which normally hides exceptions.
     * @param r the runnable that could throw an exception
     * @return a new runnable which prints out the stacktrace of the thrown exception
     */
    public static Runnable exceptionSaveRunnable(Runnable r) {
        return () -> {
            try {
                r.run();
            }catch(Throwable t) {
                t.printStackTrace();
                System.exit(-1);
            }
        };
    }
}
