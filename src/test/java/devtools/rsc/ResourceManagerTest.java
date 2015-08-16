package devtools.rsc;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by nico on 16.08.15.
 */
public class ResourceManagerTest {
    @Test
    public void rscTest() throws MalformedURLException {
        ResourceManager rm = new ResourceManager("./resources");
        URL url = getClass().getResource("./resources/exp.txt");

        assertEquals("./resources", rm.getRoot());
        assertEquals(null, rm.getRsc("bla.txt"));
        assertEquals(url, rm.getRsc("exp.txt"));

        ResourceManager rm2 = new ResourceManager("/usr");
        URL url2 = getClass().getResource("/usr/share");
        assertEquals(url2, rm2.getRsc("share"));
    }
}
