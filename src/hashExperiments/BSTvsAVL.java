package hashExperiments;

import cse332.datastructures.containers.Item;
import cse332.datastructures.trees.BinarySearchTree;
import cse332.datastructures.trees.BinarySearchTree.BSTNode;
import cse332.interfaces.misc.Dictionary;
import datastructures.dictionaries.AVLTree;
import tests.TestsUtility;

public class BSTvsAVL {
    
    

        
        public static void main(String[] args) {
            int NUM_TESTS = 10;
            int NUM_WARMUP = 3;
            int n = 5000;
            
            double totalTime = 0;
            for (int i = 0; i < NUM_TESTS; i++) {
                
                long startTime = System.currentTimeMillis();
                
                // Put whatever you want to time here .....
                AVLTest(n);
                
                long endTime = System.currentTimeMillis();
                if (NUM_WARMUP <= i) { // Throw away first NUM_WARMUP runs to exclude JVM warmup
                    totalTime += (endTime - startTime);
                }
            }
            double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
            System.out.println("averageRuntime for n =  " + n + " , is " +  averageRuntime);

        }
        
        
        public static void BSTTest(int n) {

            BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
            for (int i = 0; i < n; i++)
                incCount(tree, i);   
        }

        public static void AVLTest(int n) {
            
            AVLTree<Integer, Integer> tree = new AVLTree<>();
            for (int i = 0; i < n; i++)
            incCount(tree, i);
        }
        
        protected static BinarySearchTree<String, Integer> init2() {
            BinarySearchTree<String, Integer> tree = new BinarySearchTree<>();

            return tree;
        }
        
        protected static AVLTree<String, Integer> init() {
            AVLTree<String, Integer> tree = new AVLTree<>();

            return tree;
        }
        
        @SuppressWarnings("rawtypes")
        public static String nestd(BSTNode root) {
            if(root == null)
                return ".";
            return " [" + root.key + nestd(root.children[0]) + nestd(root.children[1]) + "]";
        }
        @SuppressWarnings("rawtypes")
        public static String nestc(BSTNode root) {
            if(root == null)
                return ".";
            return " [" + root.value + nestc(root.children[0]) + nestc(root.children[1]) + "]";
        }
        
        private static <E extends Comparable<E>> void incCount(Dictionary<E, Integer> tree, E key) {
            Integer value = tree.find(key);
            if (value == null) {
                tree.insert(key, 1);
            } else {
                tree.insert(key, value + 1);
            }
        }
    }


