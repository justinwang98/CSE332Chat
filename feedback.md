# Project 2 (uMessage) Feedback #
## CSE 332 Winter 2018 ##

**Team:** Justin Wang (jwang98) and Leonhardsen Chandra (leon28) <br />
**Graded By:** Ollin (ollin@cs.washington.edu)
<br>

## Unit Tests ##

**AVLTree**  `(6/6)`
> ✓ Passed *initialize* <br>
> ✓ Passed *insert* <br>
> ✓ Passed *getters* <br>
> ✓ Passed *sorted_duplicate_input* <br>
> ✓ Passed *unsorted_duplicate_input* <br>
> ✓ Passed *structure* <br>

**MoveToFrontList**  `(6/6)`
> ✓ Passed *initialize* <br>
> ✓ Passed *insert* <br>
> ✓ Passed *getters* <br>
> ✓ Passed *sorted_duplicate_input* <br>
> ✓ Passed *unsorted_duplicate_input* <br>
> ✓ Passed *structure* <br>

**HashTable**  `(6/6)`
> ✓ Passed *initialize* <br>
> ✓ Passed *insert* <br>
> ✓ Passed *getters* <br>
> ✓ Passed *sorted_duplicate_input* <br>
> ✓ Passed *unsorted_duplicate_input* <br>
> ✓ Passed *comparator* <br>
> ✓ Passed *negative_hash_codes* <br>

**HeapSort**  `(5/5)`
> ✓ Passed *integer_inorder* <br>
> ✓ Passed *integer_reverseorder* <br>
> ✓ Passed *integer_interleaved* <br>
> ✓ Passed *integer_random* <br>
> ✓ Passed *string* <br>
> ✓ Passed *dataCount_string* <br>

**QuickSort**  `(5/5)`
> ✓ Passed *integer_inorder* <br>
> ✓ Passed *integer_reverseorder* <br>
> ✓ Passed *integer_interleaved* <br>
> ✓ Passed *integer_random* <br>
> ✓ Passed *string* <br>
> ✓ Passed *dataCount_string* <br>

**TopKSort**  `(6/6)`
> ✓ Passed *integer_random_100* <br>
> ✓ Passed *string_20* <br>
> ✓ Passed *dataCount_ngram_counts_inorder* <br>
> ✓ Passed *dataCount_ngram_counts_reverseorder* <br>
> ✓ Passed *dataCount_ngram_counts_interleaved* <br>
> ✓ Passed *dataCount_ngram_counts_random* <br>

**CircularArrayHashCode**  `(1/2)`
> ✓ Passed *generate_hash_codes* <br>
> `✘ Failed` *hash_overlap* <br>
> ✓ Passed *high_variance* <br>
> `✘ Failed` *with_null_chars* <br>

**CircularArrayComparator**  `(2/2)`
> ✓ Passed *vary_length* <br>
> ✓ Passed *vary_order* <br>

**NGramToNextChoicesMap** ✝ Exception `(1/2)`
> ✓ Passed *poem* <br>
> `✝ Exception` *large* <br>
> `.lang.IndexOutOfBoundsException` *java* <br>

**UMessage** ✝ Exception `(1/6)`
> `✝ Exception` *simple_HashTable_AVL* <br>
> `✝ Exception` *simple_HashTable_HashTable* <br>
> ✓ Passed *simple_HashTable_AVL_topk* <br>
> ✓ Passed *simple_HashTable_HashTable_topk* <br>
> `✝ Exception` *poem_HashTable_AVL* <br>
> `✝ Exception` *poem_HashTable_HashTable* <br>
> `✝ Exception` *poem_HashTable_MTF* <br>
> `✘ Failed` *poem_HashTable_AVL_topk* <br>
> `✘ Failed` *poem_HashTable_HashTable_topk* <br>
> `✘ Failed` *poem_HashTable_MTF_topk* <br>
> `✝ Exception` *large_HashTable_AVL_topk* <br>
> `✝ Exception` *large_HashTable_HashTable_topk* <br>

## Miscellaneous ##











## Write-Up ##

**Project Enjoyment**
`(3/3)`
It's good to hear you enjoyed this project!  Debugging can be tricky; the suggestions on the debugging handout can be helpful, but it also takes practice... I'm glad the visualizer was some help (it's usually really useful for AVLTree).

**BST vs. AVLTree**
`(4/4)`
Looks good! It would be good to mention a bit more about your experimental procedure (that you're using averaging; that you're discarding trials to account for JVM warmup, etc.)

**ChainingHashTable**
`(2/3)`
Your runtime analysis isn't quite reasonable (MTFList insert isn't constant time because of duplicate-checking, and none of those should be asymptotically exponential...)

**Hash Functions**
`(0/3)`
The question asks you to show how big a difference a hash function can make on the average-case runtime.  Although you showed a difference, your modified hash function was still similar enough to the good one (for the data you tested) that it doesn't really show the difference between the expected constant (good hash) and log/linear (bad hash) average-time performance.

**General Purpose Dictionary**
`(3/3)`
Nicely done! It would have been a bit nicer to do an asymptotic analysis by varying the size of the input (proportion of the file inserted, for example).

**uMessage**
`(3/3)`
Congratulations on getting uMessage working! And Ubuntu is a common linux distribution-exactly the sort of thing that people using IRC usually chat about :)

**Above & Beyond**
`(EX: 0)`
