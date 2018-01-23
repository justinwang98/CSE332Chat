package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.PriorityWorkList;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeap<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int size;
    
	@SuppressWarnings("unchecked")
	public MinFourHeap() {
    	data = (E[])new Comparable[10];
    	size = 0;
    }

    @Override
    public boolean hasWork() {
    	return this.size() > 0;
    }

    @SuppressWarnings("unchecked")
	@Override
    //insert
    public void add(E work) {
    	if (size == data.length) { // if size is max
    		E[] data2 = (E[]) new Comparable[data.length * 2]; // make new array with double size
    		for (int i = 0; i < data.length; i++) { // add in all the old elements to new array
    			data2[i] = data[i];
    		}
    		data = data2; // make old array point to new array
    	}
    	
    	//insert here
    	size++;
    	int i = percolateUp(size - 1, work);
    	data[i] = work;
    }

    @Override
    public E peek() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
        return data[0];
    }

    @Override
    //delete min
    public E next() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
    	E min = data[0];
    	int hole = percolateDown(0, data[size - 1]); 
    	data[hole] = data[size - 1];
    	size--;
        return min;
    }
    

    @Override
    public int size() {
    	return size;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void clear() {
    	data = (E[])new Comparable[10];
    	size = 0;
    }
    
    private int percolateUp(int hole, E work) {	
    	//while node is not the top most node and priority value of node is less than its parent;
    	while (hole > 0 && work.compareTo(data[(hole - 1) / 4]) < 0) {
    		data[hole] = data[(hole - 1) / 4];
    		hole = (hole - 1) / 4;
    	}
    	return hole;
    }
    
    private int percolateDown(int hole, E work) {
    	
    	//while hole has leaves (exactly half of total nodes are nodes with leaves)
    	while (4 * hole + 1 <= size - 1) {
    	int min = 4 * hole + 1;
  		int small = 4 * hole + 1;
    		for (int i = small; i < small + 4 && i < size; i++) {
    			if (data[i].compareTo(data[min]) < 0) {
    				min = i;
    			}
    		}
    		
    		if (data[min].compareTo(work) < 0) {
    			data[hole] = data[min];
    			hole = min;
    		} else {
    			break;
    		}    		
    	}
    	return hole;
    }
}
