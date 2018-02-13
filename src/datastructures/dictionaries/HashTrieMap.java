package datastructures.dictionaries;

import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;
import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.BString;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.trie.TrieMap;
import datastructures.dictionaries.HashTrieMap.HashTrieNode;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Dictionary<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }
        
        @SuppressWarnings("unchecked")
        public HashTrieNode(V value) {
            this.pointers = new ChainingHashTable<A, HashTrieNode>(() -> new MoveToFrontList());
            this.value = value;
        }
        
        private class SimpleEntryIterator implements Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> {
            private Iterator<Item<A, HashTrieMap<A, K, V>.HashTrieNode>> oldIter = pointers.iterator();
            
            public SimpleEntry<A, HashTrieNode> next() {
                Item<A, HashTrieMap<A, K, V>.HashTrieNode> item = oldIter.next();
                return new SimpleEntry<A, HashTrieNode>(item.key, item.value);
            }
            
            public boolean hasNext() {
                return oldIter.hasNext();
            }
        }
        
        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return new SimpleEntryIterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @SuppressWarnings("unchecked")
	@Override
    public V insert(K key, V value) {
    	if (key == null || value == null) {
    		throw new IllegalArgumentException();
    	}
    	size++;
    	HashTrieNode temp = (HashTrieNode)this.root;
    	Iterator<A> iter = key.iterator();
    	for (int i = 0; i < key.size(); i ++) {
    		A iterVal = iter.next();
    		if (temp.pointers.find(iterVal) == null) {
    			temp.pointers.insert(iterVal, new HashTrieNode());
    		}
    		temp = temp.pointers.find(iterVal);
    	}
    	V currentValue = temp.value;
		temp.value = value;
    	if (currentValue == null) {
    		return null;
    	}
    	return currentValue;
    }

    @Override
    public V find(K key) {
    	if (key == null) {
    		throw new IllegalArgumentException();
    	}
        HashTrieNode temp = search(key);
        if (temp == null) {
        	return null;
        }
        return temp.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
        	throw new IllegalArgumentException();
        }
        HashTrieNode temp = search(key);
        return (temp != null && (!temp.pointers.isEmpty() || temp.value != null));
    }
    
    @SuppressWarnings("unchecked")
	private HashTrieNode search(K key) {
    	HashTrieNode temp = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
    	Iterator<A> iter = key.iterator();
    	for (int i = 0; i < key.size(); i ++) {
    		A iterVal = iter.next();
    		if (temp.pointers.find(iterVal) == null) {
    			return null;
    		}
    		temp = temp.pointers.find(iterVal);
    	}
    	return temp;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void delete(K key) {
//    	if (key == null) {
//    		throw new IllegalArgumentException();
//    	}
//    	HashTrieNode temp = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
//    	Iterator<A> iter = key.iterator();
//    	HashTrieNode lastSplit = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
//    	A lastTrans = null;
//    	for (int i = 0; i < key.size(); i++) {
//    		if (temp == null) {
//    			return;
//    		}
//    		A iterVal = iter.next();
//    		if (temp.value != null || temp.pointers.size() > 1) {
//    			lastSplit = temp;
//    			lastTrans = iterVal;
//    		}
//    		temp = temp.pointers.find(iterVal);
//    	}
//    	if (temp != null && temp.value != null) {
//    		if (temp.pointers.isEmpty() && lastTrans != null) {
//    			lastSplit.pointers.remove(lastTrans);
//    			size--;
//    		} else if (temp.pointers.isEmpty() && lastTrans == null) {
//    			this.clear();
//    		} else {
//        		temp.value = null;
//    		}
//    	}
    }

    @Override
    public void clear() {
        this.root = new HashTrieNode();
        this.size = 0;
    }
}
