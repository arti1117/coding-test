package ItDoesNotMatters.ShortestRange.problem;

import java.util.*;

public class IsItShortestRange {
	static Integer one = 1;
	Integer two = 2;
	static Set<Integer> aa = new HashSet<Integer>(100);
	
	public static void main(String[] args) {

		int[][] params = {{4,10,15,24,26},{0,9,12,20},{5,18,22,30}};
		
		List<Baguni> baguni = new ArrayList<Baguni>();
		for(int i=0;i<params.length;i++) {
			for(int j=0;j<params[i].length;j++) {
				baguni.add(new Baguni(String.valueOf(i),params[i][j]));
			}
		}
		Collections.sort(baguni, new Bagugi());
		/*
		for(Baguni b : baguni) {
			System.out.println(b.value);
		}
		*/
		
		List<Yuelmae> yuelmae = new ArrayList<Yuelmae>();
		b:for(int i=0;i<baguni.size();i++) {
			if((i+params.length)==baguni.size()) break;
			for(int j=i;j<i+params.length-one;j++) {
				if(!yesYouCanDoIt(baguni.get(j).id)) {
					aa.removeAll(aa);
					continue b;
				}
			}
			yuelmae.add(new Yuelmae(String.valueOf(yuelmae.size()),baguni.get(i).value,baguni.get(i+2).value));
			/*
			String x = baguni.get(i).id;
			String y = baguni.get(i+1).id;
			String z = baguni.get(i+2).id;
			
			if(!x.equals(y) && !x.equals(z) && !y.equals(z)) {
				//System.out.println(baguni.get(i).value+","+baguni.get(i+1).value+","+baguni.get(i+2).value);
				yuelmae.add(new Yuelmae(String.valueOf(yuelmae.size()),baguni.get(i).value,baguni.get(i+2).value));
				continue;
			}*/
		}
		Collections.sort(yuelmae, new Bigogi());
		List<List<Integer>> input = Arrays.asList(Arrays.asList(4,10,15,24,26),Arrays.asList(0,9,12,20),Arrays.asList(5,18,22,30));
		int[] yesIAmSerious = AreYouSerious(input);
		System.out.print("{");
		for(int i=0;i<yesIAmSerious.length;i++) {
			System.out.print(yesIAmSerious[i]);
			if(i == yesIAmSerious.length-one) break; 
			System.out.print(", ");
		}
		System.out.print("}");;
	}
	
	// input {{4,10,15,24,26},{0,9,12,20},{5,18,22,30}}
	// output {20,24}
	public static int[] AreYouSerious(List<List<Integer>> nums) {
		
		List<Baguni> baguni = new ArrayList<Baguni>();
		
		for(int i=0;i<nums.size();i++) {
			for(int j=0;j<nums.get(i).size();j++) {
				baguni.add(new Baguni(String.valueOf(i),nums.get(i).get(j)));
			}
		}
		
		Collections.sort(baguni, new Bagugi());
		
		List<Yuelmae> yuelmae = new ArrayList<Yuelmae>();
		a:for(int i=0;i<baguni.size();i++) {
			if((i+nums.size())==baguni.size()) break;
			for(int j=i;j<i+nums.size()-one;j++) {
				if(!yesYouCanDoIt(baguni.get(j).id)) {
					aa.removeAll(aa);
					continue a;
				}
			}
			yuelmae.add(new Yuelmae(String.valueOf(yuelmae.size())
						,baguni.get(i).value
						,baguni.get(i+nums.size()-one).value));
		}
		Collections.sort(yuelmae, new Bigogi());
		
		int[] nuts = new int[] {yuelmae.get(0).x,yuelmae.get(0).y};	
		return nuts;
	};
	public static boolean yesYouCanDoIt(String index) {
		Integer b = Integer.parseInt(index);
		if(!aa.contains(b)) {
			aa.add(b);
		} else {
			aa.removeAll(aa);
			return false;
		}
		return true;
	}
}

class Baguni {
	
	String id;
	int value;
	
	public Baguni(String id, int value) {
		this.id = id;
		this.value = value;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

class Bagugi implements Comparator<Baguni> {
	
	@Override
	public int compare(Baguni e1, Baguni e2) {
		if(e1.value<e2.value) {
			return -1;
		} else {
			return 1;
		}
	}
}

class Bigogi implements Comparator<Yuelmae> {
	@Override
	public int compare(Yuelmae c1, Yuelmae c2) {
		if(c1.getSum()<c2.getSum()) {
			return -1;
		} else {
			return 1;
		}
	}
}

class Yuelmae {
	String id;
	int x;
	int y;
	
	public Yuelmae(String id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public int getSum() {
		return y-x;
	}
}
