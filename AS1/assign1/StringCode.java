package assign1;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if(str.length() == 0) return 0;
		int max = 1, curLen = 1;
		char lastChar = str.charAt(0);
		for(int i = 1; i < str.length(); i++){
			if(str.charAt(i) == lastChar) {
				curLen++;
			} else {
				lastChar = str.charAt(i);
				curLen = 1;
			}
			if (curLen > max) max = curLen;
		}
		return max;
	}

	//returns string of length n consisting of the passed character
	private static String miniBlow(char c, int n){
		String result = "";
		for(int i = 0; i < n; i++){
			result += c;
		}
		return result;
	}
	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		String result = "";
		for(int i = 0; i < str.length() - 1; i++){
			if(Character.isDigit(str.charAt(i))){
				result += miniBlow(str.charAt(i + 1), (str.charAt(i) - '0'));
			} else {
				result += str.charAt(i);
			}
		}
		if(str.length() != 0 && !Character.isDigit(str.charAt(str.length() - 1))) {
			result += str.charAt(str.length() - 1);
		}
		return result;
	}
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		if(len == 0) return true;
		Set<String> first = new HashSet<String>();
		Set<String> second = new HashSet<String>();
		for(int i = 0; i < a.length() - len + 1; i++){
			first.add(a.substring(i, i + len));
		}
		for(int i = 0; i < b.length() - len + 1; i++){
			second.add(b.substring(i, i + len));
		}
		boolean intersects = !Collections.disjoint(first, second);
		return intersects;
	}
}
