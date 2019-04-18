package com.main.pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class LRU {
	LinkedList<Integer> cache;
	int fault = 0;
	int hit = 0;
	public void getIterations(ArrayList<Integer> rs, int capacity) {
		cache = new LinkedList<>();
		System.out.println("PgNo.\tLRUAllocation");
		for(int i = 0; i<rs.size();i++) {
			System.out.print(rs.get(i));
			if(cache.size() <= 0) {
				cache.add(rs.get(i));
				fault = fault + 1;
			}
			else {
				if(cache.contains(rs.get(i))) {
					cache.remove(rs.get(i));
					cache.push(rs.get(i));
					hit = hit + 1;
				}
				else {
					if(cache.size() < capacity) {
						cache.push(rs.get(i));
						fault = fault + 1;
					}
					else {
						cache.removeLast();
						cache.push(rs.get(i));
						fault = fault + 1;
					}
				}
			}
			System.out.print("\t" + displayCache());
			System.out.println();
		}
		System.out.println("Total number of faults: " + fault);
		System.out.println("Hit Ratio: " + (Double.valueOf(hit)/Double.valueOf(rs.size())));

	}
	public String displayCache() {
		String ret = "";
		for(int i = 0;i<cache.size();i++) {
			ret = ret + String.valueOf(cache.get(i)) + " ";
		}
		return ret;
	}
	public static void main(String[] args) {
		LRU lru = new LRU();
		ArrayList<Integer> rs = new ArrayList<>();
		int capacity, size;
		/*
		 * For runtime process
		 */
		/*
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the capacity of Main Memory/RAM: ");
		capacity = scan.nextInt();
		System.out.print("Enter the size of reference string: ");
		size = scan.nextInt();
		System.out.println("Enter the reference string: ");
		Integer x;
		for(int i = 0;i<size;i++) {
			x = scan.nextInt();
			rs.add(x);
		}
		*/
		
		/*
		 * For testing purpose
		 */
		int[] elements = {2,3,2,1,5,2,4,5,3,2,5,2};
		for(int i = 0; i < elements.length;i ++) {
			rs.add(elements[i]);
		}
		capacity = 3;
		lru.getIterations(rs, capacity);
	}
}