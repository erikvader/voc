import org.python.Object;
import org.python.exceptions.OverflowError;
import org.python.exceptions.TypeError;
import org.python.exceptions.ValueError;
import org.python.stdlib.datetime.TimeDelta;

import org.junit.Test;
import org.python.types.*;
import org.python.types.Float;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TimeDeltaTests {
    public static TimeDelta createDelta(double days, double seconds, double microseconds, double milliseconds,
                                        double minutes, double hours, double weeks) {
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("days", new Float(days));
        kwargs.put("seconds", new Float(seconds));
        kwargs.put("microseconds", new Float(microseconds));
        kwargs.put("milliseconds", new Float(milliseconds));
        kwargs.put("minutes", new Float(minutes));
        kwargs.put("hours", new Float(hours));
        kwargs.put("weeks", new Float(weeks));
        return new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs);
    }

    public static void assertDelta(long days, long seconds, long microseconds, TimeDelta delta) {
        assertEquals(days, ((Int) delta.days).value);
        assertEquals(seconds, ((Int) delta.seconds).value);
        assertEquals(microseconds, ((Int) delta.microseconds).value);
    }

    public static void assertBool(boolean expected, Object bool) {
        assertEquals(expected, ((Bool) bool).value);
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

        TimeDelta delta = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);

        assertDelta(50, 21902, 4003, delta);
    }

    @Test
    public void When_InstantiateWithAllKwargs_Expect_ValidTimeDelta() {
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("days", Int.getInt(1));
        kwargs.put("seconds", Int.getInt(2));
        kwargs.put("microseconds", Int.getInt(3));
        kwargs.put("milliseconds", Int.getInt(4));
        kwargs.put("minutes", Int.getInt(5));
        kwargs.put("hours", Int.getInt(6));
        kwargs.put("weeks", Int.getInt(7));

        TimeDelta delta = new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs);

        assertDelta(50, 21902, 4003, delta);

        assertEquals(new Str("50 days, 6:05:02.004003"), delta.__str__());
    }

    @Test
    public void When_InstantiateWithDayAndOddHours_Expect_ValidTimeDelta() {
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("days", new Float(0.1111111111));

        TimeDelta delta;
        delta = new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs);
        assertDelta(0, 9599, 999999, delta);

        Object[] args2 = { new Float(0), new Float(0.5) };
        kwargs.clear();
        kwargs.put("microseconds", new Float(500000));

        delta = new TimeDelta(args2, kwargs);
        assertDelta(0, 1, 0, delta);

        kwargs.clear();
        kwargs.put("seconds", new Float(-1));
        delta = new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs);
        assertDelta(-1, 86399, 0, delta);
        assertEquals("-1 day, 23:59:59", delta.__str__().value);
    }

    @Test
    public void When_InstantiateWithDayAndEvenHours_Expect_ValidTimeDelta() {
        Object[] args = { Int.getInt(-1) };
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("hours", Int.getInt(-24));
        kwargs.put("milliseconds", Int.getInt(-1));
        kwargs.put("minutes", Int.getInt(-2));

        TimeDelta delta = new TimeDelta(args, kwargs);

        assertDelta(-3, 86279, 999000, delta);

        assertEquals(new Str("-3 days, 23:57:59.999000"), delta.__str__());
    }

    @Test
    public void When_InstantiateWithDecimalWeeks_Expect_ValidTimeDelta() {
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("weeks", new Float(1.0 + 1.0 / 7.0));

        TimeDelta delta = new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs);

        assertDelta(8, 0, 0, delta);
    }

    @Test
    public void When_InstantiateWithInvalidKwarg_Expect_Error() {
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("abc", Int.getInt(5));

        TypeError error = assertThrows(TypeError.class, () -> new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs));
        assertEquals("'abc' is an invalid keyword argument for __new__()", error.getMessage());
    }

    @Test
    public void When_InstantiateWithInvalidType_Expect_Error() {
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("days", new Str("a"));

        TypeError error = assertThrows(TypeError.class, () -> new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs));
        assertEquals("unsupported type for timedelta days component: str", error.getMessage());

        kwargs.clear();
        kwargs.put("weeks", new List());

        error = assertThrows(TypeError.class, () -> new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs));
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

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);
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

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);
        TimeDelta TDres = TD.__mul__(Int.getInt(0));
    }

    @Test(expected = TypeError.class)
    public void Test_MultiplyTimeDeltaWithString_ExpectError(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);
        TimeDelta TDres = TD.__mul__(new Str("test"));
    }

    @Test
    public void Test_total_seconds_Expect_Correct(){
        TimeDelta a = createDelta(1, 1, 1, 0, 0, 0, 0);
        assertEquals(new Float(86401.000001), a.total_seconds());
    }

    @Test(expected = TypeError.class)
    public void Test_AddTDWithString_ExpectError(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);

        TimeDelta TDRes = TD.__add__(new Str("test"));
    }

    @Test
    public void Test_AddTDWithPositiveValues_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);
        TimeDelta TD2 = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);

        TimeDelta TDRes = TD.__add__(TD2);

        assertDelta(4,4,4, TDRes);
    }

    @Test
    public void Test_AddTDWithNegativeValues_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        Object[] negArgs = {
            Int.getInt(-2),
            Int.getInt(-2),
            Int.getInt(-2)
        };


        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);
        TimeDelta TD2 = new TimeDelta(negArgs, TimeDelta.EMPTY_KWARGS);

        TimeDelta TDRes = TD.__add__(TD2);

        assertDelta(0,0,0, TDRes);
    }

    @Test
    public void Test_PosTD_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);

        TimeDelta TDPos = TD.__pos__();

        assertDelta(2,2,2, TDPos);
    }

    @Test
    public void Test_StrTD_ExpectCorrect(){
        TimeDelta a = createDelta(12, 0, 0, 0, 0, 0, 0);
        TimeDelta b = createDelta(0, 1, 1, 0, 0, 0, 0);
        TimeDelta c = createDelta(1, 0, 0, 0, 0, 0, 0);

        assertEquals(new Str("12 days, 0:00:00"), a.__str__());
        assertEquals(new Str("0:00:01.000001"), b.__str__());
        assertEquals(new Str("1 day, 0:00:00"), c.__str__());
    }

    @Test
    public void Test_AbsTDPositiveDays_Expect_ValidTimeDelta(){
        Object[] args = {
            Int.getInt(2),
            Int.getInt(2),
            Int.getInt(2)
        };

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);

        TimeDelta TDabs = TD.__abs__();

        assertDelta(2,2,2, TDabs);
    }

    @Test
    public void Test_AbsTDNegativeDays_Expect_ValidTimeDelta() {
        Object[] args = {
            Int.getInt(-2),
            Int.getInt(-2),
            Int.getInt(-2)
        };

        TimeDelta TD = new TimeDelta(args, TimeDelta.EMPTY_KWARGS);

        TimeDelta TDabs = TD.__abs__();

        assertDelta(2, 2, 2, TDabs);
    }

    @Test
    public void When_MicrosecondsIsDecimal_Expect_RoundCorrectly() {
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("microseconds", new Float(0.5));

        TimeDelta delta = new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs);
        assertDelta(0, 0, 0, delta);

        kwargs.put("microseconds", new Float(0.51));
        delta = new TimeDelta(TimeDelta.EMPTY_ARGS, kwargs);
        assertDelta(0, 0, 1, delta);
    }

    @Test
    public void When_ComparingDeltas_Expect_Equal() {
        TimeDelta a = createDelta(0, 0, 0, 0, 0, 0, 0);
        assertTrue(a.__eq__(a).toBoolean());

        TimeDelta b = createDelta(0, 0, 0, 0, 0, 0, 0);
        assertTrue(a.__eq__(b).toBoolean());
        assertEquals(b.hashCode(), a.hashCode());

        a = createDelta(0, 0, 0, 0, 0, 1, 0);
        b = createDelta(0, 0, 0, 0, 0, 1, 0);
        assertTrue(a.__eq__(b).toBoolean());

        a = createDelta(1, 0, 0, 0, 0, 1, 0);
        b = createDelta(1, 0, 0, 0, 0, 1, 0);
        assertTrue(a.__eq__(b).toBoolean());

        a = createDelta(1, 0, 0, 0, 0, 24, 0);
        b = createDelta(2, 0, 0, 0, 0, 0, 0);
        assertTrue(a.__eq__(b).toBoolean());
    }

    @Test
    public void When_ComparingDeltas_Expect_NotEqual() {
        TimeDelta a = createDelta(0, 0, 0, 0, 0, 0, 0);
        TimeDelta b = createDelta(0, 0, 0, 0, 0, 0, 0);
        assertFalse(a.__ne__(b).toBoolean());

        a = createDelta(1, 0, 0, 0, 0, 0, 0);
        b = createDelta(0, 0, 0, 0, 0, 0, 0);
        assertTrue(a.__ne__(b).toBoolean());

        a = createDelta(1, 0, 0, 0, 0, 0, 0);
        b = createDelta(1, 1, 0, 0, 0, 0, 0);
        assertTrue(a.__ne__(b).toBoolean());

        a = createDelta(1, 1, 0, 0, 0, 0, 0);
        b = createDelta(1, 1, 1, 0, 0, 0, 0);
        assertTrue(a.__ne__(b).toBoolean());
    }

    @Test
    public void When_ComparingGreater_Expect_CorrectComparison() {
        TimeDelta a = createDelta(1, 0, 0, 0, 0, 0, 0);
        TimeDelta b = createDelta(0, 1, 0, 0, 0, 0, 0);
        assertBool(true, a.__gt__(b));

        a = createDelta(0, 0, 0, 0, 1, 0, 0);
        b = createDelta(0, 0, 0, 0, 0, 0, 1);
        assertBool(true, b.__gt__(a));
        assertBool(true, b.__ge__(a));

        a = createDelta(0, 0, 0, 0, 1, 0, 0);
        b = createDelta(0, 0, 0, 0, 1, 0, 0);
        assertBool(false, a.__gt__(b));
        assertBool(true, a.__ge__(b));

        final TimeDelta c = createDelta(0, 0, 0, 0, 1, 0, 0);
        TypeError error = assertThrows(TypeError.class, () -> c.__gt__(Bool.getBool(false)));
        assertEquals("'>' not supported between instances of 'datetime.timedelta' and 'bool'", error.getMessage());

        error = assertThrows(TypeError.class, () -> c.__ge__(Int.getInt(5)));
        assertEquals("'>=' not supported between instances of 'datetime.timedelta' and 'int'", error.getMessage());
    }

    @Test
    public void When_ComparingLess_Expect_CorrectComparison() {
        TimeDelta a = createDelta(1, 0, 0, 0, 0, 0, 0);
        TimeDelta b = createDelta(0, 0, 0, 0, 0, 0, 0);
        assertBool(true, b.__lt__(a));

        a = createDelta(0, 1, 0, 0, 0, 0, 0);
        b = createDelta(1, 0, 0, 0, 0, 0, 0);
        assertBool(true, a.__lt__(b));
        assertBool(true, a.__le__(b));

        a = createDelta(0, 0, 0, 0, 1, 0, 0);
        b = createDelta(0, 0, 0, 0, 1, 0, 0);
        assertBool(false, b.__lt__(a));
        assertBool(true, b.__le__(a));

        final TimeDelta c = createDelta(0, 0, 0, 0, 1, 0, 0);
        TypeError error = assertThrows(TypeError.class, () -> c.__lt__(Bool.getBool(false)));
        assertEquals("'<' not supported between instances of 'datetime.timedelta' and 'bool'", error.getMessage());

        error = assertThrows(TypeError.class, () -> c.__le__(Int.getInt(5)));
        assertEquals("'<=' not supported between instances of 'datetime.timedelta' and 'int'", error.getMessage());
    }

    @Test
    public void When_ComparingDifferentTypes_Expect_Correct() {
        TimeDelta a = createDelta(1, 0, 0, 0, 0, 0, 0);
        Bool b = Bool.getBool(true);
        Bool c = Bool.getBool(false);
        Int d = Int.getInt(20);

        assertBool(false, a.__eq__(b));
        assertBool(false, a.__eq__(c));
        assertBool(false, a.__eq__(d));
    }

    @Test
    public void When_TestingAllNegativeValues_Expect_CorrectDelta() {
        Object[] args = { new Float(-1.5) };
        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("hours", Int.getInt(-11));
        kwargs.put("seconds", Int.getInt(-1));
        kwargs.put("microseconds", new Float(-20.5));

        TimeDelta delta = new TimeDelta(args, kwargs);
        assertDelta(-2, 3598, 999980, delta);

        kwargs.put("microseconds", new Float(-20.6));
        delta = new TimeDelta(args, kwargs);
        assertDelta(-2, 3598, 999979, delta);
    }

    @Test
    public void When_UsingTooLargeInputs_Expect_Exception() {
        OverflowError error = assertThrows(
            OverflowError.class,
            () -> createDelta(1000000000, 0, 0, 0, 0, 0, 0)
        );
        assertEquals("days=1000000000; must have magnitude <= 999999999", error.getMessage());

        error = assertThrows(
            OverflowError.class,
            () -> createDelta(-1000000000, 0, 0, 0, 0, 0, 0)
        );
        assertEquals("days=-1000000000; must have magnitude <= 999999999", error.getMessage());
    }

    @Test
    public void Test_daysTD_ExpectCorrect(){
        TimeDelta a = createDelta(1, 0, 0, 0, 0, 0, 0);
        assertEquals(Int.getInt(1), a.__days__());
    }

    @Test
    public void Test_secondsTD_ExpectCorrect(){
        TimeDelta a = createDelta(0, 1, 0, 0, 0, 0, 0);
        assertEquals(Int.getInt(1), a.__seconds__());
    }

    @Test
    public void Test_microsecondsTD_ExpectCorrect(){
        TimeDelta a = createDelta(0, 0, 1, 0, 0, 0, 0);
        assertEquals(Int.getInt(1), a.__microseconds__());
    }

    @Test
    public void Test_minTD_Expect_ValidTimeDelta(){
        assertDelta(-999999999, 0, 0, TimeDelta.__min__());
    }

    @Test
    public void Test_maxTD_Expect_ValidTimeDelta(){
        assertDelta(999999999, 86399, 999999, TimeDelta.__max__());
    }

    @Test
    public void Test_resolutionTD_Expect_ValidTimeDelta(){
        assertDelta(0, 0, 1, TimeDelta.__resolution__());
    }

    @Test
    public void Test_modTDDays_Expect_ValidTimeDelta(){
        TimeDelta a = createDelta(12, 0, 0, 0, 0, 0, 0);
        TimeDelta b = createDelta(10, 0, 0, 0, 0, 0, 0);

        assertDelta(2, 0, 0, a.__mod__(b));
    }

    @Test
    public void Test_modTDSeconds_Expect_ValidTimeDelta(){
        TimeDelta a = createDelta(0, 12, 0, 0, 0, 0, 0);
        TimeDelta b = createDelta(0, 10, 0, 0, 0, 0, 0);

        assertDelta(0, 2, 0, a.__mod__(b));
    }

    @Test
    public void Test_modTDMicroseconds_Expect_ValidTimeDelta(){
        TimeDelta a = createDelta(0, 0, 12, 0, 0, 0, 0);
        TimeDelta b = createDelta(0, 0, 10, 0, 0, 0, 0);

        assertDelta(0, 0, 2, a.__mod__(b));
    }

    @Test(expected = TypeError.class)
    public void Test_modTDWithString_ExpectError(){
        TimeDelta a = createDelta(12, 0, 0, 0, 0, 0, 0);

        assertDelta(2, 0, 0, a.__mod__(new Str("test")));
    }
}
