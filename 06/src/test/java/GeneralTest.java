import ch.bbw.et.task.Task;
import org.instancio.Instancio;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneralTest {

    @Ignore
    @Test
    public void testData(){
        List<Task> list = Instancio.ofList(Task.class).size(100).create(); //Keine Ahnung ob die ID's unique sind könnte für Fehler sorgen.

        assertEquals(10,  list.size()); //nur da um mit debugger die generierten Daten anzuschauen
    }

    @Test
    public void testSortById(){
        List<Task> list = Instancio.ofList(Task.class).size(100).create();

        list.sort(Comparator.comparing(Task::getId));
        list.stream().limit(50).forEach(System.out::println);
    }

    @Test
    public void testReverseOrder() {
        List<Task> list = Instancio.ofList(Task.class).size(100).create();
        list.sort(Collections.reverseOrder());
        list.stream().limit(10).forEach(System.out::println);
    }

    @Test
    public void testSortWithLambda(){
        List<Task> list = Instancio.ofList(Task.class).size(100).create();

        list.sort((o1, o2) -> {
            var value = o1.getId() - o2.getId();
            return value;
        });
        list.stream().limit(50).forEach(System.out::println);
    }

    @Test
    public void testSortWithAnonymeKlasse(){
        List<Task> list = Instancio.ofList(Task.class).size(100).create();

        list.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getCreated().compareTo(o2.getCreated());
            }
        });
        list.stream().limit(50).forEach(System.out::println);
    }

    @Test
    public void testSortComparatorChain1() {
        List<Task> list = Instancio.ofList(Task.class).size(100).create();

        list.sort(Task.comparatorByActiveThenCreated());
        list.stream().limit(50).forEach(System.out::println);
    }

    @Test
    public void testSortComparatorChain2() { // nur dammit alle Attribute verwendet werden
        List<Task> list = Instancio.ofList(Task.class).size(100).create();

        list.sort(Task.comparatorByTagCountThenName());
        list.stream().limit(50).forEach(System.out::println);
    }







    /*
    Code Auschnitt wie Java sortiert.

    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

    Ich habe mich durch navigiert bis zu dieser Funktion

    private static void legacyMergeSort(Object[] a) {
        Object[] aux = a.clone();
        mergeSort(aux, a, 0, a.length, 0);
    }

    hier sieht man das eine Copie erstellt wird und
    mit mergeSort schlussendlich der sortier Algorythmus
    eine neue version der List/Collection erstellt
    es handelt sich hier auch um eine Rekursive funktion

    Schluss Funktion:

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void mergeSort(Object[] src,
                                  Object[] dest,
                                  int low,
                                  int high,
                                  int off) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low &&
                         ((Comparable) dest[j-1]).compareTo(dest[j])>0; j--)
                    swap(dest, j, j-1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable)src[p]).compareTo(src[q])<=0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    **/
}
