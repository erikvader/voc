package python;

@org.python.Module(
    __doc__ = "This module provides various functions to do math.\n" +
              "fabs(x) -- return absolute value of x"
)

public class math extends org.python.types.Module {
    public math() {
        super();
    }

    @org.python.Method(
        __doc__ = "Returns the absolute value of the float x",
        args = {"x"}
    )
    public static org.python.Object fabs(org.python.Object x) {
        if (!(x instanceof org.python.types.Float)) {
            throw new org.python.exceptions.TypeError("an float is required (got type " + x.typeName() +")");
        }

        double abs = ((org.python.types.Float) x).value;
        if (abs < 0) {
            abs *= -1;
        }
        return new org.python.types.Float(abs);
    }
}
