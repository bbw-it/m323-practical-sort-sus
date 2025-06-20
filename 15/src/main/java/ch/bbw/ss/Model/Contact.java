package ch.bbw.ss.Model;

import java.util.Comparator;

public record Contact (String phone, String email) {
    static Comparator<Contact> byPhone = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return CharSequence.compare(c1.phone, c2.phone);
        }
    };

    static Comparator<Contact> byEmail = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return CharSequence.compare(c1.email, c2.email);
        }
    };
}
