package ch.bbw.lb.DataClasses;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;

public final class RepeatedEvent implements Comparable<RepeatedEvent> {
    public final Integer id;
    public final String title;
    public final LocalDate firstOccurrence;
    public final LocalDate lastOccurrence;
    public final LocalTime startTime;
    public final LocalTime endTime;
    public final DinnerTime dinnerTime;
    public final Integer userId;

    public RepeatedEvent(
            Integer id,
            String title,
            LocalDate firstOccurrence,
            LocalDate lastOccurrence,
            LocalTime startTime,
            LocalTime endTime,
            DinnerTime dinnerTime,
            Integer userId
    ) {
        this.id = id;
        this.title = title;
        this.firstOccurrence = firstOccurrence;
        this.lastOccurrence = lastOccurrence;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dinnerTime = dinnerTime;
        this.userId = userId;
    }

    private LocalDate getFirstOccurrence() {
        return firstOccurrence;
    }

    public LocalDate getLastOccurrence() {
        return lastOccurrence;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public DinnerTime getDinnerTime() {
        return dinnerTime;
    }

    public static final Comparator<RepeatedEvent> BY_OCCURRENCE_THEN_BY_TITLE =
            Comparator.comparing(RepeatedEvent::getFirstOccurrence)
                    .thenComparing(RepeatedEvent::getLastOccurrence)
                    .thenComparing(Comparator.comparing(RepeatedEvent::getTitle).reversed());

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RepeatedEvent) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.firstOccurrence, that.firstOccurrence) &&
                Objects.equals(this.lastOccurrence, that.lastOccurrence) &&
                Objects.equals(this.startTime, that.startTime) &&
                Objects.equals(this.endTime, that.endTime) &&
                Objects.equals(this.dinnerTime, that.dinnerTime) &&
                Objects.equals(this.userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, firstOccurrence, lastOccurrence, startTime, endTime, dinnerTime, userId);
    }

    @Override
    public String toString() {
        return "RepeatedEvent[" +
                "id=" + id + ", " +
                "title=" + title + ", " +
                "firstOccurrence=" + firstOccurrence + ", " +
                "lastOccurrence=" + lastOccurrence + ", " +
                "startTime=" + startTime + ", " +
                "endTime=" + endTime + ", " +
                "dinnerTime=" + dinnerTime + ", " +
                "userId=" + userId + ']';
    }

    @Override
    public int compareTo(@NotNull RepeatedEvent o) {
        return BY_OCCURRENCE_THEN_BY_TITLE.compare(this, o);
    }
}