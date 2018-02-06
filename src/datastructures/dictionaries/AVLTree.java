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

        public AVLNode(K key, V value) {
            super(key, value);
            height = 0;
        }  

    }
    
    //returns the height of a node using recursion
    public int height(AVLNode node)
    {

        if (node == null) {
            return -1;
        }
        //returns the height of the parent, which is 1 greater than the higher subtree
        return Math.max(height((AVLNode) node.children[0]), height((AVLNode) node.children[1])) + 1;
    }
    
    protected AVLNode find(K key, V value) {
        AVLNode prev = null;
        AVLNode current = (AVLNode) this.root;

        int child = -1;

        while (current != null) {
            int direction = Integer.signum(key.compareTo(current.key));

            // We found the key!
            if (direction == 0) {
                return current;
            }
            else {
                // direction + 1 = {0, 2} -> {0, 1}
                child = Integer.signum(direction + 1);
                prev = current;
                current = (AVLNode) current.children[child];
            }
        }
        
        //if key does not exist, we have to insert the node
        if (value != null) {
            current = new AVLNode(key, value);
            if (this.root == null) {
                this.root = current;
            }
            else {
                assert(child >= 0); // child should have been set in the loop
                                    // above
                prev.children[child] = current;
            }
            this.size++;
            
        }
        //return the inserted node
        return current;
    }
    
    @Override 
    public V insert(K key, V value) {
        
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
           
        AVLNode insertedNode = find(key,value);
        V oldValue = insertedNode.value;
        insertedNode.value = value;
        AVLNode problematicNode = verifyAVLBalance((AVLNode)this.root);
        
        if (problematicNode == null) {
            return value;
        }
        else {
          //if left subtree is taller than right subtree
            if (height((AVLNode) problematicNode.children[0]) > height((AVLNode) problematicNode.children[1])) {
                
                //if left sub-subtree is taller than right sub-subtree
                if (height((AVLNode) problematicNode.children[0].children[0]) > height((AVLNode) problematicNode.children[0].children[1])) {
                    rightRotation(problematicNode);
                }
                
                //if right sub-subtree is taller than left sub-subtree
                else {
                    leftRightRotation(problematicNode);
                }
                
            }
            // if right subtree is taller than left subtree
            else {
                
                //if right sub-subtree is taller than left sub-subtree
                if (height((AVLNode) problematicNode.children[1].children[1]) > height((AVLNode) problematicNode.children[1].children[0])) {
                    leftRotation(problematicNode);
                }
                
                //if left sub-subtree is taller than right sub-subtree
                else {
                    rightLeftRotation(problematicNode);
                }                 
            }
        
        return value;    
        }
    }
            
            
            
    
    //find the problematic node
    //check AVL balance condition: height difference between left and right subtree cannot be greater than 1.
    public AVLNode verifyAVLBalance(AVLNode node) {
        
        if (node != null) {
            AVLNode previous = null;
            while (Math.abs(height((AVLNode)node.children[0]) - height((AVLNode)node.children[1])) > 1) {
                
                if (height((AVLNode)node.children[0]) > height((AVLNode)node.children[1])) {
                    previous = node;
                    node = (AVLNode)node.children[0];
                }
                else {
                    previous = node;
                    node = (AVLNode)node.children[1];
                }
            }
            return previous;
        }
        return null;
    }
    
    //left subtree of left child
    public AVLNode rightRotation(AVLNode node) {
        
        
        AVLNode newParent = (AVLNode) node.children[0];
        AVLNode temp = (AVLNode) newParent.children[1];
        
        if (this.root.equals(node)) {
            this.root = newParent;
            
        }
        
        newParent.children[1] = node;
        node.children[0] = temp;
        
        node.height = Math.max(height((AVLNode) node.children[0]), height((AVLNode) node.children[1])) + 1;
        newParent.height = Math.max(height((AVLNode) newParent.children[0]), height((AVLNode) newParent.children[1])) + 1;
        
        return newParent;
    }
    
    //right subtree of right child
    public AVLNode leftRotation(AVLNode node) {
        
        AVLNode newParent = (AVLNode) node.children[1];
        AVLNode temp = (AVLNode) newParent.children[0];
        
        if (this.root.equals(node)) {
            this.root = newParent;
            
        }
        
        newParent.children[0] = node;
        node.children[1] = temp;
        
        node.height = Math.max(height((AVLNode) node.children[0]), height((AVLNode) node.children[1])) + 1;
        newParent.height = Math.max(height((AVLNode) newParent.children[0]), height((AVLNode) newParent.children[1])) + 1;
        
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
        
        //traverse the AVLTree to find where the node should go,
        //insert the node there
        //verifyAVL balance to find out which node the problem is at
        //if not balanced, determine which rotation case it is by finding which subtree the inserted node is in
        //call the helper method for the specific rotation case
        
        //think about it slowly,
        //changing of pointers
        //for kinks, you have to move nodes, so use a temp
        
        /*
         * Helper methods:
         * 
         * 1) height(node) : to return height of the node
         * 2) verifyBalance(root) : similar to Ex08, check whether balance factor of a node is more than 1
         * 3) rotateLeft(node) : case 1 rotation
         * 4) rotateLeftRight(node) : case 2 rotation, just call rotateLeft then rotateRight
         * 5) rotateRightLeft(node) : case 3 rotation, just call rotateRight then rotateLeft
         * 4) rotateRight(node) : case 4 rotation
         */
    
}
