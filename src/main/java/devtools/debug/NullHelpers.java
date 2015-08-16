package devtools.debug;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/** Contains methods for handling null-pointers.
 *
 * <br/><br/>Created by nico on 16.08.15.
 */
public final class NullHelpers {
    private NullHelpers() { }

    /**
     * Tests if the given objects aren't null.
     *
     *  @see DebugHelpers#requireNotNull(Object...)
     */
    public static void requireNotNull(Object... objects) {
        DebugHelpers.requireNotNull(objects);
    }

    /**
     * Tests if the given object isn't null.
     *
     * @see DebugHelpers#notNull(Object)
     */
    public static boolean notNull(Object o) {
        return DebugHelpers.notNull(o);
    }

    /**
     * Tests if the given object is null and throws an error, if it's null.
     *
     * @see DebugHelpers#notNullError(Object)
     */
    public static void notNullError(Object o) {
        DebugHelpers.notNullError(o);
    }

    /**
     * Turns the result of the given function into an optional for handling eventually given null-references.
     *
     * <br/>This can be handy if a function returns eventually a null-reference.
     *
     * @param f the supplier that could return a null-reference
     * @param <A> the type of the result from f
     * @return an optional containing the result of f if it's not null, an empty optional otherwise
     */
    public static <A> Optional<A> nullToOptional(Supplier<A> f) {
        A value = f.get();
        return (value == null) ? Optional.<A>empty() : Optional.of(value);
    }
}
