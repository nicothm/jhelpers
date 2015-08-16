package devtools.debug;

import org.junit.Test;

import java.net.URL;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by nico on 16.08.15.
 */
public class NullHelpersTest {
    @Test
    public void nullToOptionalTest() {
        Object o1 = new Object();
        Object o2 = null;
        URL rsc = getClass().getResource("notThere.txt");

        Optional<Object> o1Option = NullHelpers.nullToOptional( () -> o1 );
        Optional<Object> o2Option = NullHelpers.nullToOptional(() -> o2);
        Optional<URL> rscOption = NullHelpers.nullToOptional( () -> rsc );

        assertEquals(true, o1Option.isPresent());
        assertEquals(false, o2Option.isPresent());
        assertEquals(false, rscOption.isPresent());

    }
}
