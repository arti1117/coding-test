package ItDoesNotMatters.KakaoBlindTest.problem;

import java.util.*;
import java.util.regex.*;

public class DartGame {
	
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int FOUR = 4;
	public static final int STAR = 2;
	public static final int ACHA = -1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution("1S2D*3T"));
		System.out.println(solution("1D2S#10S"));
		System.out.println(solution("1D2S0T"));
		System.out.println(solution("1S*2T*3S"));
		System.out.println(solution("1D#2S*3S"));
		System.out.println(solution("1T2D3D#"));
		System.out.println(solution("1D2S3T*"));
	}
	
	public static int solution(String ask) {
		List<String> seperate = new ArrayList<String>();
		List<Integer> numbers = new ArrayList<Integer>();
		Matcher matcher = Pattern.compile("[0-9]+[SDT][*#]?").matcher(ask);
		String bonus = "@SDT*";
		int answer = ZERO;
		
		while(matcher.find()) {
			seperate.add(matcher.group());
		}
		
		for(String get:seperate) {
			int a = ZERO;
			Matcher x = Pattern.compile("[0-9]+").matcher(get);
			if(x.find()) a = Integer.parseInt(x.group());
			
			int b = ZERO;
			Matcher y = Pattern.compile("[SDT]").matcher(get);
			if(y.find()) b = bonus.indexOf(y.group());
			
			int c = ZERO;
			Matcher z = Pattern.compile("[*#]+").matcher(get);
			if(z.find()) c = bonus.indexOf(z.group());				
			numbers.add(c);
			numbers.add((int)Math.pow(a, b));
		}
		
		for(int i=ZERO;i<numbers.size();i+=TWO) {
			if(numbers.get(i)==FOUR) {
				if(i!=ZERO) {
					numbers.set(i-ONE, numbers.get(i-ONE)*STAR);
					numbers.set(i+ONE, numbers.get(i+ONE)*STAR);
				} else {
					numbers.set(i+ONE, numbers.get(i+ONE)*STAR);
				}
			} else if(numbers.get(i)==-ONE) {
				numbers.set(i+ONE, numbers.get(i+ONE)*ACHA);
			} else {
				numbers.set(i+ONE, numbers.get(i+ONE));
			}
		}
		for(int i=ONE;i<numbers.size();i+=TWO) {
			answer += numbers.get(i);
		}
		return answer;
	}
