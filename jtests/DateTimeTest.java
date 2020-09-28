import static org.junit.Assert.*;
import org.junit.Test;
import org.python.stdlib.datetime.DateTime;
import org.python.exceptions.ValueError;
import org.python.exceptions.TypeError;
import org.python.types.Int;

import python.datetime;

import java.util.Map;
import java.util.HashMap;

public class DateTimeTest {
    @Test
    public void testCreateArgs() {
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        org.python.Object[] args = {
            Int.getInt(1),
            Int.getInt(2),
            Int.getInt(3),
        };
        DateTime dt = new DateTime(args, kwargs);
        assertEquals(dt.year, Int.getInt(1));
        assertEquals(dt.month, Int.getInt(2));
        assertEquals(dt.day, Int.getInt(3));
        assertEquals(dt.hour, Int.getInt(0));
        assertEquals(dt.minute, Int.getInt(0));
        assertEquals(dt.second, Int.getInt(0));
        assertEquals(dt.microsecond, Int.getInt(0));
    }

    @Test
    public void testCreateKwargs() {
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        org.python.Object[] args = {};
        kwargs.put("year", Int.getInt(1997));
        kwargs.put("month", Int.getInt(12));
        kwargs.put("day", Int.getInt(2));
        kwargs.put("hour", Int.getInt(5));
        kwargs.put("microsecond", Int.getInt(250000));

        DateTime dt = new DateTime(args, kwargs);
        assertEquals(dt.year, Int.getInt(1997));
        assertEquals(dt.month, Int.getInt(12));
        assertEquals(dt.day, Int.getInt(2));
        assertEquals(dt.hour, Int.getInt(5));
        assertEquals(dt.minute, Int.getInt(0));
        assertEquals(dt.second, Int.getInt(0));
        assertEquals(dt.microsecond, Int.getInt(250000));
    }

    @Test(expected = TypeError.class)
    public void testCreateKwargsTooFew() {
        org.python.Object[] args = {};
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        kwargs.put("year", Int.getInt(1997));
        DateTime dt = new DateTime(args, kwargs);
    }

    @Test(expected = TypeError.class)
    public void testCreateKwargsTooFew2() {
        org.python.Object[] args = {};
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        DateTime dt = new DateTime(args, kwargs);
    }

    @Test(expected = TypeError.class)
    public void testCreateArgsTooFew() {
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        org.python.Object[] args = {
            Int.getInt(1),
            Int.getInt(2),
        };
        DateTime dt = new DateTime(args, kwargs);
    }

    @Test(expected = TypeError.class)
    public void testCreateArgsTooFew2() {
        org.python.Object[] args = {};
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        DateTime dt = new DateTime(args, kwargs);
    }

    @Test()
    public void testCreateKwargsOutOfRange() {
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        org.python.Object[] args = {};
        kwargs.put("year", Int.getInt(1997));
        kwargs.put("month", Int.getInt(12));
        kwargs.put("day", Int.getInt(1));
        kwargs.put("hour", Int.getInt(1));
        kwargs.put("minute", Int.getInt(1));
        kwargs.put("second", Int.getInt(1));
        kwargs.put("microsecond", Int.getInt(200000));

        Map<String, org.python.Object> faultykwargs = new HashMap<String, org.python.Object>();
        faultykwargs.put("year", Int.getInt(0));
        faultykwargs.put("month", Int.getInt(0));
        faultykwargs.put("day", Int.getInt(0));
        faultykwargs.put("hour", Int.getInt(-1));
        faultykwargs.put("minute", Int.getInt(-1));
        faultykwargs.put("second", Int.getInt(-1));
        faultykwargs.put("microsecond", Int.getInt(-1));

        for (Map.Entry<String,org.python.Object> entry : faultykwargs.entrySet()) {
            org.python.Object old = kwargs.get(entry.getKey());
            kwargs.put(entry.getKey(), entry.getValue());
            boolean threw = false;
            try {
                DateTime dt = new DateTime(args, kwargs);
            } catch (ValueError e) {
                //TODO: check exception message
                threw = true;
            }
            assertTrue(threw);
            kwargs.put(entry.getKey(), old);
        }

        faultykwargs.put("year", Int.getInt(10000));
        faultykwargs.put("month", Int.getInt(13));
        faultykwargs.put("day", Int.getInt(32));
        faultykwargs.put("hour", Int.getInt(25));
        faultykwargs.put("minute", Int.getInt(61));
        faultykwargs.put("second", Int.getInt(61));
        faultykwargs.put("microsecond", Int.getInt(1000001));

        //TODO: DRY
        for (Map.Entry<String,org.python.Object> entry : faultykwargs.entrySet()) {
            org.python.Object old = kwargs.get(entry.getKey());
            kwargs.put(entry.getKey(), entry.getValue());
            boolean threw = false;
            try {
                DateTime dt = new DateTime(args, kwargs);
            } catch (ValueError e) {
                //TODO: check exception message
                threw = true;
            }
            assertTrue(threw);
            kwargs.put(entry.getKey(), old);
        }
    }

    @Test()
    public void testComparison() {
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        org.python.Object[] args = {Int.getInt(1), Int.getInt(1), Int.getInt(1)};
        DateTime dt1 = new DateTime(args, kwargs);

        assertTrue(dt1.__eq__(dt1).toBoolean());
        assertFalse(dt1.__ne__(dt1).toBoolean());
        assertTrue(dt1.__le__(dt1).toBoolean());
        assertFalse(dt1.__lt__(dt1).toBoolean());
        assertTrue(dt1.__ge__(dt1).toBoolean());
        assertFalse(dt1.__gt__(dt1).toBoolean());

        org.python.Object[] args2 = {Int.getInt(1), Int.getInt(2), Int.getInt(1)};
        DateTime dt2 = new DateTime(args2, kwargs);

        assertFalse(dt1.__eq__(dt2).toBoolean());
        assertTrue(dt1.__ne__(dt2).toBoolean());
        assertTrue(dt1.__le__(dt2).toBoolean());
        assertTrue(dt1.__lt__(dt2).toBoolean());
        assertFalse(dt1.__ge__(dt2).toBoolean());
        assertFalse(dt1.__gt__(dt2).toBoolean());

        org.python.Object[] args3 = {Int.getInt(4), Int.getInt(2), Int.getInt(5)};
        DateTime dt3 = new DateTime(args3, kwargs);

        assertFalse(dt3.__eq__(dt1).toBoolean());
        assertTrue(dt3.__ne__(dt1).toBoolean());
        assertFalse(dt3.__le__(dt1).toBoolean());
        assertFalse(dt3.__lt__(dt1).toBoolean());
        assertTrue(dt3.__ge__(dt1).toBoolean());
        assertTrue(dt3.__gt__(dt1).toBoolean());
    }

    @Test
    public void testTimestamp() {
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        org.python.Object[] args = {
            Int.getInt(1970),
            Int.getInt(1),
            Int.getInt(1),
            Int.getInt(1),
            Int.getInt(1),
            Int.getInt(1),
            Int.getInt(0),
        };
        DateTime dt1 = new DateTime(args, kwargs);
        double ts = ((org.python.types.Float) dt1.timestamp()).value;

        //TODO: This test will be incorrect if run in another timezone
        assertEquals((long)ts, 61);
    }

    @Test
    public void testFromIsoString() {
        DateTime dt = (DateTime) DateTime.fromisoformat("1970-01-02T03:04:05.000010");

        assertEquals(dt.year, Int.getInt(1970));
        assertEquals(dt.month, Int.getInt(1));
        assertEquals(dt.day, Int.getInt(2));
        assertEquals(dt.hour, Int.getInt(3));
        assertEquals(dt.minute, Int.getInt(4));
        assertEquals(dt.second, Int.getInt(5));
        assertEquals(dt.microsecond, Int.getInt(10));
    }

    @Test
    public void testWeekday() {
        Map<String, org.python.Object> kwargs = new HashMap<String, org.python.Object>();
        org.python.Object[] args = {
            Int.getInt(2020),
            Int.getInt(9),
            Int.getInt(28),
        };
        DateTime dt = new DateTime(args, kwargs);
        assertEquals(dt.weekday(),Int.getInt(0));
    }
}
