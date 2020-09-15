package python;

@org.python.Module(
    __doc__ =
        "This module provides various functions to manipulate time values.\n" +
            "\n" +
            "There are two standard representations of time.  One is the number\n" +
            "of seconds since the Epoch, in UTC (a.k.a. GMT).  It may be an integer\n" +
            "or a floating point number (to represent fractions of seconds).\n" +
            "The Epoch is system-defined; on Unix, it is generally January 1st, 1970.\n" +
            "The actual value can be retrieved by calling gmtime(0).\n" +
            "\n" +
            "Functions:\n" +
            "\n" +
            "time() -- return current time in seconds since the Epoch as a float\n"
)
public class math extends org.python.types.Module {
    public math() {
        super();
    }

    @org.python.Method(
        __doc__ = "sqrt(x)\n" +
            "\n" +
            "Delay execution for a given number of seconds.  The argument may be\n" +
            "a floating point number for subsecond precision.\n",
        args = {"x"}
    )
    public static org.python.Object sqrt(org.python.Object x) {
        double value;
        try {
            value = ((org.python.types.Float) x).value;
        } catch (ClassCastException ignored) {
            try {
                value = ((org.python.types.Int) x).value;
            } catch (ClassCastException e) {
                throw new org.python.exceptions.TypeError("TypeError: must be real number, not " + x.typeName());
            }
        }

        if (value < 0) {
            throw new org.python.exceptions.ValueError("math domain error");
        }
        double result = java.lang.Math.sqrt(value);
        return new org.python.types.Float(result);
    }
}
