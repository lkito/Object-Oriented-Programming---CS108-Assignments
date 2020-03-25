/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/
package assign1;

import java.util.*;

public class Taboo<T> {

	private Map<T, HashSet<T> > ruleMap;
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		this.ruleMap = new HashMap<T, HashSet<T>>();
		Iterator<T> it = rules.iterator();
		T cur;
		T last = null;
		if(it.hasNext()) last = it.next();
		while(it.hasNext()){
			cur = it.next();
			if(cur == null){
				last = null;
				continue;
			}
			if(last != null){
				if(!ruleMap.containsKey(last)) ruleMap.put(last, new HashSet<T>());
				ruleMap.get(last).add(cur);
			}
			last = cur;
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		if(!ruleMap.containsKey(elem)) return Collections.emptySet();
		 return ruleMap.get(elem);
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		T last = null;
		if(!list.isEmpty()){
			last = list.get(0);
		}
		int size = list.size();
		for(int i = 1; i < size; i++){
			if(ruleMap.containsKey(last) && ruleMap.get(last).contains(list.get(i))){
				list.remove(i);
				size--;
				i--;
			} else last = list.get(i);
		}
	}
}




