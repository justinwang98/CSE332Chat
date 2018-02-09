package datastructures.dictionaries;

import java.lang.reflect.Array;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.datastructures.trees.BinarySearchTree.BSTNode;

/**
 * TODO: Replace this comment with your own as appropriate.
 *
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and calls to superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 *
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 *    instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 *    children array or left and right fields in AVLNode.  This will 
 *    instead mask the super-class fields (i.e., the resulting node 
 *    would actually have multiple copies of the node fields, with 
 *    code accessing one pair or the other depending on the type of 
 *    the references used to access the instance).  Such masking will 
 *    lead to highly perplexing and erroneous behavior. Instead, 
 *    continue using the existing BSTNode children array.
 * 4. If this class has redundant methods, your score will be heavily
 *    penalized.
 * 5. Cast children array to AVLNode whenever necessary in your
 *    AVLTree. This will result a lot of casts, so we recommend you make
 *    private methods that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 */

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V>  {
    
    public AVLTree() {
        super();
    }
    
    public class AVLNode extends BSTNode {
        
        protected int height;
        AVLNode parent;
        int balanceFactor;

        public AVLNode(K key, V value, AVLNode parent) {
            super(key, value);
            height = 0;
            this.parent = parent; 
        }  
    }

    public int height(AVLNode node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }
    

    public void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = Math.max(height((AVLNode) node.children[0]), height((AVLNode) node.children[1])) + 1;
        }
    }
    
    public void setBalance(AVLNode node) {
            updateHeight(node);
            node.balanceFactor = height((AVLNode) node.children[0]) - height((AVLNode) node.children[1]);
    }
    
    public void doRotation(AVLNode node) {
        setBalance(node);
        
        //left subtree is taller
        if (node.balanceFactor == -2) {
            //single rightRotation
            if (height((AVLNode) node.children[1].children[1]) >= height((AVLNode) node.children[1].children[0])) {
                node = leftRotation(node);
            }
            else {
                node = rightLeftRotation(node);
            }
        }
        
        //right subtree is taller
        if (node.balanceFactor == 2) {
            //single leftRotation
            if (height((AVLNode) node.children[0].children[0]) >= height((AVLNode) node.children[0].children[1])) {
                node = rightRotation(node);
            }
            else {
                node = leftRightRotation(node);
            }
        }
        
        if (node.parent != null) {
            doRotation(node.parent);
        }
        else {
            this.root = node;
        }
    }
    
    @Override 
    public V insert(K key, V value) {
        
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
           
        if (this.root == null) {
            this.root = new AVLNode(key, value, null);
            this.size++;
        }
        else {
            AVLNode current = (AVLNode) this.root;
            while (current.key != null) {
                
                if(current.key.equals(key)) {
                    current.value = value;
                    break;
                }
                
                AVLNode previous = current;
                
                boolean directionLeft = current.key.compareTo(key) > 0;
                if (directionLeft) {
                    current = (AVLNode) current.children[0];
                }
                else {
                    current = (AVLNode) current.children[1];
                }
                
                if (current == null) {
                    if (directionLeft) {
                        previous.children[0] = new AVLNode(key, value, previous);
                        this.size++;
                    }
                    else {
                        previous.children[1] = new AVLNode(key, value, previous);
                        this.size++;
                    }
                    doRotation(previous);
                    break;
                }
            }
        }
        return value;
    }
            
            
            
    
    //left subtree of left child
    public AVLNode leftRotation(AVLNode node) {
        
        AVLNode newParent = (AVLNode) node.children[1];
        newParent.parent = node.parent;
        
        node.children[1] = (AVLNode) newParent.children[0];
        
        if ((AVLNode) node.children[1] != null) {
            AVLNode temp = (AVLNode) node.children[1];
            temp.parent = node;
        }
        
        newParent.children[0] = node;
        node.parent = newParent;
        
        if (newParent.parent != null) {
            if (newParent.parent.children[1].equals(node)) {
                newParent.parent.children[1] = newParent;
            }
            else {
                newParent.parent.children[0] = newParent;
            }
        }
        
        setBalance(node);
        setBalance(newParent);
        
        return newParent;
    }
    
    //right subtree of right child
    public AVLNode rightRotation(AVLNode node) {
        
        AVLNode newParent = (AVLNode) node.children[0];
        newParent.parent = node.parent;
        
        node.children[0] = (AVLNode) newParent.children[1];
        
        if ((AVLNode) node.children[0] != null) {
            AVLNode temp = (AVLNode) node.children[0];
            temp.parent = node;
        }
        
        newParent.children[1] = node;
        node.parent = newParent;
        
        if (newParent.parent != null) {
            if (newParent.parent.children[1].equals(node)) {
                newParent.parent.children[1] = newParent;
            }
            else {
                newParent.parent.children[0] = newParent;
            }
        }
        
        setBalance(node);
        setBalance(newParent);
       
        return newParent;
    }
    
    //right subtree of left child
    public AVLNode rightLeftRotation(AVLNode node) {
        node.children[1] = rightRotation((AVLNode) node.children[1]);
        return leftRotation(node);
    }
    
    //right subtree of left child
    public AVLNode leftRightRotation(AVLNode node) {
        node.children[0] = leftRotation((AVLNode) node.children[0]);
        return rightRotation(node);
    }
    
}
