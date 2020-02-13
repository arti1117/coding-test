package ItDoesNotMatters.KakaoBlindTest.problem;

import java.util.*;

public class CacheLeastRecentlyUsed {

	private static final int HIT = 1;
	private static final int MISS = 5;
	
	private static final int ZERO = 0;
	private static final int ONE = 1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int grosstime;
		int cachesize;
		String[] input;

		grosstime = ZERO;
		cachesize = 3;
		input = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		System.out.println("Executiont Time: "+leastRecentlyUsed(cachesize,input,grosstime));

		cachesize = 3;
		input = new String[] {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
		System.out.println("Executiont Time: "+leastRecentlyUsed(cachesize,input,grosstime));

		cachesize = 2;
		input = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
		System.out.println("Executiont Time: "+leastRecentlyUsed(cachesize,input,grosstime));

		cachesize = 5;
		input = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
		System.out.println("Executiont Time: "+leastRecentlyUsed(cachesize,input,grosstime));

		cachesize = 2;
		input = new String[] {"Jeju", "Pangyo", "NewYork", "newyork"};
		System.out.println("Executiont Time: "+leastRecentlyUsed(cachesize,input,grosstime));

		cachesize = 0;
		input = new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		System.out.println("Executiont Time: "+leastRecentlyUsed(cachesize,input,grosstime));
	}
	
	private static int leastRecentlyUsed(int cachesize, String[] input, int grosstime) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> list = new ArrayList<String>();
		
		for(int i=ZERO;i<input.length;i++) {
			list.add(input[i].toUpperCase());
		}
		
		for(int i=ZERO;i<list.size();i++) {
			if(map.size()<cachesize) {
				if(map.containsKey(list.get(i))) {
					map.replace(list.get(i), map.get(list.get(i)), i);
					grosstime += HIT;
					
				} else {
					map.put(list.get(i), i);
					grosstime += MISS;
				}
			} else if(cachesize==ZERO) {
				map.remove(list.get(i));
				map.put(list.get(i), i);
				grosstime += MISS;
			} else {
				if(map.containsKey(list.get(i))) {
					map.replace(list.get(i), map.get(list.get(i)), i);
					grosstime += HIT;
					
				} else {
					map.remove(removeLeast(map.entrySet()));
					map.put(list.get(i), i);
					grosstime += MISS;
				}
			}
		}
		return grosstime;
	}
	
	private static String removeLeast(Set<Map.Entry<String,Integer>> set) {
		Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
		List<KeyValue> keyValue = new ArrayList<KeyValue>();
		String answer = null;
		
		while(iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			keyValue.add(new KeyValue(entry.getKey(),entry.getValue()));
		}
		Collections.sort(keyValue, new ForSort());
		answer = keyValue.get(ZERO).getKey();
		return answer;
	}
}

class KeyValue {
	private String key;
	private Integer value;
	
	public KeyValue(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Integer getValue() {
		return value;
	}
}

class ForSort implements Comparator<KeyValue> {
	@Override
	public int compare(KeyValue o, KeyValue n) {
		if(o.getValue()<n.getValue()) {
			return -1;
		} else {
			return 1;
		}
	}
}
