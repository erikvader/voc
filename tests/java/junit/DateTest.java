import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.python.stdlib.datetime.Date;
import org.python.exceptions.ValueError;
import org.python.types.Int;
import java.util.Map;
import java.util.TimeZone;
import java.util.HashMap;
import java.util.Calendar; //Since date.getYear() is deprecated

public class DateTest {
    @Test
    public void testCreateDate() {
        org.python.Object[] args = { org.python.types.Int.getInt(2020), // year
                org.python.types.Int.getInt(10), // month
                org.python.types.Int.getInt(24), // day
        };
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        Date d = new Date(args, kwargs);
        assertEquals(d.year, args[0]);
        assertEquals(d.month, args[1]);
        assertEquals(d.day, args[2]);
    }

    @Test
    public void testKwargs() {
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        kwargs.put("year", org.python.types.Int.getInt(2020));
        kwargs.put("month", org.python.types.Int.getInt(10));
        kwargs.put("year", org.python.types.Int.getInt(24));
        org.python.Object[] args = {};
        Date d = new Date(args, kwargs);
        assertEquals(d.year, kwargs.get("year"));
        assertEquals(d.month, kwargs.get("month"));
        assertEquals(d.day, kwargs.get("day"));
    }

    // Todo, test too many and too few arguments

    @Test
    public void testToday() {
        Map kwargs = new HashMap<java.lang.String, org.python.Object>();
        org.python.Object[] args = {};
        Date today = new Date(args, kwargs);
        java.util.Date javaToday = new java.util.Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Stockholm"));
        cal.setTime(javaToday);
        assertEquals(today.year, cal.get(Calendar.YEAR));
        assertEquals(today.month, cal.get(Calendar.MONTH));
        assertEquals(today.day, cal.get(Calendar.DAY_OF_MONTH));
    }
}
