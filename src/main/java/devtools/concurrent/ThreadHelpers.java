package devtools.concurrent;

/**
 * Created by nico on 15.08.15.
 */
public final class ThreadHelpers {
    private ThreadHelpers() {}

    public static Thread asThread(Runnable r) {
        return new Thread(r);
    }
}
