package hashExperiments;

import cse332.datastructures.containers.Item;
import cse332.datastructures.trees.BinarySearchTree;
import cse332.interfaces.misc.Dictionary;
import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.MoveToFrontList;

public class ChainingHashTableExp {
    public static int n = 0;
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (n < 300) {
                n += 100;
            } else if (n < 500) {
                n += 200;
            } else if (n < 800) {
                n += 300;
            } else if (n < 1500) {
                n += 700;
            } else if (n < 3000) {
                n += 1500;
            } else if (n < 7000) {
                n += 2000;
            } else {
                n += 3000;
            }
            fulltest();
        }
    }

    public static void fulltest() {
        final int NUM_TESTS = 10;
        final int NUM_WARMUP = 3;
        
        double totalTime = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            long startTime = System.currentTimeMillis();
            // Put whatever you want to time here .....
            test("avl");
            long endTime = System.currentTimeMillis();
            if (NUM_WARMUP <= i) { // Throw away first NUM_WARMUP runs to exclude JVM warmup
                totalTime += (endTime - startTime);
            }
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("n = " + n + " and average time is: " + averageRuntime);
    }
    
    private static void incCount(Dictionary<String, Integer> list, String key) {
        Integer find = list.find(key);
        if (find == null)
            list.insert(key, 1);
        else
            list.insert(key, 1 + find);
    }
    public static int test(String type) {
        ChainingHashTable<String, Integer> list;
        if (type.equals("mtf")) { // mtf list
            list = new ChainingHashTable<>(() -> new MoveToFrontList<>());
        } else if (type.equals("bst")) { // bst list
            list = new ChainingHashTable<String, Integer>(() -> new BinarySearchTree<>());
        } else if (type.equals("avl")){ // avl list
            list = new ChainingHashTable<String, Integer>(() -> new MoveToFrontList<>());
        } else {
            throw new IllegalArgumentException();
        }
        
        // Add them
        for (int i = 0; i < 5 * n; i++) {
            int k = (i % n) * 37 % n;
            String str = String.format("%05d", k);
            for (int j = 0; j < k + 1; j ++)
                incCount(list, str);
        }

        // Delete them all
        boolean passed = true;
        int totalCount = 0;
        for (Item<String, Integer> dc : list) {
            passed &= (Integer.parseInt(dc.key) + 1) * 5 == dc.value;
//          System.out.println(((Integer.parseInt(dc.data) + 1) * 5 == dc.count) + ": " + ((Integer.parseInt(dc.data) + 1) * 5) + "==" + dc.count);
            totalCount += dc.value;
        }

        passed &= totalCount == (n * (n + 1)) / 2 * 5;
        passed &= list.size() == n;
        
        return passed ? 1 : 0;
    }
}
