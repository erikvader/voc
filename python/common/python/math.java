package python;

@org.python.Module(
  __doc__ = "A module that supplies some mathematical functions.\n"+
            "Current functions:\n"+
            "pow(x, y) -> Return x raised to the power y."
)

public class math extends org.python.types.Module {
  public math() {
    super();
  }

  @org.python.Method(
    __doc__ = "Returns x raised to the power y.\n",
    args = {"x", "y"}
  )

  public static org.python.Object pow(org.python.Object x, org.python.Object y) {

    if(!(x instanceof org.python.types.Float || y instanceof org.python.types.Float)) {
      throw new org.python.exceptions.TypeError("x and y needs to be of type float");
    }

    double a = ((org.python.types.Float) x).value;
    double b = ((org.python.types.Float) y).value;

    if(a < 0 || b < 0) {
      throw new org.python.exceptions.TypeError("Only values of x >= 0 and y >= 0 are accepted");
    }

    double result = java.lang.Math.pow(a, b);
    return new org.python.types.Float(result);

  }
}
