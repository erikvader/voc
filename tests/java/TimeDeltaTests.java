import org.python.stdlib.datetime.TimeDelta;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TimeDeltaTests {
    @Test
    public void hello2() {
        org.python.Object args[] = {};
        java.util.Map<java.lang.String, org.python.Object> kwargs = new HashMap<>();
        TimeDelta td = new TimeDelta(args, kwargs);
        org.python.types.Int seconds = (org.python.types.Int) td.seconds;
        assertEquals(1, seconds.value);
    }
}
