package ch.bbw.sven_weber;

import ch.bbw.sven_weber.model.User;

import java.util.Comparator;

public class EmailComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getEmailDomain().compareTo(o2.getEmailDomain());
    }
}
