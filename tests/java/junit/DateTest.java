import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.python.stdlib.datetime.Date;
import org.python.exceptions.TypeError;
import org.python.exceptions.ValueError;
import org.python.types.Int;
import java.util.Map;
import java.util.TimeZone;
import java.util.HashMap;
import java.util.Calendar; //Since date.getYear() is deprecated
import java.util.Collections;

public class DateTest {
    @Test
    public void testCreateDate() {
        org.python.Object[] args = { Int.getInt(2020), // year
                Int.getInt(10), // month
                Int.getInt(24), // day
        };
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        Date d = new Date(args, kwargs);
        assertEquals(d.year, args[0]);
        assertEquals(d.month, args[1]);
        assertEquals(d.day, args[2]);
    }


    @Test(expected = TypeError.class)
    public void testMissingDay() {
        org.python.Object[] args = { 
            Int.getInt(2020), // year
            Int.getInt(10), // month
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
    }

    @Test(expected = TypeError.class)
    public void testMissingMonth() {
        org.python.Object[] args = {
            Int.getInt(2020), // year
            Int.getInt(24), // day
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
    }

    @Test(expected = TypeError.class)
    public void testMissingYear() {
        org.python.Object[] args = {
            Int.getInt(10), // month
            Int.getInt(24), // day
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
    }

    @Test(expected = ValueError.class)
    public void testMonthOutOfRange() {
        org.python.Object[] args = {
            Int.getInt(2020),
            Int.getInt(13), // month
            Int.getInt(24), // day
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
    }

    @Test(expected = ValueError.class)
    public void testMonthZero() {
        org.python.Object[] args = {
            Int.getInt(2020),
            Int.getInt(0), // month
            Int.getInt(24), // day
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
    }

    @Test(expected = ValueError.class)
    public void testDayOutOfRange() {
        org.python.Object[] args = {
            Int.getInt(2020),
            Int.getInt(6), // month
            Int.getInt(32), // day
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
    }

    @Test(expected = ValueError.class)
    public void testDayZero() {
        org.python.Object[] args = {
            Int.getInt(2020),
            Int.getInt(6), // month
            Int.getInt(0), // day
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
    }

    @Test
    public void testKwargs() {
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        kwargs.put("year", Int.getInt(2020));
        kwargs.put("month", Int.getInt(10));
        kwargs.put("day", Int.getInt(24));
        org.python.Object[] args = {};
        Date d = new Date(args, kwargs);
        assertEquals(d.year, kwargs.get("year"));
        assertEquals(d.month, kwargs.get("month"));
        assertEquals(d.day, kwargs.get("day"));
    }
    // Todo, test too many and too few arguments

    @Test
    public void testToday() {
        Date today = (Date) Date.today();
        java.util.Date javaToday = new java.util.Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Stockholm"));
        cal.setTime(javaToday);
        assertEquals(today.year, Int.getInt(cal.get(Calendar.YEAR)));
        assertEquals(today.month, Int.getInt(cal.get(Calendar.MONTH) + 1)); //Cal month starts at 0
        assertEquals(today.day, Int.getInt(cal.get(Calendar.DAY_OF_MONTH)));
    }
}
