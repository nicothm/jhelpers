package devtools.debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
    }

    @Test
    public void notNullTest() {
        Object o1 = new Object();
        Object o2 = null;

        assertTrue( notNull(o1) );
        assertFalse(notNull(o2));

        notNullError(o1);

        try {
            notNullError(o2);
        }catch(IllegalStateException e) {
            //ok should throw an exception
        }
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
}
