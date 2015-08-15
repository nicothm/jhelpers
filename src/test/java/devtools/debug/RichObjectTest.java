package devtools.debug;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nico on 15.08.15.
 */
public class RichObjectTest {
    //objects
    Cl1 objCl1_1 = new Cl1();
    Cl1 objCl1_2 = new Cl1("nick", 40);
    Cl1 objCl1_2Copy = new Cl1("nick", 40);
    Cl2 objCl2_1 =  new Cl2();
    Cl2 objCl2_2 =  new Cl2("nick", 40);
    Cl2 objCl2_2Copy =  new Cl2("nick", 40);

    @Test
    public void toStringTest() {
        final String awaited1 = "[ age:0 name:null ]";
        final String awaited2 = "[ age:40 name:nick ]";

        //toString-results
        String res1_1 = RichObject.toString(objCl1_1);
        String res1_2 = RichObject.toString(objCl1_2);
        String res2_1 = RichObject.toString(objCl2_1);
        String res2_2 = RichObject.toString(objCl2_2);

        assertEquals(awaited1, res1_1);
        assertEquals(awaited2, res1_2);
        assertEquals(awaited1, res2_1);
        assertEquals(awaited2, res2_2);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(objCl1_1.hashCode(), objCl1_1.hashCode());
        assertNotEquals(objCl1_1.hashCode(), objCl2_2.hashCode());
    }

    @Test
    public void equalsTest() {
        assertFalse(objCl1_1.equals(objCl2_1));
        assertFalse(objCl2_1.equals(null));


        assertTrue(RichObject.equals(null, null));
        assertTrue(objCl1_1.equals(objCl1_1));
        assertTrue(objCl2_2.equals(objCl2_2Copy));
        assertTrue(objCl1_2.equals(objCl1_2Copy));
    }
}

// ===== Simple-Test-classes
class Cl2 {
    private String name;
    private int age;

    public Cl2() {}
    public Cl2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return RichObject.toString(this);
    }

    @Override
    public int hashCode() {
        return RichObject.hashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return RichObject.equals(this, other);
    }
}

class Cl1 {
    public String name;
    public  int age;

    public Cl1() {}
    public Cl1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return RichObject.toString(this);
    }

    @Override
    public int hashCode() {
        return RichObject.hashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return RichObject.equals(this, other);
    }
}