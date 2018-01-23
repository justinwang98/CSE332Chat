 package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.FIFOWorkList;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    
	private ListNode front;
	private ListNode back;
	public int size;
	
	public class ListNode {
		
	    public E data;       // data stored in this node
	    public ListNode next;  // link to next node in the list

	    // post: constructs a node with data 0 and null link
	    public ListNode() {
	        this(null, null);
	    }

	    // post: constructs a node with given data and null link
	    public ListNode(E data) {
	        this(data, null);
	    }

	    // post: constructs a node with given data and given link
	    public ListNode(E data, ListNode next) {
	        this.data = data;
	        this.next = next;
	    }
	}
	
    public ListFIFOQueue() {
    	front = null;
    	back = null;
    	size = 0;
    }

    @Override
    public void add(E work) {
    	if (front == null) {
            front = new ListNode(work);
    		back = front;
	    } else {
	        back.next = new ListNode(work);
	        back = back.next;
        }
    	size++;
    }

    @Override
    public E peek() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
    	if (front == null) {
    		return null;
    	} else {
    		return front.data;
        }

    }

    @Override
    public E next() {
    	if (!this.hasWork()) {
    		throw new NoSuchElementException();
    	}
        if (front == null) {
    		return null;
        }
    	else {
            E item = front.data;
            front = front.next;
            size--;
            return item;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }
}
