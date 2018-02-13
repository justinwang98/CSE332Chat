package p2.sorts;

import java.util.Comparator;
import cse332.exceptions.NotYetImplementedException;
import datastructures.worklists.MinFourHeap;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<E>(comparator);
        for (int i = 0; i < array.length; i++) {
            if (heap.size() >= k) { // full
                if (comparator.compare(heap.peek(), array[i]) < 0) { // next array value is bigger
                    heap.next();
                    heap.add(array[i]);
                }
            } else { // not full
                heap.add(array[i]);
            }
        }
        // save max size
        int maxSize = heap.size();
        for (int i = 0; i < array.length; i++) {
            if (i < maxSize) { // from i to maxSize - 1, put the heap values into array
                array[i] = heap.next();
            } else { // otherwise put null
                array[i] = null;
            }
        }
    }
}
