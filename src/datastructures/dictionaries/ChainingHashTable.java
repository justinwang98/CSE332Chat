package datastructures.dictionaries;

import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.function.Supplier;

import cse332.datastructures.containers.*;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.misc.SimpleIterator;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. You must implement a generic chaining hashtable. You may not
 *    restrict the size of the input domain (i.e., it must accept 
 *    any key) or the number of inputs (i.e., it must grow as necessary).
 * 3. Your HashTable should rehash as appropriate (use load factor as
 *    shown in class).
 * 5. HashTable should be able to grow at least up to 200,000 elements. 
 * 6. We suggest you hard code some prime numbers. You can use this
 *    list: http://primes.utm.edu/lists/small/100000.txt 
 *    NOTE: Do NOT copy the whole list!
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private final Supplier<Dictionary<K, V>> NEWCHAIN;
    private Dictionary<K, V>[] hashTable;
    private int sizeTier;
    private static final int[] PRIMES = {23, 37, 53, 79, 127, 173, 257, 389, 577, 877, 1297, 
            1949, 2917, 4391, 6563, 9851, 14767, 22147, 33211, 
            49823, 74729, 112087, 168143, 200003};

    @SuppressWarnings("unchecked")
    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        NEWCHAIN = newChain;
        hashTable = new Dictionary[23];
        sizeTier = 0;
    }

    public int lambda() {
        return this.size/hashTable.length;
    }
    
    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (NEWCHAIN.get() == null) { // not sure if needed
            throw new NullPointerException();
        }
        if (lambda() > 0.75) { // check lambda is at acceptable lvls
            rehash();
        }
        int bucket = key.hashCode() % hashTable.length;
        if (hashTable[bucket] == null) { // no chain
            hashTable[bucket] = NEWCHAIN.get(); // creates new chain
            hashTable[bucket].insert(key, value);
            this.size++; //increase num of elements in table
            return null;
        } else { // chain
            V temp = hashTable[bucket].find(key);
            if (temp == null) {
                this.size++; //increase num of elements in table
            }
            hashTable[bucket].insert(key, value);
            return temp;
        }
    }
    
    // increases size of array to next available prime
    @SuppressWarnings("unchecked")
    private void rehash() {
        sizeTier += 1;
        int newSize = 0;
        if (sizeTier <= PRIMES.length - 1) { // below 200000
            newSize = PRIMES[sizeTier];
        } else { // above 200000
            newSize = 200000 * (int) (Math.pow(2, sizeTier - PRIMES.length));
        }
        Dictionary<K, V>[] temp = (Dictionary<K, V>[]) new Dictionary[newSize];
        Iterator<Item<K, V>> iter = this.iterator();
        while (iter.hasNext()) {
            Item<K, V> item = iter.next();
            temp[item.key.hashCode() % temp.length] = NEWCHAIN.get();
            temp[item.key.hashCode() % temp.length].insert(item.key, item.value);
        }
        hashTable = temp;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (hashTable[key.hashCode() % hashTable.length] == null) {
            return null;
        }
        return hashTable[key.hashCode() % hashTable.length].find(key);
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new HashIterator<Item<K, V>>();
    }
    
    public Iterator<SimpleEntry<K, V>> iteratorEntry() {
        return new HashIterator<SimpleEntry<K, V>>();
    }
    
    private class HashIterator<E> extends SimpleIterator<E>{
        private Dictionary<K, V> full;
        private Iterator<Item<K, V>> innerIter;
        
        public HashIterator() {
            full = NEWCHAIN.get();
            for (int i = 0; i < hashTable.length; i++) { // loop over array
                if (hashTable[i] != null) { // chain exists
                    Iterator<Item<K, V>> iter = hashTable[i].iterator();
                    for (int j = 0; j < hashTable[i].size(); j++) { // loop over chain inside array
                        Item<K, V> temp = iter.next();
                        full.insert(temp.key, temp.value);
                    }
                }   
            }
            innerIter = full.iterator();
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            return (E) innerIter.next();
        }

        @Override
        public boolean hasNext() {
            return innerIter.hasNext();
        }
    }
}
