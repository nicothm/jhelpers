package devtools.debug;

/**
 * Created by nico on 15.08.15.
 */
public final class DebugHelpers {
    private DebugHelpers() {}

    public static void  require(boolean b)  {
        require(b, "Given requirement wasn't fulfilled.");
    }
    public static void require(boolean b, String msg)  {
        if(!b) throw new IllegalArgumentException(msg);
    }
    public static boolean notNull(Object o) {
        return o != null;
    }
    public static void notNullError(Object o) {
        if(o == null) throw new IllegalStateException("Given Object ("+o+") was null!");
    }

    public static void todo() {
        throw new MissingImplementationException();
    }

    public static void todo(String msg) {
        throw new MissingImplementationException(msg);
    }

    public static class MissingImplementationException extends IllegalStateException {
        public MissingImplementationException() { super(); }
        public MissingImplementationException(String s) { super(s); }
    }
}
