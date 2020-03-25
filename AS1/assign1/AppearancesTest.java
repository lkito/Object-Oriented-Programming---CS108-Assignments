package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class AppearancesTest {
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
			// note: String.valueOf() converts lots of things to string form
		}
		return list;
	}
	
	@Test
	public void testSameCount1() {
		List<String> a = stringToList("abbccc");
		List<String> b = stringToList("cccbba");
		assertEquals(3, Appearances.sameCount(a, b));
	}

	@Test
	public void testSameCount2() {
		List<String> a = stringToList("aba1aba2aba3");
		List<String> b = stringToList("sababi");
		assertEquals(0, Appearances.sameCount(a, b));
	}

	@Test
	public void testSameCount3() {
		List<String> a = stringToList("12345678910");
		List<String> b = stringToList("11121314151617181920");
		assertEquals(8, Appearances.sameCount(a, b));
	}

	@Test
	public void testSameCount4() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
	}

	@Test
	public void testSameCount5() {
		List<Integer> a = Arrays.asList(1, 1, 1, 109, 1, 1, 1);
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 1, 1, 1, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 1, 1, 1, 1, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(109, 1, 1, 1, 1, 1, 1)));
	}

	@Test
	public void testSameCount6() {
		List<Integer> a = Arrays.asList();
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 1, 1, 1, 1)));
		List<Integer> b = Arrays.asList();
		assertEquals(0, Appearances.sameCount(a, b));
	}

	@Test
	public void testSameCount7() {
		Double[] doubleArr = {2.23, 0.0, 2.23, 1.99999, 4.5};
		List<Double> a = Arrays.asList(doubleArr);
		Double[] testArr = {2.23, 0.0, 2.23, 2.23, 1.99999};
		List<Double> testList = Arrays.asList(testArr);
		Double[] testArr2 = {2.23, 0.0, 2.23, 1.99999, 1.999999, 1.9999999, 2.0, 10.0, 0.0, 4.5};
		List<Double> testList2 = Arrays.asList(testArr2);
		assertEquals(2, Appearances.sameCount(a, testList));
		assertEquals(3, Appearances.sameCount(a, testList2));
	}

	@Test
	public void testSameCount8() {

		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		Set<Integer> set = new HashSet<Integer>(a);

		List<Integer> b = Arrays.asList(1, 12, 3, 2, 6, 10, 3, 5);
		Set<Integer> testSet = new HashSet<Integer>(b);

		assertEquals(4, Appearances.sameCount(set, testSet));
	}
}
