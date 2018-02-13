package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {
	private E[] array;
	private int end;
	private int start;
	private int size;
	
    @SuppressWarnings("unchecked")
	public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        array = (E[])new Comparable[capacity];
        end = 0;
        start = 0;
        size = 0;
    }

    @Override
    public void add(E work) {
        if (this.isFull()) {
        	throw new IllegalStateException();
        }
        array[end] = work;
        size++;
        end = (end + 1) % capacity();
    }

    @Override
    public E peek() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
    	return array[start];
    }
    
    @Override
    public E peek(int i) {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
    	if (i < 0 || i >= size()) {
    		throw new IndexOutOfBoundsException();
    	}
    	return array[(i + start) % capacity()];
    }
    
    @Override
    public E next() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
    	E temp = array[start]; // saves first element value
    	start = (start + 1) % capacity();
    	size--;
    	return temp; // returns first element value;
    }
    
    @Override
    public void update(int i, E value) {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
    	if (i < 0 || i >= size()) {
    		throw new IndexOutOfBoundsException();
    	}
    	array[(i + start) % capacity()] = value;
    }
    
    @Override
    public int size() {
    	return size;
    }
    
    @Override
    public void clear() {
        end = 0;
        start = 0;
        size = 0;
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        //check which object is bigger element by element, then check for size after if both are equal
        int smallerSize = Math.min(this.size(), other.size());
        for (int i = 0; i < smallerSize; i++) {
            if (this.peek(i).compareTo(other.peek(i)) > 0) {
                return 1;
            }
            else if (this.peek(i).compareTo(other.peek(i)) < 0) {
                return -1;
            }
        }
        
        if (this.size() > other.size()) {
            return 1;
        }
        else if (this.size() < other.size()){
            return -1;
        }
        return 0;
       
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;
            //check element by element for any difference
            for (int i = 0; i < this.size(); i++) {
                if (this.peek(i).compareTo(other.peek(i)) != 0) {
                    return false;
                }   
            }
            return true;

        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (int i = 0; i < size; i ++) {
            int fieldHashcode = array[i].hashCode();
            result = 31 * result + fieldHashcode;
        }
        return result;
    }
}
