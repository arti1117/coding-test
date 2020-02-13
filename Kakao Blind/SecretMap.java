package ItDoesNotMatters.KakaoBlindTest.problem;

import java.util.*;

public class SecretMap {
	
	private static final Integer ONE = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};

		List<String> detoxic = new ArrayList<String>();
		detoxic = Arrays.asList(detoxification(n,arr1,arr2));
		System.out.print("[");
		for(int i=0;i<detoxic.size();i++) {
			System.out.print('"');
			System.out.print(detoxic.get(i));
			System.out.print('"');
			if(i+ONE==detoxic.size()) break;
			System.out.print(", ");
		}
		System.out.print("]");
	}
	
	//input 5,[9, 20, 28, 18, 11],[30, 1, 21, 17, 28]
	//output ["#####","# # #", "### #", "# ##", "#####"]
	public static String[] detoxification(int n, int[] x, int[] y) {
		
		String[] answer = new String[n];
		for(int i=0;i<n;i++) {
			answer[i] = Integer.toBinaryString(x[i]|y[i]).replace('1','#').replace('0',' ');
		}
		return answer;
	}
}
