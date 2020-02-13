package ItDoesNotMatters.KakaoBlindTest.problem;

import java.time.*;
import java.util.*;

public class ShuttleBus {
	
	private static final int ZERO = 0;
	private static final int ONE = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int schedule=0;
		int interval=0;
		int boarding=0;
		String[] timetable=null;
		
		schedule = 2;
		interval = 10;
		boarding = 3;
		timetable = new String[] {"08:00", "09:09", "09:10"}; // 09:10
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 2;
		interval = 1;
		boarding = 2;
		timetable = new String[] {"09:00", "09:00", "09:00", "09:00"}; //08:59
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 4;
		interval = 10;
		boarding = 2;
		timetable = new String[] {"08:00", "09:09", "09:10"}; //09:30
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 3;
		interval = 1;
		boarding = 2;
		timetable = new String[] {"09:00", "09:00", "09:00", "09:00", "09:02"}; // 09:02
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 1;
		interval = 1;
		boarding = 5;
		timetable = new String[] {"08:00", "08:01", "08:02", "08:03"}; // 09:00
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 2;
		interval = 10;
		boarding = 2;
		timetable = new String[] {"08:00", "09:09", "09:10"}; // 09:09
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 2;
		interval = 1;
		boarding = 2;
		timetable = new String[] {"09:00", "09:00", "09:00", "09:00"}; //08:59
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 1;
		interval = 1;
		boarding = 5;
		timetable = new String[] {"00:01", "00:01", "00:01", "00:01", "00:01"}; //00:00
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 1;
		interval = 1;
		boarding = 1;
		timetable = new String[] {"23:59"}; // 09:00
		System.out.println(exactTime(schedule,interval,boarding,timetable));
		schedule = 10;
		interval = 60;
		boarding = 45;
		timetable = new String[] {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59" 
		, "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
		System.out.println(exactTime(schedule,interval,boarding,timetable)); //18:00
	}
	
	private static String exactTime(int schedule, int interval, int boarding, String[] timetable) {
		final int board = boarding;
		LocalTime answer= LocalTime.now();
		LocalTime start = LocalTime.parse("09:00");
		LocalTime ended = LocalTime.parse("00:00");
		List<LocalTime> list = new Vector<LocalTime>();

		if(timetable.length<boarding) {
			return LocalTime.parse(start.plusMinutes((schedule-ONE)*interval).toString()).toString();
		}
		
		for(int i=ZERO;i<timetable.length;i++) {
			list.add(LocalTime.parse(timetable[i]));
		}
		
		Collections.sort(list, new TimeSorting());
		
		for(int i=ONE;i<=schedule;i++) {
			for(int j=ZERO;j<list.size();j++) {
				if(list.get(j).isAfter(start)) {
					answer = start;
					break;
				} else {
					if(list.get(j).isBefore(ended)) { 
						boarding++;
						if(j+ONE<(i*board)) {boarding--;}
					} else {boarding--;}
					answer = list.get(j).minusMinutes(ONE);
					if(boarding > ZERO) {answer = start;}
					else {break;}
				}	
			}
			ended = start;
			boarding = board;
			start = start.plusMinutes(interval);
		}
		return answer.toString();
	}
}

class TimeSorting implements Comparator<LocalTime> {
	@Override
	public int compare(LocalTime o, LocalTime n) {
		if(o.isBefore(n)) {
			return -1;
		} else if(o.isAfter(n)) {
			return 1;
		} else { 
			return 0;
		}
	}
}
