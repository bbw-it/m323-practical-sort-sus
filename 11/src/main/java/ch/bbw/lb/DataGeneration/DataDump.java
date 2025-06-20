package ch.bbw.lb.DataGeneration;

import ch.bbw.lb.DataClasses.OneTimeEvent;
import ch.bbw.lb.DataClasses.RepeatedEvent;
import ch.bbw.lb.DataClasses.User;

import java.util.List;

public record DataDump(
    List<User> users,
    List<OneTimeEvent> oneTimeEvents,
    List<RepeatedEvent> repeatedEvents
) {}