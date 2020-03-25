package assign1;

import java.util.*;

public class Appearances {

	//Creates and returns a map by iterating over a collection type
	private static <T> Map<T, Integer> fillMap(Collection<T> col){
		Iterator<T> it = col.iterator();
		Map<T, Integer> map = new HashMap<T, Integer>();
		T cur;
		while(it.hasNext()){
			cur = it.next();
			if(map.containsKey(cur)){
				map.put(cur, map.get(cur) + 1);
			} else {
				map.put(cur, 1);
			}
		}
		return map;
	}
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		Map<T, Integer> aMap = fillMap(a);
		Map<T, Integer> bMap = fillMap(b);
		int result = 0;
		Iterator<T> it = aMap.keySet().iterator();
		while(it.hasNext()){
			T cur = it.next();
			if(aMap.get(cur).equals(bMap.get(cur))) result++;
		}
		return result;
	}
	
}
