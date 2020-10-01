package org.python.stdlib.datetime;

import java.util.Collections;

public class Date extends org.python.types.Object {

    @org.python.Attribute
    public org.python.Object year = __year__();

    @org.python.Attribute
    public org.python.Object month = __month__();

    @org.python.Attribute
    public org.python.Object day = __day__();

    @org.python.Attribute
    public static final org.python.Object min = __min__();

    @org.python.Attribute
	public static final org.python.Object max = __max__();
	
	private int MIN_YEAR = 1;
	private int MAX_YEAR = 9999;
	private int MIN_MONTH = 1;
	private int MAX_MONTH = 12;
	private int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	@org.python.Method(__doc__ = 
	"A date object represents a date (year, month and day) in an idealized calendar, \n"
	+ "the current Gregorian calendar indefinitely extended in both directions.")
    public Date(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {

	super();

	if (args.length + kwargs.size() > 3) {
	    int val = args.length + kwargs.size();
	    throw new org.python.exceptions.TypeError("function takes at most 3 arguments (" + val + " given)");
	}

	if (args.length + kwargs.size() == 3) {
	    if (kwargs.get("year") != null && args.length == 2) {
		throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
	    } else if (kwargs.get("month") != null && args.length == 2) {
		throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
	    }
	    if (kwargs.get("year") != null) {
		this.year = kwargs.get("year");
	    } else {
		this.year = args[0];
	    }

	    if (kwargs.get("month") != null) {
		this.month = kwargs.get("month");
	    } else {
		this.month = args[1];
	    }

	    if (kwargs.get("day") != null) {
		this.day = kwargs.get("day");
	    } else {
		this.day = args[2];
		}


		if (!(this.year instanceof org.python.types.Int)) {
		    throw new org.python.exceptions.TypeError("integer argument expected, got " + this.year.typeName());
		}
		if (!(this.month instanceof org.python.types.Int)) {
		    throw new org.python.exceptions.TypeError("integer argument expected, got " + this.month.typeName());
		}
		if (!(this.day instanceof org.python.types.Int)) {
		    throw new org.python.exceptions.TypeError("integer argument expected, got " + this.day.typeName());
		}
		
		int year = (int) ((org.python.types.Int) this.year).value;
		int month = (int) ((org.python.types.Int) this.month).value;
		int day = (int) ((org.python.types.Int) this.day).value;

		if ( year < MIN_YEAR || year > MAX_YEAR) {
			throw new org.python.exceptions.ValueError("year " + this.year + " is out of range");
		}
		
		if ( month < MIN_MONTH || month > MAX_MONTH) {
			throw new org.python.exceptions.ValueError("month must be in 1..12");
		}

		if ( day < 1 || day > DAYS_IN_MONTH[month-1]) {
			throw new org.python.exceptions.ValueError("day is out of range for month: " + this.day);
		}
	}

	if (args.length + kwargs.size() == 2) {

	    if (args.length == 2) {
		this.year = args[0];
		this.month = args[1];
	    }

	    if (kwargs.get("year") != null) {
		this.year = kwargs.get("year");
	    } else if (args.length > 0) {
		this.year = args[0];
	    }

	    if (kwargs.get("month") != null) {
		this.month = kwargs.get("month");
	    }
	    if (kwargs.get("day") != null) {
		this.day = kwargs.get("day");
	    }

	    String y = this.year + "";
	    String m = this.month + "";
	    String d = this.day + "";

	    if (!y.equals("null") && !(this.year instanceof org.python.types.Int)) {
		throw new org.python.exceptions.TypeError("intege argument expected, got " + this.year.typeName());
	    }
	    if (kwargs.get("year") != null && args.length > 0) {
		throw new org.python.exceptions.SyntaxError("positional argument follows keyword argument");
	    }

	    if (!(this.month instanceof org.python.types.Int) && !m.equals("null")) {
		throw new org.python.exceptions.TypeError("integer argument expected, got " + this.month.typeName());
	    }

	    if (y.equals("null")) {

		throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
	    }

	    if (m.equals("null")) {

		throw new org.python.exceptions.TypeError("function missing required argument 'month' (pos 2)");
	    }
	    if (d.equals("null")) {
		throw new org.python.exceptions.TypeError("function missing required argument 'day' (pos 3)");
	    }
	}

	if (args.length + kwargs.size() == 1) {
	    if (kwargs.get("year") != null) {
		this.year = kwargs.get("year");
	    } else if (args.length > 0) {
		this.year = args[0];
	    }
	    if (kwargs.get("month") != null) {
		this.month = kwargs.get("month");
	    }

	    if (kwargs.get("day") != null) {
		this.day = kwargs.get("day");
	    }

	    String y = this.year + "";
	    String m = this.month + "";
	    String d = this.day + "";

	    if (!(this.year instanceof org.python.types.Int) && !y.equals("null")) {
		throw new org.python.exceptions.TypeError("integer argument expected, got " + this.year.typeName());

	    }
	    if (!y.equals("null")) {
		throw new org.python.exceptions.TypeError("function missing required argument 'month' (pos 2)");
	    }
	    if (!m.equals("null") || !d.equals("null")) {
		throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
	    }

	}
	if (args.length + kwargs.size() == 0) {
	    throw new org.python.exceptions.TypeError("function missing required argument 'year' (pos 1)");
	}

    }

    @org.python.Method(__doc__ = "Return datestring")
    public org.python.types.Str __repr__() {

	String year = this.year + "";
	while (year.length() < 4)
	    year = "0" + year;

	String month = this.month + "";
	while (month.length() < 2)
	    month = "0" + month;

	String day = this.day + "";
	while (day.length() < 2)
	    day = "0" + day;

	return new org.python.types.Str(year + "-" + month + "-" + day);
    }

    @org.python.Method(__doc__ = "Return year as string")
    public org.python.types.Str __year__() {
	return new org.python.types.Str(this.year + "");
    }

    @org.python.Method(__doc__ = "Return month as string")
    public org.python.types.Str __month__() {
	return new org.python.types.Str(this.month + "");
    }

    @org.python.Method(__doc__ = "Return day as string")
    public org.python.types.Str __day__() {
	return new org.python.types.Str(this.day + "");
    }

    @org.python.Method(__doc__ = "Return max date")
    private static org.python.Object __max__() {

	org.python.types.Int day = org.python.types.Int.getInt(31);
	org.python.types.Int month = org.python.types.Int.getInt(12);
	org.python.types.Int year = org.python.types.Int.getInt(9999);

	org.python.Object[] args = { year, month, day };
	return new Date(args, Collections.emptyMap());
    }

    @org.python.Method(__doc__ = "Return min date")

    private static org.python.Object __min__() {
	org.python.types.Int day = org.python.types.Int.getInt(1);
	org.python.types.Int month = org.python.types.Int.getInt(1);
	org.python.types.Int year = org.python.types.Int.getInt(1);

	org.python.Object[] args = { year, month, day };
	return new Date(args, Collections.emptyMap());

    }

    @org.python.Method(__doc__ = "Return the current local date.")
    public static org.python.Object today() {
	java.time.LocalDateTime today = java.time.LocalDateTime.now();
	int y = today.getYear();
	int m = today.getMonthValue();
	int d = today.getDayOfMonth();

	org.python.Object[] args = { org.python.types.Int.getInt(y), org.python.types.Int.getInt(m), org.python.types.Int.getInt(d) };
	return new Date(args, Collections.emptyMap());
	}

	@org.python.Method(__doc__ = "Return a date corresponding to a date_string given in the format YYYY-MM-DD")
	public static Date fromisoformat(org.python.types.Str string) {
		String[] str = string.value.split("-");
		int year = Integer.parseInt(str[0]);
		int month = Integer.parseInt(str[1]);
		int day = Integer.parseInt(str[2]);
		org.python.Object[] args = { org.python.types.Int.getInt(year), org.python.types.Int.getInt(month), org.python.types.Int.getInt(day) };
		return new Date(args, Collections.emptyMap());
	}

	@org.python.Method(__doc__ = "Check if date is before this.date")
	public org.python.types.Bool before(Date d) {
		java.util.Calendar d1 = java.util.Calendar.getInstance();
		d1.set( (int)((org.python.types.Int) this.year).value, (int)((org.python.types.Int) this.month).value, (int)((org.python.types.Int) this.day).value);
		java.util.Calendar d2 = java.util.Calendar.getInstance();
		d2.set( (int)((org.python.types.Int) d.year).value, (int)((org.python.types.Int) d.month).value, (int)((org.python.types.Int) d.day).value);
		
		if (d1.before(d2)) return org.python.types.Bool.TRUE;
		else return org.python.types.Bool.FALSE;
	}

	@org.python.Method(__doc__ = "Check if date is after this.date")
	public org.python.types.Bool after(Date d) {
		java.util.Calendar d1 = java.util.Calendar.getInstance();
		d1.set( (int)((org.python.types.Int) this.year).value, (int)((org.python.types.Int) this.month).value, (int)((org.python.types.Int) this.day).value);
		java.util.Calendar d2 = java.util.Calendar.getInstance();
		d2.set( (int)((org.python.types.Int) d.year).value, (int)((org.python.types.Int) d.month).value, (int)((org.python.types.Int) d.day).value);
		
		if (d1.after(d2)) return org.python.types.Bool.TRUE;
		else return org.python.types.Bool.FALSE;
	}

    @org.python.Method(__doc__ = "Return a string representing the date")
    public org.python.Object ctime() {

	String[] monthList = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	long monthNum = ((org.python.types.Int) this.month).value;
	String monthStr = monthList[(int) monthNum - 1];

	String[] weekdayList = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
	long weekdayNum = ((org.python.types.Int) weekday()).value;
	String weekdayStr = weekdayList[(int) weekdayNum];

	return new org.python.types.Str(weekdayStr + " " + monthStr + " " + this.day + " 00:00:00 " + this.year);
    }

    @org.python.Method(__doc__ = "Return the day of the week as an integer, where Monday is 0 and Sunday is 6. ")
    public org.python.Object weekday() {
	long y = ((org.python.types.Int) this.year).value;
	long m = ((org.python.types.Int) this.month).value;
	long d = ((org.python.types.Int) this.day).value;

	java.util.Date myCalendar = new java.util.GregorianCalendar((int) y, (int) m - 1, (int) d).getTime();
	java.util.Calendar c = java.util.Calendar.getInstance();
	c.setTime(myCalendar);
	int day = c.get(java.util.Calendar.DAY_OF_WEEK);
	int[] convertToPython = { 6, 0, 1, 2, 3, 4, 5};
	return org.python.types.Int.getInt(convertToPython[day - 1]);

	}

	@org.python.Method(__doc__ = "Return the day of the week as an integer, where Monday is 1 and Sunday is 7.")
	public org.python.Object isoweekday() {
		org.python.types.Int dayOfWeek = (org.python.types.Int) weekday();
		return org.python.types.Int.getInt(dayOfWeek.value + 1);
	}
}
