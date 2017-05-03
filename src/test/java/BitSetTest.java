import org.junit.Test;
import ut.algos.rb.BitSet;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class BitSetTest {
    @Test
    public void testAdd() {
        BitSet b = new BitSet(100);
        for (short i = 0; i < 100; i++) {
            assertFalse(b.contains(i));
            b.add(i);
            assertTrue(b.contains(i));
        }
    }

    @Test
    public void testRemove() {
        BitSet b = new BitSet(100);
        for (short i = 0; i < 100; i++) {
            assertFalse(b.contains(i));
            b.add(i);
            assertTrue(b.contains(i));
        }

        for (short i = 0; i < 100; i++) {
            assertTrue(b.contains(i));
            b.remove(i);
            assertFalse(b.contains(i));
        }
        assertEquals(0, b.getCardinality());
    }

    @Test
    public void testCardinality() {
        BitSet b = new BitSet(100);
        assertEquals(b.getCardinality(), 0);
        for (short i = 0; i < 100; i++) {
            if (i % 2 == 0)
                b.add(i);
        }

        // Only pairs
        assertEquals(b.getCardinality(), 50);
    }

    @Test
    public void testAnd() {
        BitSet b1 = new BitSet(100);
        BitSet b2 = new BitSet(100);

        for (short i = 0; i < 100; i++) {
            if (i % 2 == 0)
                b1.add(i);
            else
                b2.add(i);
        }

        assertEquals(b1.getCardinality(), b2.getCardinality());
        assertEquals(50, b1.getCardinality());
        b1.and(b2);
        assertEquals(0, b1.getCardinality());
        assertEquals(50, b2.getCardinality());
    }


    @Test
    public void testOr() {
        BitSet b1 = new BitSet(100);
        BitSet b2 = new BitSet(100);

        for (short i = 0; i < 100; i++) {
            if (i % 2 == 0)
                b1.add(i);
            else
                b2.add(i);
        }

        assertEquals(b1.getCardinality(), b2.getCardinality());
        assertEquals(50, b1.getCardinality());
        b1.or(b2);
        assertEquals(100, b1.getCardinality());
        assertEquals(50, b2.getCardinality());
    }
}
