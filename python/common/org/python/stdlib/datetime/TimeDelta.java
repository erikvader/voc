package org.python.stdlib.datetime;

import org.python.exceptions.ValueError;
import org.python.types.Float;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

public class TimeDelta extends org.python.types.Object {

    @org.python.Attribute
    public org.python.Object days = __days__();

    @org.python.Attribute
    public org.python.Object seconds = __seconds__();

    @org.python.Attribute
    public org.python.Object microseconds = __microseconds__();

    @org.python.Attribute
    public static org.python.Object min = __min__();
    @org.python.Attribute
    public static org.python.Object max = __max__();
    @org.python.Attribute
    public static org.python.Object resolution = __resolution__();

    public long totalNanoseconds = 0;

    @org.python.Method(__doc__ = "")
    public TimeDelta(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        super();

        this.days = org.python.types.Int.getInt(0);
        this.seconds = org.python.types.Int.getInt(0);
        this.microseconds = org.python.types.Int.getInt(0);

        int totalArgs = args.length + kwargs.size();
        if (totalArgs > 7) {
            throw new org.python.exceptions.TypeError("__new__() takes at most 7 arguments (" + totalArgs + " given)");
        }

        String[] allowed = {"days", "seconds", "microseconds", "milliseconds", "minutes", "hours", "weeks"};
        List<String> allowedList = Arrays.asList(allowed);
        if (!kwargs.isEmpty()) {
            boolean correct = true;
            for (java.lang.String key : kwargs.keySet()) {
                correct = allowedList.contains(key);
                if (!correct) {
                    throw new org.python.exceptions.TypeError("'" + key + "' is an invalid keyword argument for __new__()");
                }
            }
            if (args.length > 0) {
                if (kwargs.get("days") != null) {
                    throw new org.python.exceptions.TypeError("argument for __new__() given by name ('days') and position (1)");
                }

                if (kwargs.get("seconds") != null && args.length >= 2) {
                    throw new org.python.exceptions.TypeError("argument for __new__() given by name ('seconds') and position (2)");
                }

                if (kwargs.get("microseconds") != null && args.length >= 3) {
                    throw new org.python.exceptions.TypeError("argument for __new__() given by name ('microseconds') and position (3)");
                }

                if (kwargs.get("milliseconds") != null && args.length >= 4) {
                    throw new org.python.exceptions.TypeError("argument for __new__() given by name ('milliseconds') and position (4)");
                }

                if (kwargs.get("minutes") != null && args.length >= 5) {
                    throw new org.python.exceptions.TypeError("argument for __new__() given by name ('minutes') and position (5)");
                }

                if (kwargs.get("hours") != null && args.length >= 6) {
                    throw new org.python.exceptions.TypeError("argument for __new__() given by name ('hours') and position (6)");
                }
            }
        }

        if (args.length >= 1) {
            addDays(args[0]);
        }
        if (args.length >= 2) {
            addSeconds(args[1]);
        }
        if (args.length >= 3) {
            addMicroseconds(args[2]);
        }
        if (args.length >= 4) {
            addMilliseconds(args[3]);
        }
        if (args.length >= 5) {
            addMinutes(args[4]);
        }
        if (args.length >= 6) {
            addHours(args[5]);
        }
        if (args.length >= 7) {
            addWeeks(args[6]);
        }

        if (kwargs.get("days") != null) {
            addDays(kwargs.get("days"));
        }
        if (kwargs.get("seconds") != null) {
            addSeconds(kwargs.get("seconds"));
        }
        if (kwargs.get("microseconds") != null) {
            addMicroseconds(kwargs.get("microseconds"));
        }
        if (kwargs.get("milliseconds") != null) {
            addMilliseconds(kwargs.get("milliseconds"));
        }
        if (kwargs.get("minutes") != null) {
            addMinutes(kwargs.get("minutes"));
        }
        if (kwargs.get("hours") != null) {
            addHours(kwargs.get("hours"));
        }
        if (kwargs.get("weeks") != null) {
            addWeeks(kwargs.get("weeks"));
        }

        Duration duration = Duration.ofNanos(totalNanoseconds);
        long totalDays = duration.toDays();
        this.days = org.python.types.Int.getInt(totalDays);
        Duration durationSeconds = duration.minusDays(totalDays);
        long totalSeconds = durationSeconds.toSeconds();
        this.seconds = org.python.types.Int.getInt(totalSeconds);
        this.microseconds = org.python.types.Int.getInt(durationSeconds.minusSeconds(totalSeconds).toNanos() / 1000);
    }

    private double getDouble(org.python.Object object, String key) {
        try {
            return ((org.python.types.Float) object.__float__()).value;
        } catch (Exception ignored) {
            throw new org.python.exceptions.TypeError(
                "unsupported type for timedelta " + key + " component: " + object.typeName()
            );
        }
    }

    private void addDays(org.python.Object days) {
        double value = getDouble(days, "days");
        totalNanoseconds += value * 24 * 60 * 60 * 1e9;
    }
    private void addSeconds(org.python.Object seconds) {
        double value = getDouble(seconds, "seconds");
        totalNanoseconds += value * 1e9;
    }
    private void addMicroseconds(org.python.Object microseconds) {
        double value = getDouble(microseconds, "microseconds");
        totalNanoseconds += value * 1000;
    }
    private void addMilliseconds(org.python.Object milliseconds) {
        double value = getDouble(milliseconds, "milliseconds");
        totalNanoseconds += value * 1e6;
    }
    private void addMinutes(org.python.Object minutes) {
        double value = getDouble(minutes, "minutes");
        totalNanoseconds += value * 60 * 1e9;
    }
    private void addHours(org.python.Object hours) {
        double value = getDouble(hours, "hours");
        totalNanoseconds += value * 60 * 60 * 1e9;
    }
    private void addWeeks(org.python.Object weeks) {
        double value = getDouble(weeks, "weeks");
        totalNanoseconds += value * 7 * 24 * 60 * 60 * 1e9;
    }

    @org.python.Method(__doc__ = "returns days")
    public org.python.types.Str __days__() {
        return new org.python.types.Str(this.days + "");
    }

    @org.python.Method(__doc__ = "returns seconds")
    public org.python.types.Str __seconds__() {
        return new org.python.types.Str(this.seconds + "");
    }

    @org.python.Method(__doc__ = "returns microseconds")
    public org.python.types.Str __microseconds__() {
        return new org.python.types.Str(this.microseconds + "");
    }

    @org.python.Method(__doc__ = "returns the most negative TimeDelta object")
    public static TimeDelta __min__() {
        org.python.Object[] args = {};
        java.util.HashMap<java.lang.String, org.python.Object> kwargs = new HashMap<>();

        kwargs.put("days", org.python.types.Int.getInt(-999999999));

        TimeDelta TD = new TimeDelta(args, kwargs);
	    return TD;
    }

    @org.python.Method(__doc__ = "returns the most positive TimeDelta object")
    public static TimeDelta __max__() {
        org.python.Object[] args = {};
        java.util.HashMap<java.lang.String, org.python.Object> kwargs = new HashMap<>();

        kwargs.put("days", org.python.types.Int.getInt(999999999));
        kwargs.put("hours", org.python.types.Int.getInt(23));
        kwargs.put("minutes", org.python.types.Int.getInt(59));
        kwargs.put("seconds", org.python.types.Int.getInt(59));
        kwargs.put("microseconds", org.python.types.Int.getInt(999999));

        TimeDelta TD = new TimeDelta(args, kwargs);
        return TD;
    }

    @org.python.Method(__doc__ = "returns smallest possible difference between non-equal TimeDelta objects")
    public static TimeDelta __resolution__() {
        org.python.Object[] args = {};
        java.util.HashMap<java.lang.String, org.python.Object> kwargs = new HashMap<>();

        kwargs.put("microseconds", org.python.types.Int.getInt(1));

        TimeDelta TD = new TimeDelta(args, kwargs);
        return TD;
    }

    @org.python.Method(__doc__ = "returns total seconds of timedelta")
    public org.python.types.Str total_seconds() {
        long days = (((org.python.types.Int) this.days).value) * 24 * 3600;
        long sum_seconds = days + (((org.python.types.Int) this.seconds).value);
        long microseconds = (((org.python.types.Int) this.microseconds).value);
        String micro = "";
        if (microseconds == 0) {
            micro = "0";
        } else if (microseconds < 10) {
            micro = "00000" + microseconds;
        } else if (microseconds < 100) {
            micro = "0000" + microseconds;
        } else if (microseconds < 1000) {
            micro = "000" + microseconds;
        } else if (microseconds < 10000) {
            micro = "00" + microseconds;
        } else if (microseconds < 100000) {
            micro = "0" + microseconds;
        } else {
            micro = "" + microseconds;
        }
        String returnStr = ("" + sum_seconds + "." + micro);
        return new org.python.types.Str(returnStr);
    }

    @org.python.Method(__doc__ = "Adds another TimeDelta and returns the resulting TimeDelta", args = {"other"})
    public TimeDelta __add__(org.python.Object other) {
        long thisDays = ((org.python.types.Int) this.days).value;
        TimeDelta otherObject = (org.python.stdlib.datetime.TimeDelta) other;
        long otherDays = ((org.python.types.Int) otherObject.days).value;
        long thisSeconds = ((org.python.types.Int) this.seconds).value;
        long otherSeconds = ((org.python.types.Int) otherObject.seconds).value;
        long thisMicroseconds = ((org.python.types.Int) this.microseconds).value;
        long otherMicroSeconds = ((org.python.types.Int) otherObject.microseconds).value;
        long sumDays = thisDays + otherDays;
        long sumSeconds = thisSeconds + otherSeconds;
        long sumMicroseconds = thisMicroseconds + otherMicroSeconds;
        org.python.Object[] args = {org.python.types.Int.getInt(sumDays),
                                    org.python.types.Int.getInt(sumSeconds),
                                    org.python.types.Int.getInt(sumMicroseconds)};
        TimeDelta TD = new TimeDelta(args, Collections.EMPTY_MAP);
        return TD;
    }

    @org.python.Method(__doc__ = "Returns a copy of TimeDelta")
    public TimeDelta __pos__() {
        long otherSeconds = ((org.python.types.Int) this.seconds).value;
        long otherMicroSeconds = ((org.python.types.Int) this.microseconds).value;
        long otherDays = ((org.python.types.Int) this.days).value;
        org.python.Object[] args = {org.python.types.Int.getInt(otherDays),
                                    org.python.types.Int.getInt(otherSeconds),
                                    org.python.types.Int.getInt(otherMicroSeconds)};
        TimeDelta TD = new TimeDelta(args, Collections.EMPTY_MAP);
        return TD;
    }

    public org.python.types.Str __str__() {
        long days = ((org.python.types.Int) this.days).value;
        long seconds = ((org.python.types.Int) this.seconds).value;
        long microseconds = ((org.python.types.Int) this.microseconds).value;

        Duration duration = Duration.ofDays(days)
            .plus(
                Duration.ofSeconds(seconds)
                    .plus(Duration.ofNanos(microseconds * 1000))
            );

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days);
            sb.append(" day");
            if (days > 1) {
                sb.append("s");
            }
            sb.append(", ");
        }
        Duration hours = duration.minusDays(days);
        sb.append(hours.toHours());
        sb.append(":");
        Duration minutes = hours.minusHours(hours.toHours());
        sb.append(String.format("%02d", minutes.toMinutes()));
        sb.append(":");
        sb.append(String.format("%02d", minutes.minusMinutes(minutes.toMinutes()).toSeconds()));
        if (microseconds > 0) {
            sb.append(String.format(".%06d", microseconds));
        }
        return new org.python.types.Str(sb.toString());
    }

    @org.python.Method(__doc__ = "Delta multiplied by an integer, integer can't be zero", args = {"multiple"})
    public TimeDelta __mul__(org.python.Object multiple) {
        long mul;
        try {
            mul = ((org.python.types.Int) multiple).value;
        } catch (ClassCastException e) {
            throw new org.python.exceptions.TypeError("Argument needs to be an int, got " + multiple.typeName());
        }

        long days = ((org.python.types.Int)this.days).value;
        long seconds = ((org.python.types.Int)this.seconds).value;
        long microseconds = ((org.python.types.Int)this.microseconds).value;

        if(mul == 0) {
            throw new org.python.exceptions.ValueError("Argument can't be zero");
        }

        long mulDays = days * mul;
        long mulSeconds = seconds * mul;
        long mulMicroseconds = microseconds * mul;
        org.python.Object[] args = {org.python.types.Int.getInt(mulDays),
                                    org.python.types.Int.getInt(mulSeconds),
                                    org.python.types.Int.getInt(mulMicroseconds)};

        TimeDelta returnDelta = new TimeDelta(args, Collections.EMPTY_MAP);

        return returnDelta;
    }

    @org.python.Method(__doc__ = "Delta absolute values")
    public TimeDelta __abs__() {
        long absDays;
        long absSeconds;
        long absMicroseconds;

        long days = ((org.python.types.Int)this.days).value;
        long seconds = ((org.python.types.Int)this.seconds).value;
        long microseconds = ((org.python.types.Int)this.microseconds).value;
        if(days >= 0){
            absDays = days;
            absSeconds = seconds;
            absMicroseconds = microseconds;
        } else {
            absDays = -1*days;
            absSeconds = -1*seconds;
            absMicroseconds = -1*microseconds;
        }

        org.python.Object[] args = {org.python.types.Int.getInt(absDays),
                                    org.python.types.Int.getInt(absSeconds),
                                    org.python.types.Int.getInt(absMicroseconds)};

        TimeDelta returnDelta = new TimeDelta(args, Collections.EMPTY_MAP);

        return returnDelta;
    }
}
