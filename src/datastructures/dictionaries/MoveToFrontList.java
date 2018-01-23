package datastructures.dictionaries;

import java.util.Iterator;
import java.util.NoSuchElementException;

import cse332.datastructures.containers.*;
import cse332.datastructures.trees.BinarySearchTree;
import cse332.datastructures.trees.BinarySearchTree.BSTNode;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.SimpleIterator;
import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.ArrayStack;
import datastructures.worklists.ListFIFOQueue.ListNode;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. The list is typically not sorted.
 * 2. Add new items to the front oft he list.
 * 3. Whenever find is called on an item, move it to the front of the 
 *    list. This means you remove the node from its current position 
 *    and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 *    elements to the front.  The iterator should return elements in
 *    the order they are stored in the list, starting with the first
 *    element in the list.
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {
    
    private Node front;
    
    //list node class
    public class Node {
            

            public Item<K,V> data;       // data stored in this node
            public Node next;  // link to next node in the list
    
            // post: constructs a node with given data and null link
            public Node(Item<K,V> data) {
                this(data, null);
            }
    
            // post: constructs a node with given data and given link
            public Node(Item<K,V> data, Node next) {
                this.data = data;
                this.next = next;
            }
    }
    
    @Override
    //insert a new node at the front.
    //if key already exists, update the value and move it to the front.
    public V insert(K key, V value) {
        //put in data in new node
        Node newNode = new Node(new Item<K, V>(key, value));
        //create pointer
        Node previousPointer = front;
        Node currentPointer = front;
        //if list is empty, just insert the new node.
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (front == null) {
            front = newNode;
            return null;
        }
        else {
            //check whether it exists.
            while (!currentPointer.data.key.equals(key)) {
                //if next is null, that means key does not exist, so just insert to the front
                if (currentPointer.next == null) {
                    newNode.next = front;
                    front = newNode;
                    return value;
                }
                previousPointer = currentPointer;
                currentPointer = currentPointer.next;
            }
            //if while loop ends, that means the key exist
            //move it to the front
            V tempValue = currentPointer.data.value;
            newNode.next = front;
            front = newNode;
            //connect the hole left by the Item
            previousPointer.next = currentPointer.next;
            return tempValue;
        }
    }

    @Override
    /**
     * Returns the value to which the specified key is mapped, or {@code null}
     * if this map contains no mapping for the key.
     *
     * @param key
     *            the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null}
     *         if this map contains no mapping for the key
     * @throws IllegalArgumentException
     *             if key is null.
     */
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (front == null) {
            return null;
        }
        Node previousPointer = front;
        Node currentPointer = front;
        while (!currentPointer.data.key.equals(key)) {
            if (currentPointer.next == null) {
                return null;
            }
            previousPointer = currentPointer;
            currentPointer = currentPointer.next;
        }
        Node temp = currentPointer;
        temp.next = front;
        front = temp;
        //connect the hole left by the Item
        previousPointer.next = currentPointer.next;
        return front.data.value;
            
    }
    
    public Iterator<Item<K, V>> iterator() {
        return new Iterator<Item<K, V>>() {
            Node iterate = null;
            
            @Override
            public boolean hasNext() {
                return iterate != null;
            }
            @Override
            public Item<K, V> next() {
                if (iterate == null) {
                    iterate = front;
                    return iterate.data;
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item<K, V> item = new Item<K,V>(iterate.data.key, iterate.data.value);
                iterate = iterate.next;
                return item;
            }
        };
    }  
}
