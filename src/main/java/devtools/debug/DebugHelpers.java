package devtools.debug;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Helper for debugging a class and for avoiding common problems with null-references, not initialzed values, non-final values etc.
 * <br/><br/>Created by nico on 15.08.15.
 */
public final class DebugHelpers {
    private static final String WARNING = "[ WARNING ]: ";
    private DebugHelpers() {}

    /**
     * Tests if the given condition is fulfilled.
     *
     * @see DebugHelpers#require(boolean, String)
     * @param condition the tested condition
     * @throws IllegalArgumentException if the condition isn't fulfilled
     */
    public static void  require(boolean condition)  {
        require(condition, "Given requirement wasn't fulfilled.");
    }

    /**
     * Tests if the given condition is fulfilled, throws an exception if not.
     *
     * @param condition the tested condition
     * @param msg the message that should be in the exception
     * @throws IllegalArgumentException if the condition isn't fulfilled
     */
    public static void require(boolean condition, String msg)  {
        if(!condition) throw new IllegalArgumentException(msg);
    }

    /**
     * Tests that all given objects aren't null, throws an exception if not.
     *
     * @param objects the objects that should be checked against null
     * @throws NullPointerException if 1 of the objects is null
     */
    public static void requireNotNull(Object... objects) {
        for(Object o : objects)
            if(o == null)
                throw new NullPointerException("A non-null reference was required.");
    }

    /**
     * Tests the given object against null.
     *
     * @param o the object for the test
     * @return true if o != null, false otherwise
     */
    public static boolean notNull(Object o) {
        return o != null;
    }

    /**
     * Tests the given object against null, throws an exception if o is null.
     *
     * @param o the object for the test
     * @throws IllegalStateException if o == null
     */
    public static void notNullError(Object o) {
        if(o == null) throw new IllegalStateException("Given Object ("+o+") was null!");
    }

    /**
     * Throws an MissingImplementationException() to indicate that an implementation is missing.<br/>
     * This can be useful when implementing an interface method-for-method.
     * All not-implemented methods of the interface calls this function and throws an exception,
     * so all callers of the function knows that this method is missing.
     *
     * @throws MissingImplementationException everytime
     */
    public static void todo() {
        throw new MissingImplementationException();
    }

    /**
     * Throws an MissingImplementationException() with the given message to indicate that an implementation is missing.<br/>
     * This can be useful when implementing an interface method-for-method.
     * All not-implemented methods of the interface calls this function and throws an exception,
     * so all callers of the function knows that this method is missing.
     *
     * @param msg the message for the exception
     * @throws MissingImplementationException everytime
     */
    public static void todo(String msg) {
        throw new MissingImplementationException(msg);
    }

    /**
     * Tests that <B>all fields</B> of the given object <B>aren't null.</B><br/>
     *
     * This method is useful when debugging a not-running systems or to check
     * that every field of the object is initialized with "good" references. <br/><br/>
     *
     * Normally this method is called with <B>this</B> as argument.
     *
     * @see DebugHelpers#fieldsAreFinal(Object)
     * @see DebugHelpers#fieldsArePrivate(Object)
     * @param othis the object with the fields
     */
    public static void fieldsNotNull(Object othis) {
        final Class<?> clazz = othis.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);

        for(Field f : fields) {
            try {
                if (f.get(othis) == null)
                    throw new NullPointerException("Field: " + f.getName() + " is null!");
            }catch(IllegalAccessException iae) {
                //ignore
            }
        }
    }

    /**
     * Tests that <B>all fields</B> of the given object <B>are final.</B><br/>
     *
     * This method is useful when debugging a not-running systems or to check
     * that every field of the object is final. <br/><br/>
     *
     * Normally this method is called with <B>this</B> as argument.
     *
     * @see DebugHelpers#fieldsArePrivate(Object)
     * @see DebugHelpers#fieldsNotNull(Object)
     * @param othis the object with the fields
     */
    public static void fieldsAreFinal(Object othis) {
        final Class<?> clazz = othis.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);

        for(Field f : fields) {
            int modifier = f.getModifiers();
            if(!Modifier.isFinal(modifier)) {
                System.out.println(WARNING+clazz.getCanonicalName()+" - Field "+f.getName()+" is not final!");
            }

        }
    }

    /**
     * Tests that <B>all fields</B> of the given object <B>are private.</B><br/>
     *
     * This method is useful when debugging a not-running systems or to check
     * that every field of the object is private. <br/><br/>
     *
     * Normally this method is called with <B>this</B> as argument.
     *
     * @see DebugHelpers#fieldsAreFinal(Object)
     * @see DebugHelpers#fieldsNotNull(Object)
     * @param othis the object with the fields
     */
    public static void fieldsArePrivate(Object othis) {
        final Class<?> clazz = othis.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);

        for(Field f : fields) {
            int modifier = f.getModifiers();
            if(!Modifier.isPrivate(modifier)) {
                System.out.println(WARNING+clazz.getCanonicalName()+" - Field "+f.getName()+" is not private!");
            }

        }
    }

    public static class MissingImplementationException extends IllegalStateException {
        public MissingImplementationException() { super(); }
        public MissingImplementationException(String s) { super(s); }
    }
}
