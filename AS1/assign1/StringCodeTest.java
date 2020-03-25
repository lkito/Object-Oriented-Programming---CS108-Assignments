// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringCodeTest {
	//
	// blowup
	//
	@Test
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));

		assertEquals("aabbccddee", StringCode.blowup("1a1b1c1d1e"));
		assertEquals("ZZZZZZZZZZ", StringCode.blowup("9Z"));
	}

	@Test
	public void testBlowup2() {
		// things with digits

		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));

		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));

		assertEquals("aa33", StringCode.blowup("aa23"));
		assertEquals("aa22", StringCode.blowup("aa22"));

		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));

		assertEquals("", StringCode.blowup("0000"));
		assertEquals("", StringCode.blowup("0"));
		assertEquals("0", StringCode.blowup("10"));
	}

	@Test
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));

		assertEquals("    ", StringCode.blowup("3 "));
		assertEquals("2\"\"\"", StringCode.blowup("12\""));

		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));

		assertEquals("2334445555", StringCode.blowup("012345"));
	}


	//
	// maxRun
	//
	@Test
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));

		assertEquals(2, StringCode.maxRun("h  pla"));
		assertEquals(3, StringCode.maxRun("---___---"));
		assertEquals(4, StringCode.maxRun("\"\"\"\"\'\'\'\""));
		assertEquals(3, StringCode.maxRun("-- ---"));
		assertEquals(1, StringCode.maxRun("IYO ARABETS ROSTEVAN, MEFE GMRTISAGAN SVIANI, " +
				"MAGALI, UXVI, MDABALI, LASHQAR-MRAVALI, YMIANI. MOSAMARTLE DA MOWYALE, MORWMULI, " +
				"GANGEBIANI, TVIT MEOMARI UEBRO, KVLA MOUBARI WYLIANI."));

	}

	@Test
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));

		assertEquals(2, StringCode.maxRun("12345678900987654321"));
		assertEquals(3, StringCode.maxRun("AaAAAbb"));
		assertEquals(1, StringCode.maxRun("AaAaAaAaA"));
	}

	@Test
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("1"));

		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));

		assertEquals(3, StringCode.maxRun("111a22a33"));;
		assertEquals(1, StringCode.maxRun("b1b1b1a2b2a3b3b"));
	}

	//
	// stringIntersect
	//
	@Test
	public void testIntersect1() {
		//true tests
		assertEquals(true, StringCode.stringIntersect("a", "a", 1));
		assertEquals(true, StringCode.stringIntersect("aba", "ab", 2));
		assertEquals(true, StringCode.stringIntersect("12a123", "12a123", 6));
		assertEquals(true, StringCode.stringIntersect("AAAAbbbb", "cccAAbbBBB", 2));
		assertEquals(true, StringCode.stringIntersect("AAAAbbbb", "cccAAbbBBB", 4));
	}

	@Test
	public void testIntersect2() {
		//false tests
		assertEquals(false, StringCode.stringIntersect("a", "a", 2));
		assertEquals(false, StringCode.stringIntersect("aa", "ab", 2));
		assertEquals(false, StringCode.stringIntersect("abababa", "abaa", 4));
		assertEquals(false, StringCode.stringIntersect("aAaAaAaA", "aaaa", 4));
		assertEquals(false, StringCode.stringIntersect("123123456", "321", 3));
		assertEquals(false, StringCode.stringIntersect("AAAAbbbb", "cccAAbbBBB", 5));

	}

	@Test
	public void testIntersect3() {
		//Empty strings
		assertEquals(false, StringCode.stringIntersect("", "", 1));
		assertEquals(false, StringCode.stringIntersect("", "", 10));

		//zero length
		assertEquals(true, StringCode.stringIntersect("AAAAbbbb", "cccAAbbBBB", 0));
		assertEquals(true, StringCode.stringIntersect("", "", 0));
	}



}
