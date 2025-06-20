package ch.bbw.et.task;

import lombok.Value;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Value
public class Task implements Comparable<Task> {

    private int id;
    private String name;
    private boolean active;
    private Date created;
    private List<String> tags;

    public Task(int id, String name, boolean active, Date created, List<String> tags) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.created = created;
        this.tags = tags;
    }

    public static Comparator<Task> comparatorByActiveThenCreated() {
        return Comparator
                .comparing(Task::isActive).reversed()
                .thenComparing(Task::getCreated);
    }

    public static Comparator<Task> comparatorByTagCountThenName() {
        return Comparator
                .comparingInt((Task t) -> t.getTags().size()).reversed()
                .thenComparing(Task::getName);
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
