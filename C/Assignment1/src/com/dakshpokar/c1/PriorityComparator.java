package com.dakshpokar.c1;

import java.util.Comparator;

public class PriorityComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Process p11=(Process)o1;  
		Process p21=(Process)o2;  

		if(p11.getPriority() < p21.getPriority()){
			return 1;
		}
		else if(p11.getPriority() > p21.getPriority()){
			return -1;
		}
		else	{
			return 0;
		}
	}

}
