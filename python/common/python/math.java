package python;

@org.python.Module(
    __doc__ =
        "This module provides access to the mathematical functions defined by the C standard.\n" +
            "\n" +
            "These functions cannot be used with complex numbers; \n" +
            "use the functions of the same name from the cmath module\n" +
            "if you require support for complex numbers. \n" +
            "The distinction between functions which support\n" +
            "complex numbers and those which don’t is made since most users \n" +
            "do not want to learn quite as much mathematics as required to understand complex numbers.\n" +
            "\n" +
            "Read more here https://docs.python.org/3/library/math.html?highlight=math#module-math" +
            "\n" +
            "Functions:\n" +
            "\n" +
            "math(x) -- returns the square root of x\n"
)
public class math extends org.python.types.Module {
    public math() {
        super();
    }

    @org.python.Method(
        __doc__ = "sqrt(x)\n" +
            "\n" +
            "Take the square root of a number.  The argument may be\n" +
            "any real number (int, float, double) bigger or equal to zero.\n",
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
