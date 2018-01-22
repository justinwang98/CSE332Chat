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
    private Node back;
    private Node currentNode;
    
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
    public V insert(K key, V value) {
        if (front == null) {
            front = new Node(new Item<K, V>(key,value));
            back = front;
            return front.data.value;
        } else {
            V temp = back.data.value;
            back.next = new Node(new Item<K, V>(key,value));
            back = back.next;
            return temp;
        }      
    }

    @Override
    public V find(K key) {
        Node pointer = front;
        while (pointer.data.key != key) {
            pointer = pointer.next;
        }
        front = pointer;
        currentNode.next = front;
        return front.data.value;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new Iterator<Item<K, V>>() {
            Node currentNode = null;
            @Override
            public boolean hasNext() {
                if (front == null) {
                    return false;
                }
                if (back.next == null) {
                    return false;
                }
                return true;
            }
            @Override
            public Item<K, V> next() {
                if (front == null){
                    throw new NoSuchElementException();
                } else if (currentNode == null) {
                    this.currentNode = front;
                    return currentNode.data;
                } else if (currentNode.next == null) {
                    throw new NoSuchElementException();
                }
                this.currentNode = currentNode.next;
                return currentNode.data;
            }
        };
    }  
}
