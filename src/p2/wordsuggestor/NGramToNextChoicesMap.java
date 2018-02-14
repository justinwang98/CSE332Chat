package p2.wordsuggestor;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Supplier;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.Dictionary;
import cse332.misc.LargeValueFirstItemComparator;
import cse332.sorts.InsertionSort;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import p2.sorts.QuickSort;
import p2.sorts.TopKSort;

public class NGramToNextChoicesMap {
    private final Dictionary<NGram, Dictionary<AlphabeticString, Integer>> map;
    private final Supplier<Dictionary<AlphabeticString, Integer>> newInner;

    public NGramToNextChoicesMap(
            Supplier<Dictionary<NGram, Dictionary<AlphabeticString, Integer>>> newOuter,
            Supplier<Dictionary<AlphabeticString, Integer>> newInner) {
        this.map = newOuter.get();
        this.newInner = newInner;
    }

    /**
     * Increments the count of word after the particular NGram ngram.
     */
    public void seenWordAfterNGram(NGram ngram, String word) {
        AlphabeticString aWord = new AlphabeticString(word);
        if (map.find(ngram) == null) { // if ngram is not there
            map.insert(ngram, newInner.get()); // insert ngram
        }
        if (map.find(ngram).find(aWord) == null) { // word is not there
            map.find(ngram).insert(aWord, 1);
        } else { // word is there, then increment by one
            map.find(ngram).insert(aWord, map.find(ngram).find(aWord) + 1);
        }
    }

    /**
     * Returns an array of the DataCounts for this particular ngram. Order is
     * not specified.
     *
     * @param ngram
     *            the ngram we want the counts for
     * 
     * @return An array of all the Items for the requested ngram.
     */
    @SuppressWarnings("unchecked")
    public Item<String, Integer>[] getCountsAfter(NGram ngram) {
        if (map.find(ngram) == null) {
            return new Item[0];
        }
        Item<String, Integer>[] counts = new Item[map.find(ngram).size()]; //create array for size of ngram
        Iterator<Item<AlphabeticString, Integer>> iter = map.find(ngram).iterator();
        for (int i = 0; i < map.find(ngram).size(); i++) { //loop through the things
            Item<AlphabeticString, Integer> item = iter.next();
            counts[i] = new Item<String, Integer>(item.key.toString(), item.value);
        }
        return counts;
    }

    public String[] getWordsAfter(NGram ngram, int k) {
        Item<String, Integer>[] afterNGrams = getCountsAfter(ngram);

        Comparator<Item<String, Integer>> comp = new LargeValueFirstItemComparator<String, Integer>();
        if (k < 0) {
            QuickSort.sort(afterNGrams, comp);
        }
        else {
            TopKSort.sort(afterNGrams, k, comp);
        }

        String[] nextWords = new String[k < 0 ? afterNGrams.length : k];
        for (int l = 0; l < afterNGrams.length && l < nextWords.length
                && afterNGrams[l] != null; l++) {
            nextWords[l] = afterNGrams[l].key;
        }
        return nextWords;
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}
