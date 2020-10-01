package org.python.stdlib.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import org.python.types.Int;

import java.util.Calendar;
import python.datetime;

public class DateTime extends org.python.types.Object {
	private final int YEAR_INDEX = 0;
	private final int MONTH_INDEX = 1;
	private final int DAY_INDEX = 2;
	private final int HOUR_INDEX = 3;
	private final int MINUTE_INDEX = 4;
	private final int SECOND_INDEX = 5;
	private final int MICROSECOND_INDEX = 6;

	private final int MIN_YEAR = 1;
	private final int MAX_YEAR = 9999;

	private long[] timeUnits = { 0l, 0l, 0l, 0l, 0l, 0l, 0l };

	@org.python.Attribute
	public final org.python.Object year;

	@org.python.Attribute
	public final org.python.Object month;

	@org.python.Attribute
	public final org.python.Object day;

	@org.python.Attribute
	public final org.python.Object hour;

	@org.python.Attribute
	public final org.python.Object minute;

	@org.python.Attribute
	public final org.python.Object second;

	@org.python.Attribute
	public final org.python.Object microsecond;

	@org.python.Attribute
	public static final org.python.Object min = __min__();

	@org.python.Attribute
	public static final org.python.Object max = __max__();

	public DateTime(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
		super();
		String[] keys = { "year", "month", "day", "hour", "minute", "second", "microsecond" };
		boolean kwargsIsUsed = false;
		int keyIndex = 0;
		int argIndex = 0;

		for (String key : keys) {
			if (kwargs.get(key) != null) {
				this.timeUnits[keyIndex] = ((org.python.types.Int) kwargs.get(key)).value;
				kwargsIsUsed = true;
			} else if (args.length > argIndex) {		
				this.timeUnits[keyIndex] = ((org.python.types.Int) args[argIndex]).value;
				argIndex++;
			} else if (keyIndex < 3) {
				throw new org.python.exceptions.TypeError(
						"Required argument '" + keys[keyIndex] + "' (pos " + (keyIndex + 1) + ") not found");
			}
			keyIndex++;
		}

		if (this.timeUnits[YEAR_INDEX] < MIN_YEAR || this.timeUnits[YEAR_INDEX] > MAX_YEAR) {
			throw new org.python.exceptions.ValueError("year " + this.timeUnits[YEAR_INDEX] + "is out of range");
		}

		if (this.timeUnits[MONTH_INDEX] < 1 || this.timeUnits[MONTH_INDEX] > 12) {
			throw new org.python.exceptions.ValueError("month " + this.timeUnits[MONTH_INDEX] + "is out of range");
		}
		if (this.timeUnits[DAY_INDEX] < 1 || this.timeUnits[DAY_INDEX] > 31) {
			throw new org.python.exceptions.ValueError("day " + this.timeUnits[DAY_INDEX] + "is out of range");
		}

		if (this.timeUnits[HOUR_INDEX] < 0 || this.timeUnits[HOUR_INDEX] > 24) {
			throw new org.python.exceptions.ValueError("hour " + this.timeUnits[HOUR_INDEX] + "is out of range");
		}

		if (this.timeUnits[MINUTE_INDEX] < 0 || this.timeUnits[MINUTE_INDEX] > 60) {
			throw new org.python.exceptions.ValueError("minute " + this.timeUnits[MINUTE_INDEX] + "is out of range");
		}

		if (this.timeUnits[SECOND_INDEX] < 0 || this.timeUnits[SECOND_INDEX] > 60) {
			throw new org.python.exceptions.ValueError("second " + this.timeUnits[SECOND_INDEX] + "is out of range");
		}

		if (this.timeUnits[MICROSECOND_INDEX] < 0 || this.timeUnits[MICROSECOND_INDEX] > 1000000) {
			throw new org.python.exceptions.ValueError(
					"microsecond " + this.timeUnits[MICROSECOND_INDEX] + "is out of range");
		}

		this.year = __year__();
		this.month = __month__();
		this.day = __day__();
		this.hour = __hour__();
		this.minute = __minute__();
		this.second = __second__();
		this.microsecond = __microsecond__();
	}

	public org.python.types.Str __str__() {
		String year = Long.toString(this.timeUnits[YEAR_INDEX]);
		while (year.length() < 4)
			year = "0" + year;

		String month = Long.toString(this.timeUnits[MONTH_INDEX]);
		while (month.length() < 2)
			month = "0" + month;

		String day = Long.toString(this.timeUnits[DAY_INDEX]);
		while (day.length() < 2)
			day = "0" + day;

		String hour = this.timeUnits[HOUR_INDEX] != 0 ? Long.toString(this.timeUnits[HOUR_INDEX]) : "00";
		while (hour.length() < 2)
			hour = "0" + hour;

		String minute = this.timeUnits[MINUTE_INDEX] != 0 ? Long.toString(this.timeUnits[MINUTE_INDEX]) : "00";
		while (minute.length() < 2)
			minute = "0" + minute;

		String second = this.timeUnits[SECOND_INDEX] != 0 ? Long.toString(this.timeUnits[SECOND_INDEX]) : "00";
		while (second.length() < 2)
			second = "0" + second;

		String microsecond = this.timeUnits[MICROSECOND_INDEX] != 0 ? Long.toString(this.timeUnits[MICROSECOND_INDEX])
				: "";
		while (microsecond.length() < 6 && microsecond.length() != 0)
			microsecond = "0" + microsecond;

		String returnStr = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

		returnStr += microsecond.length() > 0 ? "." + microsecond : "";
		return new org.python.types.Str(returnStr);
	}

	@org.python.Method(__doc__ = "")
	public org.python.Object date() {
		org.python.Object[] args = { org.python.types.Int.getInt(this.timeUnits[YEAR_INDEX]),
				org.python.types.Int.getInt(this.timeUnits[MONTH_INDEX]),
				org.python.types.Int.getInt(this.timeUnits[DAY_INDEX]) };
		return new Date(args, Collections.emptyMap());
	}

	@org.python.Method(__doc__ = "")
	public static org.python.Object today() {
		java.time.LocalDateTime today = java.time.LocalDateTime.now();
		org.python.Object[] args = { org.python.types.Int.getInt(today.getYear()),
				org.python.types.Int.getInt(today.getMonth().getValue()),
				org.python.types.Int.getInt(today.getDayOfMonth()), org.python.types.Int.getInt(today.getHour()),
				org.python.types.Int.getInt(today.getMinute()), org.python.types.Int.getInt(today.getSecond()),
				org.python.types.Int.getInt(today.getNano() / 1000) };
		return new DateTime(args, Collections.emptyMap());
	}

	@org.python.Method(__doc__ = "returns year")
	public org.python.types.Int __year__() {
		return org.python.types.Int.getInt(this.timeUnits[YEAR_INDEX]);
	}

	@org.python.Method(__doc__ = "returns month")
	public org.python.types.Int __month__() {
		return org.python.types.Int.getInt(this.timeUnits[MONTH_INDEX]);
	}

	@org.python.Method(__doc__ = "returns day")
	public org.python.types.Int __day__() {
		return org.python.types.Int.getInt(this.timeUnits[DAY_INDEX]);
	}

	@org.python.Method(__doc__ = "returns hour")
	public org.python.types.Int __hour__() {
		return org.python.types.Int.getInt(this.timeUnits[HOUR_INDEX]);
	}

	@org.python.Method(__doc__ = "returns minute")
	public org.python.types.Int __minute__() {
		return org.python.types.Int.getInt(this.timeUnits[MINUTE_INDEX]);
	}

	@org.python.Method(__doc__ = "returns second")
	public org.python.types.Int __second__() {
		return org.python.types.Int.getInt(this.timeUnits[SECOND_INDEX]);
	}

	@org.python.Method(__doc__ = "returns microsecond")
	public org.python.types.Int __microsecond__() {
		return org.python.types.Int.getInt(this.timeUnits[MICROSECOND_INDEX]);
	}

	@org.python.Method(__doc__ = "")
	private static org.python.Object __min__() {
		org.python.types.Int year = org.python.types.Int.getInt(1);
		org.python.types.Int month = org.python.types.Int.getInt(1);
		org.python.types.Int day = org.python.types.Int.getInt(1);

		org.python.Object[] args = { year, month, day };
		return new DateTime(args, Collections.emptyMap());
	}

	@org.python.Method(__doc__ = "")
	private static org.python.Object __max__() {
		org.python.types.Int year = org.python.types.Int.getInt(9999);
		org.python.types.Int month = org.python.types.Int.getInt(12);
		org.python.types.Int day = org.python.types.Int.getInt(31);
		org.python.types.Int hour = org.python.types.Int.getInt(23);
		org.python.types.Int minute = org.python.types.Int.getInt(59);
		org.python.types.Int second = org.python.types.Int.getInt(59);
		org.python.types.Int microsecond = org.python.types.Int.getInt(999999);

		org.python.Object[] args = { year, month, day, hour, minute, second, microsecond };
		return new DateTime(args, Collections.emptyMap());
	}

	@org.python.Method(__doc__ = "")
	public org.python.Object weekday() {
		double y = ((org.python.types.Int) this.year).value;
		double m = ((org.python.types.Int) this.month).value;
		double d = ((org.python.types.Int) this.day).value;

		java.util.Date myCalendar = new java.util.GregorianCalendar((int) y, (int) m - 1, (int) d).getTime();
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(myCalendar);
		int day = c.get(java.util.Calendar.DAY_OF_WEEK);
		int[] convertToPython = { 6, 0, 1, 2, 3, 4, 5 };
		return org.python.types.Int.getInt(convertToPython[day - 1]);

	}

	// Added instance method
	
	@org.python.Method(__doc__ = "Return the total number of seconds since 1970, i.e. UNIX time. This assumes that the time is in local time if tzinfo is None.")
	public org.python.Object timestamp() {

		int year = (int)this.timeUnits[YEAR_INDEX];

		int month = (int)this.timeUnits[MONTH_INDEX];

		int day = (int)this.timeUnits[DAY_INDEX];

		int hour = (int)this.timeUnits[HOUR_INDEX];

		int minute = (int)this.timeUnits[MINUTE_INDEX];

		int microsec = (int)this.timeUnits[MICROSECOND_INDEX];
		
		int seconds = (int)this.timeUnits[SECOND_INDEX];

		Calendar now = new java.util.GregorianCalendar(year,month - 1,day,hour,minute,seconds);
		now.set(Calendar.MILLISECOND, microsec/1000);

		double totalsec = now.getTimeInMillis() / 1000;
		return new org.python.types.Float(totalsec);

	}
    
	// Added class method

	@org.python.Method(__doc__ = "Returns a datetime object based on a iso-formatted input")
	public static org.python.Object fromisoformat(String isoString) {

	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

	java.util.Date isodt;
	try {
		isodt = sdf.parse(isoString); // create date obj based on iso string
	} catch (ParseException pe) {
		System.out.println(pe);
		throw new org.python.exceptions.ValueError("Invalid isoformat string: " + isoString);
	}

	SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
	SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
	SimpleDateFormat sdfHour = new SimpleDateFormat("HH");
	SimpleDateFormat sdfMin = new SimpleDateFormat("mm");
	SimpleDateFormat sdfSec = new SimpleDateFormat("ss");
	SimpleDateFormat sdfMic = new SimpleDateFormat("SSSSSS");

	String getYear = sdfYear.format(isodt);
	String getMonth = sdfMonth.format(isodt);
	String getDay = sdfDay.format(isodt);
	String getHour = sdfHour.format(isodt);
	String getMin = sdfMin.format(isodt);
	String getSec = sdfSec.format(isodt);
	String getMic = sdfMic.format(isodt);

	int iYear = Integer.parseInt(getYear);
	int iMonth = Integer.parseInt(getMonth);
	int iDay = Integer.parseInt(getDay);
	int iHour = Integer.parseInt(getHour);
	int iMin = Integer.parseInt(getMin);
	int iSec = Integer.parseInt(getSec);
	int iMic = Integer.parseInt(getMic);
	
	org.python.types.Int yea = org.python.types.Int.getInt(iYear);
	org.python.types.Int mont = org.python.types.Int.getInt(iMonth);
	org.python.types.Int da = org.python.types.Int.getInt(iDay);
	org.python.types.Int hou = org.python.types.Int.getInt(iHour);
	org.python.types.Int minut = org.python.types.Int.getInt(iMin);
	org.python.types.Int secon = org.python.types.Int.getInt(iSec);
	org.python.types.Int microsecon = org.python.types.Int.getInt(iMic); 

	org.python.Object[] args = { yea, mont, da, hou, minut, secon, microsecon };
	return new DateTime(args, Collections.emptyMap());

	}

	@org.python.Method(__doc__ = "Checks if current dateobject is less than dt")
	public org.python.types.Object __lt__(DateTime dt) {

		return org.python.types.Bool.getBool(this.compare(dt)==-1);
	}

	@org.python.Method(__doc__ = "Check if dateobject are equal")
	public org.python.types.Object __eq__(DateTime dt) {

		return org.python.types.Bool.getBool(this.compare(dt)==0);
	}

	@org.python.Method(__doc__ = "Check if current dateobject is greater than dt")
	public org.python.types.Object __gt__(DateTime dt) {

		return org.python.types.Bool.getBool(this.compare(dt)==1);

	}

	@org.python.Method(__doc__ = "Check if current dateobject is greater than or equal to dt")
	public org.python.types.Object __ge__(DateTime dt) {

		return org.python.types.Bool.getBool(this.compare(dt)>= 0);

	}

	@org.python.Method(__doc__ = "Check if current dateobject is less than or equal to dt")
	public org.python.types.Object __le__(DateTime dt) {

		return org.python.types.Bool.getBool(this.compare(dt)<= 0);

	}

	@org.python.Method(__doc__ = "Check if current dateobject is not equal to dt")
	public org.python.types.Object __ne__(DateTime dt) {

		return org.python.types.Bool.getBool(this.compare(dt) != 0);

	}

	private int compare(DateTime dt2) {

		for (int i=0; i<this.timeUnits.length; i++) {
			if (this.timeUnits[i] < dt2.timeUnits[i]) {

				return -1;
			}
			if (this.timeUnits[i] > dt2.timeUnits[i]) {

				return 1;
			}
		
		}

		return 0;
	}
}
