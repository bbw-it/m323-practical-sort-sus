package ch.bbw.lb.DataClasses;


import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;

public final class DinnerTime implements Comparable<DinnerTime> {
    public final PresenceType presenceType;
    public final LocalTime time;

    public DinnerTime(
            PresenceType presenceType,
            LocalTime time
    ) {
        this.presenceType = presenceType;
        this.time = time;
    }

    public PresenceType getPresenceType() {
        return presenceType;
    }

    public LocalTime getTime() {
        return time;
    }

    public static final Comparator<DinnerTime> COMPARATOR =
            Comparator.comparing(
                    DinnerTime::getPresenceType,
                    Comparator.nullsFirst(Comparator.naturalOrder())
            ).thenComparing(
                    DinnerTime::getTime,
                    Comparator.nullsFirst(Comparator.naturalOrder())
            );

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DinnerTime) obj;
        return Objects.equals(this.presenceType, that.presenceType) &&
                Objects.equals(this.time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presenceType, time);
    }

    @Override
    public String toString() {
        return "DinnerTime[" +
                "presenceType=" + presenceType + ", " +
                "time=" + time + ']';
    }

    @Override
    public int compareTo(@NotNull DinnerTime o) {
        return COMPARATOR.compare(this, o);
    }

    public static class ComparatorInstance implements Comparator<DinnerTime> {
        @Override
        public int compare(DinnerTime o1, DinnerTime o2) {
            return COMPARATOR.compare(o1, o2);
        }
    }
}