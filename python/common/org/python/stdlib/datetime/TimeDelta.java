package org.python.stdlib.datetime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class TimeDelta extends org.python.types.Object {
    public static final Map<String, org.python.Object> EMPTY_KWARGS = Collections.unmodifiableMap(new HashMap<>());
    public static final org.python.Object[] EMPTY_ARGS = {};

    public static final int SECONDS_PER_DAY = 3600 * 24;
    public static final int MAX_DAYS = 999999999;

    private static final TimeDelta minDelta;
    private static final TimeDelta maxDelta;

    static {
        minDelta = new TimeDelta(EMPTY_ARGS, EMPTY_KWARGS);
        minDelta.days = org.python.types.Int.getInt(-MAX_DAYS);

        maxDelta = new TimeDelta(EMPTY_ARGS, EMPTY_KWARGS);
        maxDelta.days = org.python.types.Int.getInt(MAX_DAYS);
        maxDelta.seconds = org.python.types.Int.getInt(86399);
        maxDelta.microseconds = org.python.types.Int.getInt(999999);
    }

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

    private Duration duration;

    @org.python.Method(__doc__ = "")
    public TimeDelta(org.python.Object[] args, java.util.Map<java.lang.String, org.python.Object> kwargs) {
        super();

        duration = Duration.ZERO;

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
            for (java.lang.String key : kwargs.keySet()) {
                if (!allowedList.contains(key)) {
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
            addDays(getDouble("days", args[0]));
        }
        if (args.length >= 2) {
            addSeconds(getDouble("seconds", args[1]));
        }
        if (args.length >= 3) {
            addMicroseconds(getDouble("microseconds", args[2]));
        }
        if (args.length >= 4) {
            addMilliseconds(getDouble("milliseconds", args[3]));
        }
        if (args.length >= 5) {
            addMinutes(getDouble("minutes", args[4]));
        }
        if (args.length >= 6) {
            addHours(getDouble("hours", args[5]));
        }
        if (args.length >= 7) {
            addWeeks(getDouble("weeks", args[6]));
        }

        if (kwargs.get("days") != null) {
            addDays(getDouble("days", kwargs.get("days")));
        }
        if (kwargs.get("seconds") != null) {
            addSeconds(getDouble("seconds", kwargs.get("seconds")));
        }
        if (kwargs.get("microseconds") != null) {
            addMicroseconds(getDouble("microseconds", kwargs.get("microseconds")));
        }
        if (kwargs.get("milliseconds") != null) {
            addMilliseconds(getDouble("milliseconds", kwargs.get("milliseconds")));
        }
        if (kwargs.get("minutes") != null) {
            addMinutes(getDouble("minutes", kwargs.get("minutes")));
        }
        if (kwargs.get("hours") != null) {
            addHours(getDouble("hours", kwargs.get("hours")));
        }
        if (kwargs.get("weeks") != null) {
            addWeeks(getDouble("weeks", kwargs.get("weeks")));
        }

        normalize();
    }

    private double getDouble(String key, org.python.Object object) {
        try {
            return ((org.python.types.Float) object.__float__()).value;
        } catch (Exception ignored) {
            throw new org.python.exceptions.TypeError(
                "unsupported type for timedelta " + key + " component: " + object.typeName()
            );
        }
    }

    private void normalize() {
        double totalMicroseconds;
        long totalDays = duration.toDays();
        long totalSeconds;
        long roundedMicroseconds;
        long daysMagnitude = Math.abs(totalDays);

        if (duration.isNegative()) {
            // Handle negative durations
            long fullDays = duration.minusDays(totalDays).isZero() ? daysMagnitude : (daysMagnitude + 1);
            Duration negative = Duration.ofDays(fullDays).plus(duration.minusDays(totalDays));

            Duration withoutDays = negative.minusDays(negative.toDays());
            Duration withoutSeconds = withoutDays.minusSeconds(withoutDays.toSeconds());

            totalDays = -fullDays;
            totalSeconds = withoutDays.toSeconds();
            totalMicroseconds = withoutSeconds.toNanos() / 1000.0;
        } else {
            Duration withoutDays = duration.minusDays(totalDays);
            totalSeconds = withoutDays.toSeconds();

            Duration withoutSeconds = withoutDays.minusSeconds(totalSeconds);
            totalMicroseconds = withoutSeconds.toNanos() / 1000.0;
        }

        // Round microseconds using round-half-to-even
        roundedMicroseconds = BigDecimal.valueOf(totalMicroseconds)
            .setScale(0, RoundingMode.HALF_EVEN)
            .longValue();

        // Check days magnitude is within limits
        if (daysMagnitude >= MAX_DAYS) {
            throw new org.python.exceptions.OverflowError("days=" + totalDays + "; must have magnitude <= " + MAX_DAYS);
        }

        // Store values
        this.days = org.python.types.Int.getInt(totalDays);
        this.seconds = org.python.types.Int.getInt(totalSeconds);
        this.microseconds = org.python.types.Int.getInt(roundedMicroseconds);
    }

    private void addDays(double days) {
        long fullDays = (long) days;
        duration = duration.plusDays(fullDays);
        double seconds = (days - fullDays) * SECONDS_PER_DAY;
        addSeconds(seconds);
    }

    private void addSeconds(double seconds) {
        long fullSeconds = (long) seconds;
        duration = duration.plusSeconds(fullSeconds);
        double microseconds = (seconds - fullSeconds) * 1e6;
        addMicroseconds(microseconds);
    }

    private void addMicroseconds(double microseconds) {
        double nanoseconds = microseconds * 1000.0;
        duration = duration.plusNanos(Math.round(nanoseconds));
    }

    private void addMilliseconds(double milliseconds) {
        addMicroseconds(milliseconds * 1000);
    }

    private void addMinutes(double minutes) {
        addSeconds(minutes * 60);
    }

    private void addHours(double hours) {
        addMinutes(hours * 60);
    }

    private void addWeeks(double weeks) {
        addDays(weeks * 7);
    }

    @org.python.Method(__doc__ = "returns days")
    public org.python.Object __days__() {
        return this.days;
    }

    @org.python.Method(__doc__ = "returns seconds")
    public org.python.Object __seconds__() {
        return this.seconds;
    }

    @org.python.Method(__doc__ = "returns microseconds")
    public org.python.Object __microseconds__() {
        return this.microseconds;
    }

    @org.python.Method(__doc__ = "returns the most negative TimeDelta object")
    public static TimeDelta __min__() {
        return minDelta;
    }

    @org.python.Method(__doc__ = "returns the most positive TimeDelta object")
    public static TimeDelta __max__() {
        return maxDelta;
    }

    @org.python.Method(__doc__ = "returns smallest possible difference between non-equal TimeDelta objects")
    public static TimeDelta __resolution__() {
        org.python.Object[] args = {};
        java.util.Map<java.lang.String, org.python.Object> kwargs = new java.util.HashMap<>();

        kwargs.put("microseconds", org.python.types.Int.getInt(1));

        return new TimeDelta(args, kwargs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimeDelta)) {
            return false;
        }
        TimeDelta other = (TimeDelta) o;
        return Objects.equals(days, other.days) &&
            Objects.equals(seconds, other.seconds) &&
            Objects.equals(microseconds, other.microseconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(days, seconds, microseconds);
    }

    @org.python.Method(
            __doc__ = "Return self == value.",
            args = {"other"}
    )
    public org.python.Object __eq__(org.python.Object other) {
        return org.python.types.Bool.getBool(equals(other));
    }

    @org.python.Method(
            __doc__ = "Return self >= value.",
            args = {"other"}
    )
    public org.python.Object __ge__(org.python.Object other) {
        if (!(other instanceof TimeDelta)) {
            throw new org.python.exceptions.TypeError(
                "'>=' not supported between instances of 'datetime.timedelta' and '" + other.typeName() + "'"
            );
        }
        if (equals(other)) {
            return org.python.types.Bool.getBool(true);
        }
        return __gt__(other);
    }

    @org.python.Method(
            __doc__ = "Return self > value.",
            args = {"other"}
    )
    public org.python.Object __gt__(org.python.Object other) {
        if (!(other instanceof TimeDelta)) {
            throw new org.python.exceptions.TypeError(
                "'>' not supported between instances of 'datetime.timedelta' and '" + other.typeName() + "'"
            );
        }
        TimeDelta o = (TimeDelta) other;
        return total_seconds().__gt__(o.total_seconds());
    }

    @org.python.Method(
            __doc__ = "Return self <= value.",
            args = {"other"}
    )
    public org.python.Object __le__(org.python.Object other) {
        if (!(other instanceof TimeDelta)) {
            throw new org.python.exceptions.TypeError(
                "'<=' not supported between instances of 'datetime.timedelta' and '" + other.typeName() + "'"
            );
        }
        if (equals(other)) {
            return org.python.types.Bool.getBool(true);
        }
        return __lt__(other);
    }

    @org.python.Method(
            __doc__ = "Return self < value.",
            args = {"other"}
    )
    public org.python.Object __lt__(org.python.Object other) {
        if (!(other instanceof TimeDelta)) {
            throw new org.python.exceptions.TypeError(
                "'<' not supported between instances of 'datetime.timedelta' and '" + other.typeName() + "'"
            );
        }
        TimeDelta o = (TimeDelta) other;
        return total_seconds().__lt__(o.total_seconds());
    }

    @org.python.Method()
    public org.python.types.Float total_seconds() {
        long total = (((org.python.types.Int) this.days).value) * 24 * 3600 * 1000000;
        total += (((org.python.types.Int) this.seconds).value * 1000000);
        total += (((org.python.types.Int) this.microseconds).value);
        return new org.python.types.Float(total / 1000.0);
    }

    @org.python.Method(__doc__ = "Adds another TimeDelta and returns the resulting TimeDelta", args = {"other"})
    public TimeDelta __add__(org.python.Object other) {
        if (!(other instanceof TimeDelta)) {
            throw new org.python.exceptions.TypeError(
                "'+' not supported between instances of 'datetime.timedelta' and '" + other.typeName() + "'"
            );
        }
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
        return new TimeDelta(args, EMPTY_KWARGS);
    }

    @org.python.Method(__doc__ = "Returns a copy of TimeDelta")
    public TimeDelta __pos__() {
        long otherSeconds = ((org.python.types.Int) this.seconds).value;
        long otherMicroSeconds = ((org.python.types.Int) this.microseconds).value;
        long otherDays = ((org.python.types.Int) this.days).value;
        org.python.Object[] args = {org.python.types.Int.getInt(otherDays),
            org.python.types.Int.getInt(otherSeconds),
            org.python.types.Int.getInt(otherMicroSeconds)};
        return new TimeDelta(args, EMPTY_KWARGS);
    }

    public org.python.types.Str __str__() {
        long days = ((org.python.types.Int) this.days).value;
        long seconds = ((org.python.types.Int) this.seconds).value;
        long microseconds = ((org.python.types.Int) this.microseconds).value;

        Duration duration = Duration.ofDays(days)
                .plus(Duration.ofSeconds(seconds).plus(Duration.ofNanos(microseconds * 1000)));

        StringBuilder sb = new StringBuilder();
        if (Math.abs(days) > 0) {
            sb.append(days);
            sb.append(" day");
            if (Math.abs(days) > 1) {
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

        long days = ((org.python.types.Int) this.days).value;
        long seconds = ((org.python.types.Int) this.seconds).value;
        long microseconds = ((org.python.types.Int) this.microseconds).value;

        if (mul == 0) {
            throw new org.python.exceptions.ValueError("Argument can't be zero");
        }

        long mulDays = days * mul;
        long mulSeconds = seconds * mul;
        long mulMicroseconds = microseconds * mul;
        org.python.Object[] args = {
            org.python.types.Int.getInt(mulDays),
            org.python.types.Int.getInt(mulSeconds),
            org.python.types.Int.getInt(mulMicroseconds)
        };

        return new TimeDelta(args, EMPTY_KWARGS);
    }

    @org.python.Method(__doc__ = "Delta absolute values")
    public TimeDelta __abs__() {
        long absDays;
        long absSeconds;
        long absMicroseconds;

        long days = ((org.python.types.Int) this.days).value;
        long seconds = ((org.python.types.Int) this.seconds).value;
        long microseconds = ((org.python.types.Int) this.microseconds).value;
        if (days >= 0) {
            absDays = days;
            absSeconds = seconds;
            absMicroseconds = microseconds;
        } else {
            absDays = -1 * days;
            absSeconds = -1 * seconds;
            absMicroseconds = -1 * microseconds;
        }

        org.python.Object[] args = {org.python.types.Int.getInt(absDays),
            org.python.types.Int.getInt(absSeconds),
            org.python.types.Int.getInt(absMicroseconds)};

        return new TimeDelta(args, EMPTY_KWARGS);
    }

    @org.python.Method(__doc__ = "Delta modulus, returns a timedelta with remainders")
    public TimeDelta __mod__(org.python.Object otherObject) {
        if (!(otherObject instanceof TimeDelta)) {
            throw new org.python.exceptions.TypeError(
                "'%' not supported between instances of 'datetime.timedelta' and '" + otherObject.typeName() + "'"
            );
        }

        TimeDelta other = (org.python.stdlib.datetime.TimeDelta) otherObject;
        long thisDays = ((org.python.types.Int) this.days).value;
        long otherDays = ((org.python.types.Int) other.days).value;
        long thisSeconds = ((org.python.types.Int) this.seconds).value;
        long otherSeconds = ((org.python.types.Int) other.seconds).value;
        long thisMicroseconds = ((org.python.types.Int) this.microseconds).value;
        long otherMicroSeconds = ((org.python.types.Int) other.microseconds).value;
        long remainderDays;
        long remainderSeconds;
        long remainderMicroseconds;

        try {
            remainderDays = thisDays % otherDays;
        } catch (Exception ZeroDivisionError) {
            remainderDays = 0;
        }
        try {
            remainderSeconds = thisSeconds % otherSeconds;
        } catch (Exception ZeroDivisionError) {
            remainderSeconds = 0;
        };
        try {
            remainderMicroseconds = thisMicroseconds % otherMicroSeconds;
        } catch (Exception ZeroDivisionError) {
            remainderMicroseconds = 0;
        };

        org.python.Object[] args = {
            org.python.types.Int.getInt(remainderDays),
            org.python.types.Int.getInt(remainderSeconds),
            org.python.types.Int.getInt(remainderMicroseconds),
        };

        return new TimeDelta(args, EMPTY_KWARGS);
    }
}
