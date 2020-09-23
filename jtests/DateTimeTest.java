import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.python.stdlib.datetime.DateTime;
import org.python.exceptions.ValueError;
import java.util.Map;
import java.util.HashMap;

public class DateTimeTest {
    @Test
    public void testCreateArgs() {
        org.python.Object[] args = {
            org.python.Int(1),
            org.python.Int(2),
            org.python.Int(3),
        };
        DateTime dt = new DateTime(args);
        assertEquals(dt.year, org.python.Int(1));
        assertEquals(dt.month, org.python.Int(2));
        assertEquals(dt.day, org.python.Int(3));
        assertEquals(dt.hour, org.python.Int(0));
        assertEquals(dt.minute, org.python.Int(0));
        assertEquals(dt.second, org.python.Int(0));
        assertEquals(dt.microsecond, org.python.Int(0));
    }

    @Test
    public void testCreateKwargs() {
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        kwargs.put("year", org.python.Int(1997));
        kwargs.put("month", org.python.Int(12));
        kwargs.put("day", org.python.Int(2));
        kwargs.put("hour", org.python.Int(5));
        kwargs.put("microseconds", org.python.Int(250000));

        DateTime dt = new DateTime(kwargs);
        assertEquals(dt.year, org.python.Int(1997));
        assertEquals(dt.month, org.python.Int(12));
        assertEquals(dt.day, org.python.Int(2));
        assertEquals(dt.hour, org.python.Int(5));
        assertEquals(dt.minute, org.python.Int(0));
        assertEquals(dt.second, org.python.Int(0));
        assertEquals(dt.microsecond, org.python.Int(250000));
    }

    @Test(expected = ValueError.class)
    public void testCreateKwargsTooFew() {
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        kwargs.put("year", org.python.Int(1997));
        DateTime dt = new DateTime(kwargs);
    }

    @Test(expected = ValueError.class)
    public void testCreateKwargsTooFew2() {
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        DateTime dt = new DateTime(kwargs);
    }

    @Test(expected = ValueError.class)
    public void testCreateArgsTooFew() {
        org.python.Object[] args = {
            org.python.Int(1),
            org.python.Int(2),
        };
        DateTime dt = new DateTime(args);
    }

    @Test(expected = ValueError.class)
    public void testCreateArgsTooFew2() {
        org.python.Object[] args = {};
        DateTime dt = new DateTime(args);
    }

    @Test()
    public void testCreateKwargsOutOfRange() {
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        kwargs.put("year", org.python.Int(1997));
        kwargs.put("month", org.python.Int(12));
        kwargs.put("day", org.python.Int(1));
        kwargs.put("hour", org.python.Int(1));
        kwargs.put("minute", org.python.Int(1));
        kwargs.put("second", org.python.Int(1));
        kwargs.put("microsecond", org.python.Int(200000));

        Map faultykwargs = new HashMap<java.lang.String, org.python.Object>();
        faultykwargs.put("year", org.python.Int(0));
        faultykwargs.put("month", org.python.Int(0));
        faultykwargs.put("day", org.python.Int(0));
        faultykwargs.put("hour", org.python.Int(-1));
        faultykwargs.put("minute", org.python.Int(-1));
        faultykwargs.put("second", org.python.Int(-1));
        faultykwargs.put("microsecond", org.python.Int(-1));

        for (Map.Entry<_,_> entry : faultykwargs.entrySet()) {
            org.python.Object old = kwargs.get(entry.getKey());
            kwargs.put(entry.getKey(), entry.getValue());
            bool threw = false;
            try {
                DateTime dt = new DateTime(kwargs);
            } catch (org.python.exceptions.ValueError e) {
                //TODO: check exception message
                threw = true;
            }
            assertTrue(threw);
            kwargs.put(entry.getKey(), old);
        }

        faultykwargs.put("year", org.python.Int(10000));
        faultykwargs.put("month", org.python.Int(13));
        faultykwargs.put("day", org.python.Int(32));
        faultykwargs.put("hour", org.python.Int(25));
        faultykwargs.put("minute", org.python.Int(61));
        faultykwargs.put("second", org.python.Int(61));
        faultykwargs.put("microsecond", org.python.Int(1000001));

        //TODO: DRY
        for (Map.Entry<_,_> entry : faultykwargs.entrySet()) {
            org.python.Object old = kwargs.get(entry.getKey());
            kwargs.put(entry.getKey(), entry.getValue());
            bool threw = false;
            try {
                DateTime dt = new DateTime(kwargs);
            } catch (org.python.exceptions.ValueError e) {
                //TODO: check exception message
                threw = true;
            }
            assertTrue(threw);
            kwargs.put(entry.getKey(), old);
        }
    }
}
