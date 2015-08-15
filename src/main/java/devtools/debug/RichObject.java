package devtools.debug;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by nico on 15.08.15.
 */
public final class RichObject {
    private static final Comparator<Field> sortByName = (a,b) -> a.getName().compareTo(b.getName());

    private RichObject() { }

    public static String toString(Object o) {
        final Class<?> clazz = o.getClass();
        final StringBuilder sb = new StringBuilder("[ ");
        final Field[] fields = clazz.getDeclaredFields();
        //sort by name
        Arrays.sort(fields, sortByName );
        AccessibleObject.setAccessible(fields, true);

        for(Field f : fields) {
            try {
                sb.append(f.getName() + ":" + f.get(o) + " ");
            } catch(IllegalAccessException e) {
                sb.append(f.getName() + ": notReadable" +" ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static int hashCode(Object o) {
        return o.toString().hashCode();
    }

    public static boolean equals(Object oThis, Object other) {
        if(other == oThis) return true; //same?
        else if(oThis == null) return false; //this-ref null?
        else if(other == null) return false; //other-ref null?
        else if(oThis.getClass() != other.getClass()) return false; //same class?
        else { //ok check all fields
            final Class<?> clazzThis = oThis.getClass();
            final Class<?> clazzOther = other.getClass();
            final Field[] fieldsThis = clazzThis.getDeclaredFields();
            final Field[] fieldsOther = clazzOther.getDeclaredFields();
            //check that the fields are in same order for access with index
            Arrays.sort(fieldsThis, sortByName);
            Arrays.sort(fieldsOther, sortByName);
            AccessibleObject.setAccessible(fieldsThis, true);
            AccessibleObject.setAccessible(fieldsOther, true);

            for(int idx=0; idx<fieldsThis.length; idx++) {
                Field fieldThis = fieldsThis[idx];
                Field fieldOther = fieldsOther[idx];
                 try {
                     if (!fieldThis.get(oThis).equals(fieldOther.get(other))) {
                        return false;
                     }
                 }catch(IllegalAccessException e) {
                     System.err.println("Can't access field: "+fieldThis.getName());
                 }
            }

            return true;
        }
    }
}
