package devtools.debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.InputStream;

import static devtools.debug.DebugHelpers.*;
import static org.junit.Assert.*;

/**
 * Created by nico on 15.08.15.
 */
public class DebugHelpersTest {
    @Test
    public void requireTest() {
        require(5>4);
        require(5>4, "Argument x wasn't > 4"); //x is 5
        requireNotNull(new Object(), new Object(), new String(), System.in );
        try {
            require(5>10);
        } catch(IllegalArgumentException e) {
            //ok should be throw an exception
        }

        try {
            require(5>10, "Argument x wasn't > 10");
        } catch(IllegalArgumentException e) {
            //ok should throw an exception
            assertSame(e.getMessage(), "Argument x wasn't > 10");
        }

        try {
            requireNotNull(new Object(), new Object(), null, null);
        }catch(NullPointerException npe) {
            //ok should throw exception
        }
    }

    @Test
    public void notNullTest() {
        Object o1 = new Object();
        Object o2 = null;

        assertTrue(notNull(o1));
        assertFalse(notNull(o2));

        notNullError(o1);

        try {
            notNullError(o2);
        }catch(IllegalStateException e) {
            //ok should throw an exception
        }
    }

    @Test
    public void fieldsNotNullTest() {
        Person p1 = new Person("Muster", "Nick");

        try {
            new Person("Nick", null);
        } catch(NullPointerException e) {
            //ok should throw an exception
        }
        try {
            new Person(null, "Nick");
        } catch(NullPointerException e) {
            //ok should throw an exception
        }
        try {
            new Person(null, null);
        } catch(NullPointerException e) {
            //ok should throw an exception
        }
    }

    @Test
    public void fieldsFinalTest() {
        Person p1 = new Person("Hans", "Nick");
        DebugHelpers.fieldsAreFinal(p1);
    }

    @Test
    public void fieldsPrivateTest() {
        SimpleClass sc = new SimpleClass();
        DebugHelpers.fieldsArePrivate(sc);
    }

    @Test
    public void todoTest() {
        try {
            todo();
        } catch(MissingImplementationException e) {
            assertSame(null, e.getMessage());
        }

        final String msg = "Impl error";
        try {
            todo(msg);
        } catch(MissingImplementationException e) {
            assertSame(msg, e.getMessage());
        }
    }

    private class SimpleClass {
        private String name;
        int age;
        public String surname;
    }

    private class Person {
        private final String name;
        private String surname;

        public Person(String n, String s) {
            name = n;
            surname = s;

            fieldsNotNull(this);
        }
    }
}
