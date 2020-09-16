package python;

@org.python.Module(
    __doc__ = "This module provides various functions to do math.\n" +
              "fabs(x) -- return absolute value of x"
)
public class math extends org.python.types.Module {

    @org.python.Attribute()
    public static org.python.Object __file__ = new org.python.types.Str("python/common/python/math.java");
    @org.python.Attribute()
    public static org.python.Object __name__ = new org.python.types.Str("math");
    @org.python.Attribute()
    public static org.python.Object __package__ = new org.python.types.Str("");

    @org.python.Method(
        __doc__ = "Returns the absolute value of the float x",
        args = {"x"}
    )
    public static org.python.Object fabs(org.python.Object x) {
        org.python.Object f;
        try {
            f = x.__float__();
        } catch (org.python.exceptions.AttributeError e) {
            throw new org.python.exceptions.TypeError("must be a real number, not " + x.typeName());
        }

        if (!(f instanceof org.python.types.Float)) {
            throw new org.python.exceptions.TypeError(
                x.typeName() + ".__float__ returned a non-float (type " + f.typeName() + ")"
            );
        }

        double abs = java.lang.Math.abs(((org.python.types.Float) f).value);
        return new org.python.types.Float(abs);
    }
}
