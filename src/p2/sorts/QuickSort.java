package p2.sorts;

import java.util.Comparator;

import cse332.exceptions.NotYetImplementedException;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    //normal swap function
    public static <E> void swap(E[] array, int i, int j) {
       E temp = array[i];
       array[i] = array[j];
       array[j] = temp;
    }
    
    public static <E> void sort(E[] array, Comparator<E> comparator) {
        
        int size = array.length;
        int lo = 0;
        int hi = size - 1;
        
        quicksort(array, lo, hi, comparator);
    }
    
    public static <E> void quicksort(E[] array, int lo, int hi, Comparator<E> comparator) {
        
        
        int median = (lo + hi) / 2;
        
        E pivot = array[median];
        
        //setting two pointers on each end
        int left = lo;
        int right = hi;
        
        //while the pointers have not met, keep moving them until you need to swap
        while (left <= right) {
            
            //keep moving the left pointer to the right if it's smaller than pivot (which means it is at the correct place
            if (comparator.compare(array[left], pivot) < 0) {
                left++;
            }
            
            //keep moving the right pointer to the left if it's bigger than pivot (which means it is at the correct place
            else if (comparator.compare(array[right], pivot) > 0) {
                right--;
            }
            //if there's a problem, swap the two.
            else {
                swap(array, left, right);
                //keep on moving
                left++;
                right--;
            }
            
        }
        
        //set up recursion
        if (lo < right) {
            quicksort(array, lo, right, comparator);
        }
        
        if (left < hi) {
            quicksort(array, left, hi, comparator);
        }
       
    }
}
        

 
    
    

