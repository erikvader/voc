import static org.junit.Assert.*;
import org.junit.Test;
import org.python.stdlib.datetime.Date;
import org.python.exceptions.TypeError;
import org.python.exceptions.ValueError;
import org.python.types.*;
import java.util.Map;
import java.util.TimeZone;
import java.util.HashMap;
import java.util.Calendar; //Since date.getYear() is deprecated
import java.util.Collections;

public class DateTest {
    @Test
    public void testCreateDate() {
        org.python.Object[] args = { 
                Int.getInt(2020), // year
                Int.getInt(10), // month
                Int.getInt(24), // day
        };
        Date d = new Date(args, Collections.EMPTY_MAP);
        assertEquals(d.year, args[0]);
        assertEquals(d.month, args[1]);
        assertEquals(d.day, args[2]);
    }

    @Test
    public void testWeekday() {
        org.python.Object[] args = { 
            Int.getInt(2020), // year
            Int.getInt(10), // month
            Int.getInt(24), // day, 24 october is an saturday
        };
        Date d = new Date(args, Collections.EMPTY_MAP);

        assertEquals(d.weekday(), Int.getInt(5));
    }

    @Test
    public void testISOWeekday() {
        org.python.Object[] args = { 
            Int.getInt(2020), // year
            Int.getInt(10), // month
            Int.getInt(24), // day, 24 october is an saturday
        };
        Date d = new Date(args, Collections.EMPTY_MAP);

        assertEquals(d.isoweekday(), Int.getInt(6));
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

    @Test 
    public void testCompare() {
        org.python.Object[] date1_args = { 
            Int.getInt(2020), // year
            Int.getInt(10), // month
            Int.getInt(24), // day
        };
        Date d1 = new Date(date1_args, Collections.EMPTY_MAP);

        org.python.Object[] date2_args = { 
            Int.getInt(2021), // year
            Int.getInt(10), // month
            Int.getInt(24), // day
        };
        Date d2 = new Date(date2_args, Collections.EMPTY_MAP);
        assertEquals(d1.before(d2), Bool.TRUE);
        assertEquals(d1.after(d2), Bool.FALSE);

        d2.year = Int.getInt(2020);
        d1.month = Int.getInt(11);
        assertEquals(d1.before(d2), Bool.FALSE);
        assertEquals(d1.after(d2), Bool.TRUE);
    }

    @Test
    public void testFromISO() {
        Date d = Date.fromisoformat(new Str("2020-04-06"));
        assertEquals(d.year, Int.getInt(2020));
        assertEquals(d.month, Int.getInt(04));
        assertEquals(d.day, Int.getInt(06));

    }
}
