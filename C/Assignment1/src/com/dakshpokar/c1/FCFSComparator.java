package com.dakshpokar.c1;

import java.util.Comparator;

public class FCFSComparator implements Comparator{
	public int compare(Object p1,Object p2){  
		Process p11=(Process)p1;  
		Process p21=(Process)p2;  

		if(p11.getArrTime() < p21.getArrTime()){
			return -1;
		}
		else if(p11.getArrTime() > p21.getArrTime()){
			return 1;
		}
		else	{
			return 0;
		}
	}  
}
