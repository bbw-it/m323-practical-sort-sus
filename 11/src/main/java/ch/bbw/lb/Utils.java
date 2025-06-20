package ch.bbw.lb;

import java.util.Comparator;
import java.util.List;

public class Utils {
    public static <T> boolean isSorted(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
                System.out.println("❌ Not sorted correctly!");
                return false;
            }
        }
        System.out.println("✅ Sorted correctly!");
        return true;
    }
}
