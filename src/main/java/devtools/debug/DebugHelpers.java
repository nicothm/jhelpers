package devtools.debug;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by nico on 15.08.15.
 */
public final class DebugHelpers {
    private static final String WARNING = "[ WARNING ]: ";
    private DebugHelpers() {}

    public static void  require(boolean b)  {
        require(b, "Given requirement wasn't fulfilled.");
    }
    public static void require(boolean b, String msg)  {
        if(!b) throw new IllegalArgumentException(msg);
    }
    public static void requireNotNull(Object... objects) {
        for(Object o : objects)
            if(o == null)
                throw new NullPointerException("A non-null reference was required.");
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
