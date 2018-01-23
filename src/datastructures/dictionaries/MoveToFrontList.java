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
    public V insert(K key, V value) {
        //put in data in new node
        Node newNode = new Node(new Item<K, V>(key, value));
        if (front == null) {
            front = newNode;
            return front.data.value;
        }
        else {
            Node pointer = front;
            boolean isExist = true;
            while (!pointer.data.key.equals(key)) {
                if (pointer.next == null) {
                    isExist = false;
                    break;
                }
                pointer = pointer.next;
            }
            
            if (isExist) {
                V temp = pointer.data.value;
                
                return temp;
            }
            else {
                newNode.next = front;
                front = newNode;
                return front.data.value;
            } 
        }
    }

    @Override
    public V find(K key) {
        Node previous = front;
        Node current = front;
        while(current != null){
            if(key.equals(current.data.key)){
                //Found the item
                previous.next=current.next;
                current.next=front;
                front=current;
                return front.data.value;
            }
            previous = current;
            current=current.next;
        }
        return null;
    
        /*Node pointer = front;
        while (!pointer.data.key.equals(key)) {
            if (pointer.next == null) {
                return null;
            }
            pointer = pointer.next;
        }
        pointer.next = front;
        front = pointer;
        return front.data.value;*/
        
//        if (front == null || key == null) {
//            return null;
//        }
//        
//        Node pointerFirst = null;
//        Node pointerSecond = front;
//        //keep moving through the list if you have not found the key
//        while (!(pointerSecond.data.key.equals(key))) {
//            //make sure list has next
//            if (pointerSecond.next == null) {
//                return null;
//            }
//            
//            pointerFirst = pointerSecond;
//            pointerSecond = pointerSecond.next;
//        }
//        
//        Node temp = pointerSecond;
//        //connect the two nodes together
//        pointerFirst.next = pointerSecond.next;
//        //move the node to the front
//        temp.next = front;
//        front = temp;
//        return front.data.value;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new Iterator<Item<K, V>>() {
            Node iterate = front;
            
            @Override
            public boolean hasNext() {
                return iterate != null;
            }
            @Override
            public Item<K, V> next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                Item<K, V> item = new Item<K,V>(iterate.data.key, iterate.data.value);
                iterate = iterate.next;
                return item;
            }
        };
    }  
}
