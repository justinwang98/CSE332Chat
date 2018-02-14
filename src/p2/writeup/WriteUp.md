# Project 2 (uMessage) Write-Up #
--------

## Project Enjoyment ##
- What was your favorite part of the project? Why?
  
Our favorite part was using the knowledge we got in class to implement the different data
structures and sorts. It was really cool to have the feeling that the things we learnt in class
was important and useful.

- What was your least favorite part of the project? Why?
  
Debugging AVLTree and quicksort was a nightmare, each in its own way. Having learnt how to use
the visualizer recently, it helped me out alot in the debugging process, being able to locate 
the problem visually. However, it was really hard to keep track of all the changing of pointers
and the heavy use of recursion in both parts made debugging really confusing and tedious.

- How could the project be improved? Why?
- 
  This project felt a little rushed, especially considering the fact that there were midterms in
between. I know the two weeks before checkpoint 2 already took this into account, but nonetheless
it ultimately still was not enough to complete checkpoint 2, especially because of the long debugging
process for AVLTree.

- Did you enjoy the project?  Why or why not?
- 
  Yes, I enjoyed this project a lot, even more so than project 1. This idea of building uMessage was really exciting,
and implementing the data structures and sorts was a blast. It felt great to use all our knowledge from class and the last
six weeks to make something that is relevant in today's world.
    
-----

## Experiments ##
Throughout p1 and p2, you have written (or used) several distinct implementations of the Dictionary interface:
 - HashTrieMap 
 - MoveToFrontList
 - BinarySearchTree
 - AVLTree
 - ChainingHashTable
 
 In this Write-Up, you will compare various aspects of these data structures.  This will take a significant amount of
 time, and you should not leave it to the last minute.  For each experiment, we expect you to:
 - Explain how you constructed the inputs to make your conclusions
 - Explain why your data supports your conclusions
 - Explain your methodology (e.g., if we wanted to re-run your experiment, we would be able to)
 - Include the inputs themselves in the experiments folder
 - Include your data either directly in the write-up or in the experiments folder
 - You should include graphs of the outputs for at least a few of the questions.
   You can add a link to an image following the instructions found here:
https://courses.cs.washington.edu/courses/cse332/18wi/handouts/markdown.pdf
An example can also be found at the end of this file.
 - We recommend that you keep your "N" (as in "N-gram") constant throughout these experiments. (N = 2 and N = 3 are reasonable.) 
 - You should probably run multiple trials for each data point to help remove outliers.
 - You should not need to wait for hours and hours for your program to run in
 order to answer a question.  Do use large values for N, but not so large that 
 you are waiting overnight for your program to run (N=1,000,000 is likely larger 
 than you need.).


### BST vs. AVLTree ###
Construct inputs for BST and AVLTree to demonstrate that an AVL Tree is asymptotically better
than a Binary Search Tree. Comparing the worst case for each structure is fine here. 
To do this, we expect you to show trends.  You might consider fitting a curve to
your results. Explain your intuition on why your results are what they are.

For this experiment, I used sorted integers as input to find the worst case for each structure. In other words,
I inserted numbers from 1 to n and measured the time taken. I had n be a range from 100 to 5000. From the graph, we see that while
the two data structures have similar runtimes for smaller numbers in the hundredths, as n gets asymptotically larger,
AVLTree is significantly faster than BST. We can see a trend that shows the runtime of AVL increases linearly, while
the runtime of BST increases quadratically. My intuition for the results is that since AVLTree is self-balancing, it usually is a
shallower tree than BST, which means less comparisons, especially in sorted numbers. For example, if we have sorted numbers and we want
to insert 8, we would have to traverse down 7 times to do 7 comparisons in order to insert 8 into a BST. However, for AVLTree we only
have to traverse down 3 times to do 3 comparisons. Thus, AVLTree is asymptotically much superior than BST.

### ChainingHashTable ###
Your ChainingHashTable should take as an argument to its constructor the type of "chains" it uses.  Determine
which type of chain is (on average, not worst case) best: an MTFList, a BST, or an AVL Tree.  Explain your intuition on why
the answer you got makes sense (or doesn't!). 
<pre>TODO</pre>
 
### Hash Functions ###
Write a new hash function (it doesn't have to be any good, but remember to include the code in your repository).
Compare the runtime of your ChainingHashTable when the hash function is varied.  How big of a difference can the
hash function make (on average, not worst case)?  (You should keep all other inputs (e.g., the chain type) constant.)  Explain your intuition on
why your results are what they are.
<pre>TODO</pre>

### General Purpose Dictionary ###
Compare BST, AVLTree, ChainingHashTable, and HashTrieMap on alice.txt.  Is
there a clear winner?  Why or why not?  Is the winner surprising to you?

I tested them by using a modified incCount on each word in the text using a word reader, and calculated the average runtime for all the insertions twice
over 200 trials and 20 warmup trials. Based on the results, we can see that Chaining Hash table performs the best, followed by AVL, HashTrieMap, then BST.
However, there is not a clear winner as all of them ranges from only a couple milliseconds, which is not significant enough to determine anything.
My intuition is that the number of words in the text is not large enough to show the asymptotic difference between the data structures. That said, if 
there were enough words to do so, or sorted words are used (worst case), chaining hash table should still win due to its average  O(1) runtime for insert.
A possible reason for the slower runtime for HashTrieMap is that converting the string to Alphabetic string takes some time.


### uMessage ###
Use uMessage to test out your implementations.  Using N=3, uMessage should take less than a minute to load using
your best algorithms and data structures on a reasonable machine.

 -  How are the suggestions uMessage gives with the default corpus? (here we mean spoken.corpus or irc.corpus, not eggs.txt)
    <pre>TODO</pre>

 - Now, switch uMessage to use a corpus of YOUR OWN text. To do this, you will need a corpus. 
   You can use anything you like (Facebook, google talk, e-mails, etc.)  We provide
   instructions and a script to format Facebook data correctly as we expect it will be the most common
   choice.  If you are having problems getting data, please come to office hours and ask for help.
   Alternatively, you can concatenate a bunch of English papers you've written together to get a corpus
   of your writing.  PLEASE DO NOT INCLUDE "me.txt" IN YOUR REPOSITORY.  WE DO NOT WANT YOUR PRIVATE CONVERSATIONS.
     * Follow these instructions to get your Facebook data: https://www.facebook.com/help/212802592074644
     * Run the ParseFBMessages program in the p2.wordsuggestor package.
     * Use the output file "me.txt" as the corpus for uMessage.
 
 - How are the suggestions uMessage gives wth the new corpus?
   <pre>TODO</pre>

-----

A sample image:

![](husky.jpg)

To show you how it is done.

## Above and Beyond ##
-   Did you do any Above and Beyond?  Describe exactly what you implemented.
 <pre>TODO</pre>
