package ItDoesNotMatters.ShortestRange.problem;

import java.util.*;

public class IsItShortestRange {
	static Integer ZERO = 0;
	static Integer ONE = 1;
	static Integer TWO = 2;
	static Set<Integer> SAMERANGE = new HashSet<Integer>(100);
	
	public static void main(String[] args) {
		
		List<List<Integer>> input = Arrays.asList(
										 Arrays.asList(4,10,15,24,26)
										,Arrays.asList(0,9,12,20)
										,Arrays.asList(5,20,22,30)
										,Arrays.asList(3,17,19,25,27,31));
		
		int[] sameRangeArr = searchShortest(input);
		System.out.print("{");
		if(sameRangeArr.length > TWO) {
			for(int i=0;i<sameRangeArr.length;i+=TWO) {
				System.out.print("{");
					System.out.print(sameRangeArr[i]+", "+sameRangeArr[i+ONE]);
				System.out.print("}");
				
				if(i >= sameRangeArr.length-TWO) continue; 
				System.out.print(", ");
			}
		} else {
			for(int i=0;i<sameRangeArr.length;i++) {
				System.out.print(sameRangeArr[i]);
				if(i >= sameRangeArr.length-ONE) continue; 
				System.out.print(", ");
			}
		}
		System.out.print("}");
	}
	
	// input {{4,10,15,24,26},{0,9,12,20},{5,18,22,30}}
	// output {20,24}
	public static int[] searchShortest(List<List<Integer>> nums) {
		
		List<NumberObj> numberObj = new ArrayList<NumberObj>();
		
		for(int i=0;i<nums.size();i++) {
			for(int j=0;j<nums.get(i).size();j++) {
				numberObj.add(new NumberObj(String.valueOf(i),nums.get(i).get(j)));
			}
		}
		
		Collections.sort(numberObj, new SortNumberObj());
		
		List<SameRangeObj> sameRangeObj = new ArrayList<SameRangeObj>();
		//same range side, sequential side
		a:for(int i=0;i<numberObj.size();i++) {
			int j = ZERO;
			if((i+nums.size())==numberObj.size()) break;
			if((j+nums.size())==numberObj.size()) break;
			for(j=i;j<numberObj.size();j++) {
				if(!seperateSuite(numberObj.get(j).getId())) continue a;
				if(SAMERANGE.size()==nums.size()) break;
			}
			if(SAMERANGE.size()==nums.size()) {
				SAMERANGE.removeAll(SAMERANGE);
				sameRangeObj.add(new SameRangeObj(String.valueOf(sameRangeObj.size())
						,numberObj.get(i).getValue()
						,numberObj.get(j).getValue()));
			}
		}
		Collections.sort(sameRangeObj, new SortSameRangeObj());

		List<Integer> answer = new ArrayList<Integer>();
		for(int i=0;i<sameRangeObj.size();i++) {
			if(sameRangeObj.get(ZERO).getRange()==sameRangeObj.get(i).getRange()) {
				answer.add(sameRangeObj.get(i).getMin());
				answer.add(sameRangeObj.get(i).getMax());
			}
		}		
		int[] nuts = new int[answer.size()];
		for(int i=0;i<answer.size();i++) {
			nuts[i] = answer.get(i);
		}
		return nuts;
	};
	
	public static boolean seperateSuite(String index) {
		Integer b = Integer.parseInt(index);
		if(!SAMERANGE.contains(b)) {
			SAMERANGE.add(b);
		}
		return true;
	}
}

class NumberObj {
	
	private String id;
	private int value;
	
	public NumberObj(String id, int value) {
		this.id = id;
		this.value = value;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getId() {
		return id;
	}

	public int getValue() {
		return value;
	}
	
	
}

class SortNumberObj implements Comparator<NumberObj> {
	
	@Override
	public int compare(NumberObj e1, NumberObj e2) {
		if(e1.getValue()<e2.getValue()) {
			return -1;
		} else {
			return 1;
		}
	}
}

class SameRangeObj {
	private String id;
	private int min;
	private int max;
	
	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public SameRangeObj(String id, int min, int max) {
		this.id = id;
		this.min = min;
		this.max = max;
	}
	
	public int getRange() {
		return max-min;
	}
}

class SortSameRangeObj implements Comparator<SameRangeObj> {
	@Override
	public int compare(SameRangeObj c1, SameRangeObj c2) {
		if(c1.getRange()<c2.getRange()) {
			return -1;
		} else {
			return 1;
		}
	}
}
