package devtools.collection;


import org.junit.Test;

import static devtools.collection.CollectionHelper.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CollectionHelperTest {

    @Test
    public void mkStringTest() {
        List<String> names = Arrays.asList("Friedrich", "Steffi", "Meier", "Hans");
        List<Integer> numbers = Arrays.asList(4,5,8,9,12,456);

        String expected1 = names.stream().collect(Collectors.joining(","));
        String expected2 = numbers.stream().map(x -> x.toString()).collect(Collectors.joining(","));

        assertEquals(expected1, mkString(names, ","));
        assertEquals(expected2, mkString(numbers, ","));
    }
}
