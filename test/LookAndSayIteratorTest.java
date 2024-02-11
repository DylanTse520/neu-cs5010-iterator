import lookandsay.LookAndSayIterator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class LookAndSayIteratorTest {
    private LookAndSayIterator lookAndSayIteratorOne;
    private LookAndSayIterator lookAndSayIteratorOneOneTwoThreeTwoOne;
    private LookAndSayIterator lookAndSayIteratorNoNext;

    @Before
    public void setUp() {
        lookAndSayIteratorOne = new LookAndSayIterator();
        lookAndSayIteratorOneOneTwoThreeTwoOne = new LookAndSayIterator(new BigInteger("112321"));
        lookAndSayIteratorNoNext = new LookAndSayIterator(new BigInteger("112321"), new BigInteger("21121312"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownWithNonPositiveSeed() {
        LookAndSayIterator nonPositiveSeed = new LookAndSayIterator(new BigInteger("-112321"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownWithSmallEndValue() {
        LookAndSayIterator smallEndValue = new LookAndSayIterator(new BigInteger("112321"), new BigInteger("112321"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownWithZero() {
        LookAndSayIterator withZero = new LookAndSayIterator(new BigInteger("112021"));
    }

    @Test
    public void testHasPrevious() {
        assertTrue(lookAndSayIteratorOneOneTwoThreeTwoOne.hasPrevious());
        assertFalse(lookAndSayIteratorOne.hasPrevious());
    }

    @Test
    public void testHasNext() {
        assertTrue(lookAndSayIteratorOneOneTwoThreeTwoOne.hasNext());
        assertTrue(lookAndSayIteratorOne.hasNext());
        lookAndSayIteratorNoNext.next();
        assertFalse(lookAndSayIteratorNoNext.hasNext());
    }

    @Test
    public void testNextAndPrev() {
        assertEquals(new BigInteger("1"), lookAndSayIteratorOne.next());
        assertEquals(new BigInteger("11"), lookAndSayIteratorOne.next());
        assertEquals(new BigInteger("21"), lookAndSayIteratorOne.next());
        assertEquals(new BigInteger("1211"), lookAndSayIteratorOne.next());
        assertEquals(new BigInteger("111221"), lookAndSayIteratorOne.prev());
        assertEquals(new BigInteger("1211"), lookAndSayIteratorOne.prev());
        assertEquals(new BigInteger("112321"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("2112131211"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("1221121113111221"), lookAndSayIteratorOneOneTwoThreeTwoOne.prev());
        assertEquals(new BigInteger("2112131211"), lookAndSayIteratorOneOneTwoThreeTwoOne.prev());
    }

    @Test
    public void testNextOutBound() {
        assertEquals(new BigInteger("112321"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("2112131211"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("1221121113111221"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("112221123113312211"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("21322112132123112221"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("121113222112111312111213213211"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("1112311332211231131112311211131211131221"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("31121321232221121321133112132112311311123113112211"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("1321121113121112133221121113122123211211131221121321133112132113212221"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertEquals(new BigInteger("111312211231131112311211232221123113112211121312211231131122211211131221232112111312211312113211"), lookAndSayIteratorOneOneTwoThreeTwoOne.next());
        assertFalse(lookAndSayIteratorOneOneTwoThreeTwoOne.hasNext());
    }

}
