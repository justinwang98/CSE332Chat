package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
	private E[] array;
	private int end;
	
    @SuppressWarnings("unchecked")
	public ArrayStack() {
    	array = (E[])new Object[10];
    	end = 0;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void add(E work) {
    	if (end == array.length) { // if size is max
    		E[] array2 = (E[])new Object[array.length * 2]; // make new array with double size
    		for (int i = 0; i < array.length; i++) { // add in all the old elements to new array
    			array2[i] = array[i];
    		}
    		array = array2; // make old array point to new array
    	}
        array[end] = work;
        end++;
    }

    @Override
    public E peek() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
    	return array[end - 1];
    }

    @Override
    public E next() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
        E temp = array[end - 1];
        end--;
        return temp;
    }

    @Override
    public int size() {
        return end;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void clear() {
    	array = (E[])new Object[10];
    	end = 0;
    }
}
