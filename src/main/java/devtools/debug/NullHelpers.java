package devtools.debug;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by nico on 16.08.15.
 */
public final class NullHelpers {
    private NullHelpers() { }

    public static void requireNotNull(Object... objects) {
        DebugHelpers.requireNotNull(objects);
    }
    public static boolean notNull(Object o) {
        return DebugHelpers.notNull(o);
    }
    public static void notNullError(Object o) {
        DebugHelpers.notNullError(o);
    }
    public static <A> Optional<A> nullToOptional(Supplier<A> f) {
        A value = f.get();
        return (value == null) ? Optional.<A>empty() : Optional.of(value);
    }
}
