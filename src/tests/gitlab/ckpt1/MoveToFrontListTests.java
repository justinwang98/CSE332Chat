package tests.gitlab.ckpt1;

import java.util.Arrays;

import cse332.datastructures.containers.Item;
import datastructures.dictionaries.MoveToFrontList;
import tests.TestsUtility;

public class MoveToFrontListTests extends TestsUtility {
    public static void main(String[] args) {
        new MoveToFrontListTests().run();
    }

    @Override
	protected void run() {
	    SHOW_TESTS = true;
	    PRINT_TESTERR = true;
	    DEBUG = true;

	    test("testHugeMTFList");
	    test("checkStructure");

	    finish();
	}

    @SuppressWarnings("unchecked")
	public static int checkStructure() {
	    MoveToFrontList<Integer, Integer> list = new MoveToFrontList<Integer, Integer>();

	    int[] arr = {6, 5, 10, 14, 10, 31, 10, 13, 10, 10, 12, 10, 14, 10, 10, 11, 10, 14, 9, 8, 3, 2, 1, 0, 7, 4};
	    for(int i = 0; i < arr.length; i++) {
		Integer oldValue = list.find(arr[i]);
		if (oldValue == null) {
		    list.insert(arr[i], 1);
		} else {
		    list.insert(arr[i], 1 + oldValue);
		}
	    }
	    // Convert iterator to string
	    Item<Integer, Integer>[] dcs = (Item<Integer, Integer>[])new Item[list.size()];
	    int i = 0;
	    for (Item<Integer, Integer> item : list) {
		dcs[i++] = item;
	    }

	    // Compare strings to make sure we get the right one
	    // Can use list.toString as well, but I'm not sure if students may modify that
	    String mtf_correct = "[4=1, 7=1, 0=1, 1=1, 2=1, 3=1, 8=1, 9=1, 14=3, 10=9, 11=1, 12=1, 13=1, 31=1, 5=1, 6=1]";
	    String mtf_test = Arrays.toString(dcs);
	    return mtf_correct.equals(mtf_test) ? 1 : 0;
	}

    public static int testHugeMTFList() {
	MoveToFrontList<String, Integer> list = new MoveToFrontList<>();

	int n = 1000;
	// Add them
	for (int i = 0; i < 5 * n; i++) {
	    int k = (i % n) * 37 % n;
	    String str = String.format("%05d", k);
	    for (int j = 0; j < k + 1; j ++)
		list.insert(str, list.find(str) == null ? 1 : list.find(str) + 1);
	}
	// Delete them all
	boolean passed = true;
	int totalCount = 0;
	for (Item<String, Integer> dc : list) {
	    passed &= (Integer.parseInt(dc.key) + 1) * 5 == dc.value;
	    totalCount += dc.value;
	}
	
	// Check sizes
	passed &= totalCount == (n * (n + 1)) / 2 * 5;
	passed &= list.size() == n;
	passed &= list.find("00851") == 4260;

	return passed ? 1 : 0;
    }
}
