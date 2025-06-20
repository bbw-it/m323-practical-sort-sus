package ch.bbw.lb.DataClasses;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;

public final class OneTimeEvent implements Comparable<OneTimeEvent>  {
    public final Integer id;
    public final String title;
    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;
    public final DinnerTime dinnerTime;
    public final Integer userId;

    public OneTimeEvent(
            Integer id,
            String title,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            DinnerTime dinnerTime,
            Integer userId
    ) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dinnerTime = dinnerTime;
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    private LocalTime getStartTime() {
        return startTime;
    }

    private LocalTime getEndTime() {
        return endTime;
    }

    public DinnerTime getDinnerTime() {
        return dinnerTime;
    }

    @Override
    public int compareTo(@NotNull OneTimeEvent other) {
        return BY_DATE_then_START_REVERSED_END.compare(this, other);
    }

    // Statischer Comparator für komplexe Sortierung
    public static final Comparator<OneTimeEvent> BY_DATE_then_START_REVERSED_END =
            Comparator.comparing(OneTimeEvent::getDate)
                    .thenComparing(OneTimeEvent::getStartTime)
                    .thenComparing(Comparator.comparing(OneTimeEvent::getEndTime).reversed());

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (OneTimeEvent) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.date, that.date) &&
                Objects.equals(this.startTime, that.startTime) &&
                Objects.equals(this.endTime, that.endTime) &&
                Objects.equals(this.dinnerTime, that.dinnerTime) &&
                Objects.equals(this.userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, startTime, endTime, dinnerTime, userId);
    }

    @Override
    public String toString() {
        return "OneTimeEvent[" +
                "id=" + id + ", " +
                "title=" + title + ", " +
                "date=" + date + ", " +
                "startTime=" + startTime + ", " +
                "endTime=" + endTime + ", " +
                "dinnerTime=" + dinnerTime + ", " +
                "userId=" + userId + ']';
    }

    public static class ComparatorInstance implements Comparator<OneTimeEvent> {
        @Override
        public int compare(OneTimeEvent o1, OneTimeEvent o2) {
            return BY_DATE_then_START_REVERSED_END.compare(o1, o2);
        }
    }
}