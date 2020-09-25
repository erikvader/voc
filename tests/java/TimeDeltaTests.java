import org.python.Object;
import org.python.exceptions.TypeError;
import org.python.exceptions.ValueError;
import org.python.stdlib.datetime.TimeDelta;

import org.junit.Test;
import org.python.types.Float;
import org.python.types.Int;
import org.python.types.List;
import org.python.types.Str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TimeDeltaTests {
    public static void assertDelta(long days, long seconds, long microseconds, TimeDelta delta) {
        assertEquals(days, ((Int) delta.days).value);
        assertEquals(seconds, ((Int) delta.seconds).value);
        assertEquals(microseconds, ((Int) delta.microseconds).value);
    }

    @Test
    public void When_InstantiateWithAllArgs_Expect_ValidTimeDelta() {
        Object[] args = {
            Int.getInt(1),
            Int.getInt(2),
            Int.getInt(3),
            Int.getInt(4),
            Int.getInt(5),
            Int.getInt(6),
            Int.getInt(7),
        };
        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta delta = new TimeDelta(args, kwargs);

        assertDelta(50, 21902, 4003, delta);
    }

    @Test
    public void When_InstantiateWithAllKwargs_Expect_ValidTimeDelta() {
        Object[] args = {};
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("days", Int.getInt(1));
        kwargs.put("seconds", Int.getInt(2));
        kwargs.put("microseconds", Int.getInt(3));
        kwargs.put("milliseconds", Int.getInt(4));
        kwargs.put("minutes", Int.getInt(5));
        kwargs.put("hours", Int.getInt(6));
        kwargs.put("weeks", Int.getInt(7));

        TimeDelta delta = new TimeDelta(args, kwargs);

        assertDelta(50, 21902, 4003, delta);

        assertEquals(new Str("50 days, 6:05:02.004003"), delta.__str__());
    }

    @Test
    public void When_InstantiateWithDayAndOddHours_Expect_ValidTimeDelta() {
        Object[] args = {
            Int.getInt(1),
        };
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("hours", Int.getInt(23));

        TimeDelta delta = new TimeDelta(args, kwargs);

        assertDelta(1, 82800, 0, delta);
    }

    @Test
    public void When_InstantiateWithDayAndEvenHours_Expect_ValidTimeDelta() {
        Object[] args = {
            Int.getInt(1),
        };
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("hours", Int.getInt(24));

        TimeDelta delta = new TimeDelta(args, kwargs);

        assertDelta(2, 0, 0, delta);

        assertEquals(new Str("2 days, 0:00:00"), delta.__str__());
    }

    @Test
    public void When_InstantiateWithDecimalWeeks_Expect_ValidTimeDelta() {
        Object[] args = {};
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("weeks", new Float(1.0 + 1.0 / 7.0));

        TimeDelta delta = new TimeDelta(args, kwargs);

        assertDelta(8, 0, 0, delta);
    }

    @Test(expected = TypeError.class)
    public void When_InstantiateWithTooManyArgs_Expect_Error() {
        Object[] args = {
            Int.getInt(0),
            Int.getInt(0),
            Int.getInt(0),
            Int.getInt(0),
            Int.getInt(0),
            Int.getInt(0),
            Int.getInt(0),
            Int.getInt(0),
        };
        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta delta = new TimeDelta(args, kwargs);
    }

    @Test
    public void When_InstantiateWithInvalidKwarg_Expect_Error() {
        Object[] args = {};
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("abc", Int.getInt(5));

        TypeError error = assertThrows(TypeError.class, () -> new TimeDelta(args, kwargs));
        assertEquals("'abc' is an invalid keyword argument for __new__()", error.getMessage());
    }

    @Test
    public void When_InstantiateWithInvalidType_Expect_Error() {
        Object[] args = {};
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("days", new Str("a"));

        TypeError error = assertThrows(TypeError.class, () -> new TimeDelta(args, kwargs));
        assertEquals("unsupported type for timedelta days component: str", error.getMessage());

        kwargs.clear();
        kwargs.put("weeks", new List());

        error = assertThrows(TypeError.class, () -> new TimeDelta(args, kwargs));
        assertEquals("unsupported type for timedelta weeks component: list", error.getMessage());
    }

    @Test
    public void When_InstantiateWithInvalidArgKwargMix_Expect_Error() {
        TypeError error;
        java.util.List<Int> args = new ArrayList<>();
        Map<String, Object> kwargs = new HashMap<>();

        args.add(Int.getInt(1));
        kwargs.put("days", Int.getInt(2));

        error = assertThrows(TypeError.class, () -> new TimeDelta(args.toArray(new Int[] {}), kwargs));
        assertEquals("argument for __new__() given by name ('days') and position (1)", error.getMessage());

        args.add(Int.getInt(1));
        kwargs.remove("days");
        kwargs.put("seconds", Int.getInt(2));

        error = assertThrows(TypeError.class, () -> new TimeDelta(args.toArray(new Int[] {}), kwargs));
        assertEquals("argument for __new__() given by name ('seconds') and position (2)", error.getMessage());

        args.add(Int.getInt(1));
        kwargs.remove("seconds");
        kwargs.put("microseconds", Int.getInt(2));

        error = assertThrows(TypeError.class, () -> new TimeDelta(args.toArray(new Int[] {}), kwargs));
        assertEquals("argument for __new__() given by name ('microseconds') and position (3)", error.getMessage());

        args.add(Int.getInt(1));
        kwargs.remove("microseconds");
        kwargs.put("milliseconds", Int.getInt(2));

        error = assertThrows(TypeError.class, () -> new TimeDelta(args.toArray(new Int[] {}), kwargs));
        assertEquals("argument for __new__() given by name ('milliseconds') and position (4)", error.getMessage());

        args.add(Int.getInt(1));
        kwargs.remove("milliseconds");
        kwargs.put("minutes", Int.getInt(2));

        error = assertThrows(TypeError.class, () -> new TimeDelta(args.toArray(new Int[] {}), kwargs));
        assertEquals("argument for __new__() given by name ('minutes') and position (5)", error.getMessage());

        args.add(Int.getInt(1));
        kwargs.remove("minutes");
        kwargs.put("hours", Int.getInt(2));

        error = assertThrows(TypeError.class, () -> new TimeDelta(args.toArray(new Int[] {}), kwargs));
        assertEquals("argument for __new__() given by name ('hours') and position (6)", error.getMessage());

        args.add(Int.getInt(1));
        kwargs.remove("hours");
        kwargs.put("weeks", Int.getInt(2));

        error = assertThrows(TypeError.class, () -> new TimeDelta(args.toArray(new Int[] {}), kwargs));
        assertEquals("__new__() takes at most 7 arguments (" + (args.size() + kwargs.size()) + " given)", error.getMessage());
    }

    @Test
    public void Test_multiplyTimeDeltaWithInt_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta TD = new TimeDelta(args, kwargs);
        TimeDelta TDres = TD.__mul__(Int.getInt(2));

        assertDelta(4, 4, 4, TDres);
    }

    @Test(expected = ValueError.class)
    public void Test_MultiplyTimeDeltaWithZero_ExpectError() {
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta TD = new TimeDelta(args, kwargs);
        TimeDelta TDres = TD.__mul__(Int.getInt(0));
    }

    @Test(expected = TypeError.class)
    public void Test_MultiplyTimeDeltaWithString_ExpectError(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta TD = new TimeDelta(args, kwargs);
        TimeDelta TDres = TD.__mul__(new Str("test"));
    }

    @Test
    public void Test_AddTD_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta TD = new TimeDelta(args, kwargs);
        TimeDelta TD2 = new TimeDelta(args, kwargs);

        TimeDelta TDRes = TD.__add__(TD2);

        assertDelta(4,4,4, TDRes);
    }

    @Test
    public void Test_AbsTDPositiveDays_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta TD = new TimeDelta(args, kwargs);

        TimeDelta TDabs = TD.__abs__();

        assertDelta(2,2,2, TDabs);
    }

    @Test
    public void Test_AbsTDNegativeDays_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(-2),
            Int.getInt(-2),
            Int.getInt(-2)
        };

        Map<String, Object> kwargs = new HashMap<>();

        TimeDelta TD = new TimeDelta(args, kwargs);

        TimeDelta TDabs = TD.__abs__();

        assertDelta(2,2,2, TDabs);    
    }
}
