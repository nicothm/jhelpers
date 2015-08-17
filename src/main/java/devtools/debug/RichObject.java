package devtools.debug;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Helper with magic toString(), hashCode() and equals() methods.
 * <P>Simply call all the functions with the objects-reference and viola: there is a usefull implementation.</P>
 * <P>Created by nico on 15.08.15.</P>
 */
public final class RichObject {
    private static final Comparator<Field> sortByName = (a,b) -> a.getName().compareTo(b.getName());

    private RichObject() { }

    /**
     * An generic implementation of {@link Object#toString()}.
     * <P>This method walks over all fields of object o and wrights them together with the field's name into the resulting string.</P>
     *
     * @param o the object for which the toString should be created, normally this
     * @return a usefull string-representation of o
     */
    public static String toString(Object o) {
        if(o == null) return "Null-Reference";
        else {
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
    }

    /**
     * Creates a hashCode for the given object o.
     * <P>It's an implementation for {@link Object#hashCode()}.</P>
     *
     * @param o the object for which an hashCode should be calculated, normally this
     * @return an hashCode from o, based on the string-representation of o
     */
    public static int hashCode(Object o) {
        return o.toString().hashCode();
    }

    /**
     * An generic implementation of {@link java.lang.Object#equals(Object)}.
     *
     * @param oThis the object for which equals should be executed, normally this
     * @param other the other object which should be checked against oThis (equals's obj)
     * @return true if oThis and other are "equals" based on their fields, false otherwise
     */
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
