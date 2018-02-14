package hashExperiments;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.interfaces.misc.Dictionary;
import cse332.misc.WordReader;
import cse332.types.AlphabeticString;
import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import datastructures.dictionaries.MoveToFrontList;

public class GeneralPurposeDictionaryTest {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int NUM_TESTS = 200;
        int NUM_WARMUP = 20;
        
        double totalTime = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            
            long startTime = System.currentTimeMillis();
            
            // Put whatever you want to time here .....
            
            //HashTrieMapTest("alice.txt");
            //HashTrieMapTest("alice.txt");
            AVLTest("alice.txt");
            AVLTest("alice.txt");
            //BSTTest("alice.txt");
            //BSTTest("alice.txt");
            //ChainingHashTableTest("alice.txt");
            //ChainingHashTableTest("alice.txt");
           
            
            long endTime = System.currentTimeMillis();
            if (NUM_WARMUP <= i) { // Throw away first NUM_WARMUP runs to exclude JVM warmup
                totalTime += (endTime - startTime);
            }
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("averageRuntime = " + averageRuntime);

    }
    
    public static void BSTTest(String file) throws FileNotFoundException, IOException {

        BinarySearchTree<String, Integer> tree = new BinarySearchTree<>();
        WordReader reader = new WordReader(new FileReader(file));

        while (reader.hasNext()) {
            String key = reader.next();
            incCount(tree, key);
            incCount(tree, key);
        }
        
        reader.close(); 
    }
    
    public static void ChainingHashTableTest(String file) throws FileNotFoundException, IOException {
        
        ChainingHashTable<String, Integer> list = new ChainingHashTable<>(() -> new MoveToFrontList<>());
        
        WordReader reader = new WordReader(new FileReader(file));

        while (reader.hasNext()) {
            String key = reader.next();
            incCount(list, key);
            incCount(list, key);
        }
        
        reader.close(); 
        
    }
    
    public static void HashTrieMapTest(String file) throws FileNotFoundException, IOException {
        
        HashTrieMap<Character, AlphabeticString, Object> STUDENT = new HashTrieMap<>(AlphabeticString.class);
        
        WordReader reader = new WordReader(new FileReader(file));

        while (reader.hasNext()) {
            String key = reader.next();
            AlphabeticString key1 = new AlphabeticString(key);
            incCount2(STUDENT, key1);
            incCount2(STUDENT, key1);
        }
        
        reader.close(); 
        
    }  


    private static void incCount2(HashTrieMap<Character, AlphabeticString, Object> tree,
            AlphabeticString key) {
        
        Integer value = (Integer) tree.find(key);
        if (value == null) {
            tree.insert(key, 1);
        } else {
            tree.insert(key, value + 1);
        }
        
    }

    private static <E extends Comparable<E>> void incCount(Dictionary<E, Integer> tree, E key) {
        Integer value = tree.find(key);
        if (value == null) {
            tree.insert(key, 1);
        } else {
            tree.insert(key, value + 1);
        }
    }
    


    public static void AVLTest(String file) throws FileNotFoundException, IOException {
        
        AVLTree<String, Integer> tree = new AVLTree<>();
        WordReader reader = new WordReader(new FileReader(file));

        while (reader.hasNext()) {
            String key = reader.next();
            incCount(tree, key);
            incCount(tree, key);
        }
        
        reader.close();
    }
    
    
    
    
    
}
